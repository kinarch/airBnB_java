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

    private static final ArrayList<Reservation> reservationList = Menu.reservationList;
    private static int MAX_NB_NUITS = 60;
    private static int NB_NUIT_POUR_SEJOUR_LONG = 6;

    private GestionReservation() {
    }

    /**
     * Initialise le menu de la gestion des réservation.
     */
    public static void init() {

        System.out.println("---------- ----------");
        System.out.println("Liste des reservations");
        System.out.println("Saisir une option :");
        System.out.println(ADD + " : Ajouter un reservation");
        System.out.println(DELETE + " : Supprimer un reservation");
        System.out.println(DISPLAY + " : Afficher la liste");
        System.out.println(BACK + " : Retour");

        int userInput = Menu.choose(N_OPTIONS);
        if (userInput == BACK) {
            back();
        } else {
            try {
                switch (userInput) {
                    case ADD:
                        add();
                        break;
                    case DELETE:
                        delete();
                        break;
                    case DISPLAY:
                        display();
                        break;
                }
            } catch (InputMismatchException ex) {
                String input = Menu.scanner.next();
                System.out.println("Une erreur de saisie est survenue (input : " + input + ").");
            } catch (Exception ex) {
                System.out.println("Une erreur est survenue : " + ex.getMessage());
            } finally {
                init();
            }
        }
    }

    /**
     * Permet à l'utilisateur d'ajouter une réservation à la liste.
     *
     * @throws Exception
     */
    private static void add() throws Exception {

        System.out.println("=> Ajouter une réservation.");

        final ArrayList<Logement> logementList = Menu.logementList;
        final ArrayList<Voyageur> voyageurList = Menu.voyageurList;

        if (voyageurList.isEmpty()) {
            System.out.println("Aucun voyageur enregistré, réservation d'un sejour impossible.");
            return;
        } else if (logementList.isEmpty()) {
            System.out.println("Aucun logement enregistré, réservation d'un sejour impossible.");
            return;
        }

        int indexVoyageur;
        int indexLogement;
        Date dateArrivee;

        //  voyageur
        System.out.print("Numéro du voyageur : ");
        if (voyageurList.size() == 1) {
            indexVoyageur = 0;
            System.out.println("Un seul voyageur trouvé.");
        } else {
            System.out.println("Numéro du voyageur entre 0 et " + (voyageurList.size() - 1) + " : ");
            indexVoyageur = Menu.scanner.nextInt();
        }

        Voyageur voyageur = voyageurList.get(indexVoyageur);
        voyageur.afficher();
        System.out.println();

        //  logement
        System.out.print("Numéro du logement : ");
        if (logementList.size() == 1) {
            System.out.println("Un seul logement trouvé. L'enregister ? (0 : non, 1 ou plus : oui).");
            System.out.println(logementList.get(0));
            if (Menu.scanner.nextInt() > 0) {
                indexLogement = 0;
            } else {
                return;
            }
        } else {
            System.out.println("Numéro du logement entre 0 et " + (logementList.size() - 1) + " : ");
            indexLogement = Menu.scanner.nextInt();
        }

        Logement logement = logementList.get(indexLogement);
        logement.afficher();

        //  date arrivée
        System.out.println("Date d'arrivée : ");
        System.out.print("jour : ");
        int day = Menu.choose(MaDate.MAX_DAY);

        System.out.print("mois : ");
        int month = Menu.choose(MaDate.MAX_MONTH);

        System.out.print("année : ");
        int year = Menu.choose(2100);

        dateArrivee = new MaDate(day, month, year);
        System.out.println(dateArrivee);

        System.out.print("Nombre de nuit(s) (max 60 jours) : ");
        int nbNuit = Menu.choose(MAX_NB_NUITS);

        System.out.print("Nombre de voyageurs : ");
        int nbVoyageurs = Menu.choose(logement.getNbVoyageursMax());

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

    /**
     * Permet à l'utilisateur de choisir l'index de la réservation à supprimer de la liste.
     *
     * @throws IndexOutOfBoundsException
     * @throws InputMismatchException
     */
    private static void delete() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer une réservation.");

        if (!reservationList.isEmpty()) {

            int index = 0;

            if (reservationList.size() > 1) {
                System.out.println("Numéro ? (entre 0 et " + (reservationList.size() - 1) + ") : ");
                index = Menu.choose(reservationList.size());
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

    /**
     * Permet à l'utilisateur d'afficher la liste dans la console.
     */
    private static void display() {
        if (reservationList.size() > 0) {
            for (int i = 0; i < reservationList.size(); i++) {
                Reservation reservation = reservationList.get(i);
                System.out.println("* n°" + i + " : ");
                reservation.afficher();
            }
        } else {
            System.out.println("Aucune reservation enregistrée.");
        }
    }

    /**
     * Retour au menu initial.
     */
    private static void back() {
        Menu.init();
    }
}
