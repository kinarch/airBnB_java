package com.richard.airbnb.tools;

import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Maison;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * Permet de retourner une nouvelle liste filtrée de logements.
 */
public class Search {

    private static final int YES = 1;
    private static final int NO = -1;
    private static final int UNDEFINED = 0;

    //  required
    private int nbVoyageurs;

    //  optionnal
    private int tarifMinParNuit;
    private int tarifMaxParNuit;
    private int withPiscine;
    private int withJardin;
    private int withBalcon;

    /**
     * @param builder to construct the search.
     */
    public Search(SearchBuilder builder) {
        this.nbVoyageurs = builder.nbVoyageurs;
        this.tarifMinParNuit = builder.tarifMinParNuit;
        this.tarifMaxParNuit = builder.tarifMaxParNuit;
        this.withPiscine = builder.possedePiscine;
        this.withJardin = builder.possedeJardin;
        this.withBalcon = builder.possedeBalcon;
    }

    public void displayOptions() {
        out.println("voyageurs => " + getNbVoyageurs());
        out.println("tarif min => " + getTarifMinParNuit());
        out.println("tarif max => " + getTarifMaxParNuit());
        out.println("piscine   => " + getWithPiscine());
        out.println("jardin    => " + getWithJardin());
        out.println("balcon    => " + getWithBalcon());
    }

    /*
        --  RESULT WITH PREDICATE
     */


    /**
     * @return the filtered logements list.
     */
    public List<Logement> result() {
        out.println("FILTER");
        displayOptions();
//        return AirBnBData.getInstance().logementList.stream()
//                .filter(predNbVoyageur()
//                        .and(predTarifMin())
//                        .and(predTarifMax())
//                        .and(
//                                predJardin().and(predPiscine()).or(predBalcon())
//                        )
//                ).collect(Collectors.toList());
        return AirBnBData.getInstance().logementList.stream()
                .filter(predNbVoyageur()
                        .and(predTarifMin())
                        .and(predTarifMax())
                        .and(predJardin())
                        .and(predPiscine())
                        .and(predBalcon())
                ).collect(Collectors.toList());
    }

    private Predicate<Logement> predNbVoyageur() {
        return l -> l.getNbVoyageursMax() >= nbVoyageurs;
    }

    private Predicate<Logement> predTarifMin() {
        return l -> l.getTarifParNuit() >= tarifMinParNuit;
    }

    private Predicate<Logement> predTarifMax() {
        return l -> l.getTarifParNuit() <= tarifMaxParNuit;
    }

    private Predicate<Logement> predJardin() {
        return l -> {
            if (l instanceof Maison) {
                Maison m = (Maison) l;
                boolean hasJardin = m.getSuperficieJardin() > 0;
                return affirm(withJardin, hasJardin);
            }
            return true;
        };
    }

    private Predicate<Logement> predPiscine() {
        return l -> {
            if (l instanceof Maison) {
                boolean hasPiscine = ((Maison) l).isPossedePiscine();
                return affirm(withPiscine, hasPiscine);
            }
            return true;
        };
    }

    private Predicate<Logement> predBalcon() {
        return l -> {
            if (l instanceof Appartement) {
                boolean hasBalcon = ((Appartement) l).getSuperficieBalcon() > 0;
                return affirm(withBalcon, hasBalcon);
            }
            return true;
        };
    }

    /*
        --  RESULT WITH IF ELSE CONDITION
     */

    /**
     * @return
     * @throws Exception
     */
    public ArrayList<Logement> resultv2() {

        ArrayList<Logement> listResult = new ArrayList<>();

        displayOptions();

        for (Logement l : AirBnBData.getInstance().logementList) {

            // Test nombre de voyageur
            if (l.getNbVoyageursMax() < nbVoyageurs)
                continue;

            // Test du tarif par nuit
            if (l.getTarifParNuit() < tarifMinParNuit || l.getTarifParNuit() > tarifMaxParNuit)
                continue;

            // Test pour la piscine
            if (withPiscine == YES) {
                // Oui pour la piscine du coup c'est forcément une maison
                if (l instanceof Maison) {
                    Maison maison = (Maison) l;
                    // La maison n'a pas de piscine, on ne la prend pas
                    if (!maison.isPossedePiscine())
                        continue;
                } else
                    continue;

            } else if (withPiscine == NO) {
                // Non pour la piscine
                if (l instanceof Maison) {
                    Maison maison = (Maison) l;
                    // Si la maison a une piscine, on ne la prend pas
                    if (maison.isPossedePiscine())
                        continue;
                }
            }

            // Test pour le jardin
            if (withJardin == YES) {
                // Oui pour le jardin du coup c'est forcément une maison
                if (l instanceof Maison) {
                    Maison maison = (Maison) l;
                    // Si la maison n'a pas de jardin, on ne la prend pas
                    if (maison.getSuperficieJardin() == 0)
                        continue;
                } else
                    continue;
            } else if (withJardin == NO) {
                // Non pour le jardin
                if (l instanceof Maison) {
                    Maison maison = (Maison) l;
                    // Si la maison a un jardin, on ne la prend pas
                    if (maison.getSuperficieJardin() != 0)
                        continue;
                }
            }

            // Test pour le balcon
            if (withBalcon == YES) {
                // Oui pour le balcon, c'est forcément un appartement
                if (l instanceof Appartement) {
                    Appartement appartement = (Appartement) l;
                    // Si l'appartement n'a pas de balcon, on ne le prend pas
                    if (appartement.getSuperficieBalcon() == 0)
                        continue;
                } else
                    continue;
            } else if (withBalcon == NO) {
                // Non pour le balcon
                if (l instanceof Appartement) {
                    Appartement appartement = (Appartement) l;
                    // Si l'appartement a pas un balcon, on ne le prend pas
                    if (appartement.getSuperficieBalcon() != 0)
                        continue;
                }

            }

            listResult.add(l);
        }

        return listResult;
    }

    public int getNbVoyageurs() {
        return nbVoyageurs;
    }

    public int getTarifMinParNuit() {
        return tarifMinParNuit;
    }

    public int getTarifMaxParNuit() {
        return tarifMaxParNuit;
    }

    public int getWithPiscine() {
        return withPiscine;
    }

    public int getWithJardin() {
        return withJardin;
    }

    public int getWithBalcon() {
        return withBalcon;
    }

    /**
     * Return a boolean represent if the option affirm condition.
     *
     * @param option the option with YES, NO or UNDEFINED value
     * @param condition the condition of the filter
     * @return boolean between option and condition
     */
    private boolean affirm(int option, boolean condition) {
        switch (option) {
            case YES:
                return condition;
            case NO:
                return !condition;
            default:
                return true;
        }
    }

    public static class SearchBuilder {

        //  required
        private int nbVoyageurs;

        //  optionnal
        private int tarifMinParNuit;
        private int tarifMaxParNuit;
        private int possedePiscine;
        private int possedeJardin;
        private int possedeBalcon;

        public SearchBuilder(int nbVoyageurs) {
            this.nbVoyageurs = nbVoyageurs;
            this.tarifMaxParNuit = Integer.MAX_VALUE;
        }

        public SearchBuilder tarifMinParNuit(int tarifMinParNuit) {
            this.tarifMinParNuit = tarifMinParNuit;
            return this;
        }

        public SearchBuilder tarifMaxParNuit(int tarifMaxParNuit) {
            this.tarifMaxParNuit = tarifMaxParNuit;
            return this;
        }

        public SearchBuilder possedePiscine(boolean possedePiscine) {
            this.possedePiscine = possedePiscine ? YES : NO;
            return this;
        }

        public SearchBuilder possedeJardin(boolean possedeJardin) {
            this.possedeJardin = possedeJardin ? YES : NO;
            return this;
        }

        public SearchBuilder possedeBalcon(boolean possedeBalcon) {
            this.possedeBalcon = possedeBalcon ? YES : NO;
            return this;
        }

        public Search build() {
            return new Search(this);
        }
    }
}


//                .filter(l -> {
//                    if (possedePiscine == 0 && possedeBalcon == 0 && possedeJardin == 0) {
//                        return true;
//                    } else if (possedeBalcon <= 0 && l instanceof Maison) {
//                        Maison m = (Maison) l;
////                        return (possedePiscine == 1 && m.isPossedePiscine() && possedeJardin == 1 && m.getSuperficieJardin() > 0
////                                || possedePiscine == 1 && possedeJardin == -1 && m.isPossedePiscine() && m.getSuperficieJardin() == 0
////                                || possedePiscine == 1 && possedeJardin == 0 && m.isPossedePiscine()
////                                || possedePiscine == -1 && possedeJardin == 1 && m.getSuperficieJardin() > 0 && !m.isPossedePiscine()
////                                || possedePiscine == -1 && !m.isPossedePiscine() && possedeJardin == -1 && m.getSuperficieJardin() <= 0
////                                || possedePiscine == 0 && possedeJardin == 1 && m.getSuperficieJardin() > 0
////                        );
//                    } else if (possedePiscine <= 0 && possedeJardin <= 0 && l instanceof Appartement) {
//                        Appartement a = (Appartement) l;
//                        return (possedeBalcon == 1 && a.getSuperficieBalcon() > 0
//                                || possedeBalcon == -1 && a.getSuperficieBalcon() == 0
//                                || possedeBalcon == 0
//                        );
//                    }
//                    return false;
//                })

//        for (Logement l : logementList) {
//            if (l.getNbVoyageursMax() >= nbVoyageurs && l.getTarifParNuit() < tarifMaxParNuit && l.getTarifParNuit() > tarifMinParNuit) {
//                if (possedeBalcon == 0 && l instanceof Maison) {
//                    Maison maison = (Maison) l;
//
//                    System.out.println("-----");
//                    System.out.println("MAISON " + ++i);
//                    System.out.println(maison.getHote());
//                    System.out.println("piscine " + maison.isPossedePiscine());
//                    System.out.println("jardin " + (maison.getSuperficieJardin() > 0));
//
//                    //  ajout possede piscine & jardin
//                    if (possedePiscine == 1 && maison.isPossedePiscine() && possedeJardin == 1 && maison.getSuperficieJardin() > 0) {
//                        result.add(l);
//                    }
//                    //  ajout possede ni piscine & ni jardin
//                    else if (possedePiscine == -1 && !maison.isPossedePiscine() && possedeJardin == -1 && maison.getSuperficieJardin() <= 0) {
//                        result.add(l);
//                    }
//                    //  ajout juste jardin
//                    else if (possedePiscine == -1 && possedeJardin == 1 && maison.getSuperficieJardin() > 0 && !maison.isPossedePiscine()) {
//                        result.add(l);
//                    }
//                    //  ajout juste piscine
//                    else if (possedePiscine == 1 && possedeJardin == -1 && maison.isPossedePiscine() && maison.getSuperficieJardin() == 0) {
//                        result.add(l);
//                    }
//                    //  ajout piscine & peut-etre jardin
//                    else if (possedePiscine == 1 && possedeJardin == 0 && maison.isPossedePiscine()) {
//                        result.add(l);
//                    }
//                    //  ajout peut-être piscine et jardin
//                    else if (possedePiscine == 0 && possedeJardin == 1 && maison.getSuperficieJardin() > 0) {
//                        result.add(l);
//                    }
//                } else if (possedeJardin == 0 && possedePiscine == 0 && l instanceof Appartement) {
//                    Appartement app = (Appartement) l;
//                    //  balcon
//                    if (possedeBalcon == 1 && app.getSuperficieBalcon() > 0) {
//                        result.add(l);
//                    }
//                    //  pas de balcon
//                    else if (possedeBalcon == -1 && app.getSuperficieBalcon() == 0) {
//                        result.add(l);
//                    }
//                    //  neutre
//                    else if (possedeBalcon == 0) {
//                        result.add(l);
//                    }
//                }
//            }
//        }
//
//        return result;
