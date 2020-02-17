package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.reservations.Sejour;
import com.richard.airbnb.models.reservations.SejourCourt;
import com.richard.airbnb.models.reservations.SejourLong;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.MaDate;
import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

public final class GestionReservation extends Gestion {

    static final ArrayList<Reservation> reservationList = Menu.reservationList;
    private static int MAX_NB_NUITS = 60;
    private static int NB_NUIT_POUR_SEJOUR_LONG = 6;

    private GestionReservation() {
    }

    public static void listerReservation() {

        System.out.println("---------- ----------");
        System.out.println("Liste des reservations");

        if (reservationList.size() > 0) {
            for (int i = 0; i < reservationList.size(); i++) {
                Reservation reservation = reservationList.get(i);
                System.out.println("* n°" + i + " : ");
                reservation.afficher();
            }
        } else {
            System.out.println("Aucune reservation enregistrée.");
        }

        System.out.println("Saisir une option :");
        System.out.println("1 : Ajouter une reservation");
        System.out.println("2 : Supprimer une reservation");
        System.out.println("3 : Retour");

        switch (Menu.choix(3)) {
            case AJOUTER:
                try {
                    ajouterReservation();
                } catch (InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Une erreur de saisie est survenue lors de l'ajout d'une réservation.");
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue lors de l'ajout d'une réservation : " + e.getMessage());
                } finally {
                    listerReservation();
                }
                break;
            case SUPPRIMER:
                try {
                    supprimerReservation();
                } catch (InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println("Une erreur de saisie est survenue lors de la suppression d'une réservation.");
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue lors de la suppression d'une réservation : " + e.getMessage());
                } finally {
                    listerReservation();
                }
                break;
            case RETOUR:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterReservation() throws Exception {

        System.out.println("=> Ajouter une réservation.");

        if (GestionVoyageurs.voyageurList.isEmpty()) {
            System.out.println("Aucun voyageur enregistré, réservation d'un sejour impossible.");
        } else if (GestionLogements.logementList.isEmpty()) {
            System.out.println("Aucun logement enregistré, réservation d'un sejour impossible.");
        } else {

            int indexVoyageur;
            int indexLogement;
            Date dateArrivee;

            //  voyageur
            System.out.print("Numéro du voyageur : ");
            final ArrayList<Voyageur> listeVoyageurs = GestionVoyageurs.voyageurList;
            if (listeVoyageurs.size() == 1) {
                indexVoyageur = 0;
                System.out.println("Un seul voyageur trouvé.");
            } else {
                indexVoyageur = Menu.choix(listeVoyageurs.size() - 1);
            }
            Voyageur voyageur = listeVoyageurs.get(indexVoyageur);
            voyageur.afficher();
            System.out.println();

            //  logement
            final ArrayList<Logement> listeLogements = GestionLogements.logementList;
            System.out.print("Numéro du logement : ");
            if (listeLogements.size() == 1) {
                System.out.println("Un seul logement trouvé, voulez-vous le valider ? (0 : non, 1 ou plus : oui).");
                System.out.println(listeLogements.get(0));
                if (Menu.scanner.nextInt() > 0) {
                    indexLogement = 0;
                } else {
                    return;
                }
            } else {
                System.out.println("Saisissez le numéro d'un hôte entre 0 et " + (listeLogements.size() - 1) + " : ");
                indexLogement = Menu.scanner.nextInt();
                if (indexLogement >= listeLogements.size() || indexLogement < 0) {
                    throw new Exception("Erreur lors de la saisie du numéro de l'hote.");
                }
            }
            Logement logement = listeLogements.get(indexLogement);
            logement.afficher();

            //  date arrivée
            System.out.println("Date d'arrivée : ");
            System.out.print("jour : ");
            int day = Menu.choix(MaDate.MAX_DAY);

            System.out.print("mois : ");
            int month = Menu.choix(MaDate.MAX_MONTH);

            System.out.print("année : ");
            int year = Menu.choix(2100);

            dateArrivee = new MaDate(day, month, year);
            System.out.println(dateArrivee);

            System.out.print("Nombre de nuit(s) (max 60 jours) : ");
            int nbNuit = Menu.choix(MAX_NB_NUITS);

            System.out.print("Nombre de voyageurs : ");
            int nbVoyageurs = Menu.choix(logement.getNbVoyageursMax());

            Sejour sejour;

            if (nbNuit > NB_NUIT_POUR_SEJOUR_LONG) {
                //  sejour long
                sejour = new SejourLong(
                        dateArrivee,
                        nbNuit,
                        logement,
                        nbVoyageurs);
            } else {
                //  sejour cours
                sejour = new SejourCourt(
                        dateArrivee,
                        nbNuit,
                        logement,
                        nbVoyageurs
                );
            }

            try {
                Reservation newReservation = new Reservation(
                        sejour,
                        voyageur,
                        new MaDate());
                reservationList.add(newReservation);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void supprimerReservation() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer une réservation.");

        if (!reservationList.isEmpty()) {

            int index = 0;

            if (reservationList.size() > 1) {
                System.out.println("Numéro ? (entre 0 et " + (reservationList.size() - 1) + ") : ");
                index = Menu.choix(reservationList.size());
            } else {
                System.out.println("Une seul réservation enregistré.");
            }

            System.out.println("Etes-vous certains de supprimer la réservation n°" + index + " (0 : non | plus : oui) : ");
            if (Menu.scanner.nextInt() > 1) {
                reservationList.remove(index);
            }
        } else {
            System.out.println("Aucune reservation à supprimer.");
        }
    }
}
