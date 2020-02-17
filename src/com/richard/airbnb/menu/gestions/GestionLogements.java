package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Maison;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionLogements extends Gestion {

    public static final ArrayList<Logement> logementList = Menu.logementList;
    private static final int TYPE_MAISON = 1;
    private static final int TYPE_APPARTEMENT = 2;

    private GestionLogements() {
    }

    /**
     * Menu des logements.
     */
    public static void init() {

        System.out.println("---------- ----------");
        System.out.println("Gestion des Logements");
        System.out.println("Saisir une option :");
        System.out.println(ADD + " : Ajouter un logement");
        System.out.println(DELETE + " : Supprimer un logement");
        System.out.println(DISPLAY + " : Afficher la liste");
        System.out.println(BACK + " : Retour");

        switch (Menu.choose(N_OPTIONS)) {
            case ADD:
                try {
                    add();
                } catch (InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Une erreur de saisie est survenue lors de l'ajout d'un logement.");
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue lors de l'ajout d'un logement : " + e.getMessage());
                } finally {
                    init();
                }
                break;
            case DELETE:
                try {
                    delete();
                } catch (InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Une erreur de saisie est survenue lors de la suppression d'un logement.");
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue lors de l'ajout d'un logement : " + e.getMessage());
                } finally {
                    init();
                }
                break;
            case DISPLAY:
                display();
                break;
            case BACK:
                back();
                break;
        }
    }


    private static void add() throws Exception {

        System.out.println("=> Ajouter un logement.");

        final ArrayList<Hote> listeHotes = GestionHotes.hoteList;

        if (!listeHotes.isEmpty()) {

            Hote hote;
            int indexHote;
            int typeLogement;
            int superficie;
            int tarifParNuit;
            int voyageurMax;

            System.out.println("Saisir le type de logement :");
            System.out.println("1 : Maison");
            System.out.println("2 : Appartement");
            System.out.println("3 : Retour");

            typeLogement = Menu.choose(3);

            if (typeLogement == BACK) {
                return;
            }

            if (logementList.size() == 1) {
                System.out.println("Un seul hote trouvé, voulez-vous le validé ? (0 : non, 1 ou plus : oui).");
                if (Menu.scanner.nextInt() > 0) {
                    indexHote = 0;
                } else {
                    return;
                }
            } else {
                System.out.println("Saisissez le numéro d'un hôte entre 0 et " + (listeHotes.size() - 1) + " : ");
                indexHote = Menu.scanner.nextInt();
                if (indexHote >= listeHotes.size() || indexHote < 0) {
                    throw new Exception("Erreur lors de la saisie du numéro de l'hote.");
                }
            }

            hote = GestionHotes.hoteList.get(indexHote);
            hote.afficher();
            System.out.println();

            System.out.print("Adresse : ");
            String adresse = Menu.scanner.next();

            System.out.print("Superficie : ");
            superficie = Menu.scanner.nextInt();

            System.out.print("Tarif journalier : ");
            tarifParNuit = Menu.scanner.nextInt();

            System.out.print("Nombre maximum de voyageurs :");
            voyageurMax = Menu.scanner.nextInt();

            switch (typeLogement) {
                case TYPE_MAISON:
                    System.out.print("superficie du jardin :");
                    int superficieJardin = Menu.scanner.nextInt();

                    System.out.print("Piscine ? (0 non, 1 oui)");
                    boolean possedePiscine = Menu.scanner.nextInt() > 0;

                    logementList.add(new Maison(hote, adresse, tarifParNuit, superficie, voyageurMax, superficieJardin, possedePiscine));
                    System.out.println("Votre maison a été ajouté avec succès");
                    break;
                case TYPE_APPARTEMENT:
                    System.out.print("Saisissez le numéro de l'étage :");
                    int numeroEtage = Menu.scanner.nextInt();

                    System.out.print("Superficie du balcon ? (0 : pas de balcon)");
                    int superficieBalcon = Menu.scanner.nextInt();

                    logementList.add(new Appartement(hote, adresse, tarifParNuit, superficie, voyageurMax, numeroEtage, superficieBalcon));
                    System.out.println("Votre appartement a été ajouté avec succès");
                    break;
            }
        } else {
            System.out.println("Impossible d'ajouter un logement, aucun hote enregistré.");
        }
    }

    static void delete() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer un logement.");

        if (!logementList.isEmpty()) {

            int index = 0;

            if (logementList.size() > 1) {
                System.out.println("Numéro ? (entre 0 et " + (logementList.size() - 1) + ") : ");
                index = Menu.scanner.nextInt();
            } else {
                System.out.println("Un seul logement enregistré.");
            }

            System.out.println("Etes-vous certains de supprimer le logement n°" + index + " (0 : non | plus : oui) : ");
            if (Menu.scanner.nextInt() > 1) {
                logementList.remove(index);
            }
        } else {
            System.out.println("Aucun logement à supprimer.");
        }
    }

    private static void display() {
        if (logementList.size() > 0) {
            for (int i = 0; i < logementList.size(); i++) {
                Logement logement = logementList.get(i);
                System.out.print("* n°" + i + " : ");
                logement.afficher();
            }
        } else {
            System.out.println("Aucun logement enregistré.");
        }
    }

    private static void back() {
        Menu.init();
    }
}
