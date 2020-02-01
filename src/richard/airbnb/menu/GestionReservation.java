package richard.airbnb.menu;

import richard.airbnb.outils.MaDate;
import richard.airbnb.logements.Logement;
import richard.airbnb.reservations.Reservation;
import richard.airbnb.reservations.Sejour;
import richard.airbnb.reservations.SejourCourt;
import richard.airbnb.reservations.SejourLong;
import richard.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;
import java.util.Date;

public class GestionReservation {

    private static ArrayList<Reservation> listeReservations = Menu.listeReservations;
    private static int MAX_VALUE_OPTION = 3;
    private static int MAX_NB_NUITS = 60;
    private static int NB_NUIT_POUR_SEJOUR_LONG = 6;

    static void listerReservation() throws Exception {

        System.out.println("--------------------");
        if (listeReservations.size() > 0) {
            for (int i = 0; i < listeReservations.size(); i++) {
                Reservation reservation = listeReservations.get(i);
                System.out.println("n°" + i + " : ");
                reservation.afficher();
            }
        } else {
            System.out.println("Aucune reservation enregistrée.");
        }
        System.out.println("Saisir une option :");
        System.out.println("1 : Ajouter une reservation");
        System.out.println("2 : Supprimer une reservation");
        System.out.println("3 : Retour");

        switch (Menu.choix(MAX_VALUE_OPTION)) {
            case 1:
                ajouterReservation();
                break;
            case 2:
                supprimerReservation();
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterReservation() throws Exception {

        if (Menu.listeVoyageurs.isEmpty()) {
            System.out.println("Aucun voyageur enregistré, réservation d'un sejour impossible.");
        } else {

            System.out.print("Numéro du voyageur : ");
            int indexVoyageur;
            if (Menu.listeVoyageurs.size() == 1) {
                indexVoyageur = 0;
                System.out.println("Un seul voyageur trouvé.");
            } else {
                indexVoyageur = Menu.choix(Menu.listeVoyageurs.size() - 1);
            }
            Voyageur voyageur = Menu.listeVoyageurs.get(indexVoyageur);
            voyageur.afficher();
            System.out.println();

            System.out.print("Numéro du logement : ");
            int indexLogement;
            if (Menu.listeLogements.size() == 1) {
                indexLogement = 0;
                System.out.println("Un seul logement trouvé.");
            } else {
                indexLogement = Menu.choix(Menu.listeLogements.size() - 1);
            }
            Logement logement = Menu.listeLogements.get(indexLogement);
            logement.afficher();

            System.out.println("Date d'arrivée : ");
            System.out.print("jour : ");
            int day = Menu.choix(MaDate.maxDay);
            System.out.print("mois : ");
            int month = Menu.choix(MaDate.maxMonth);
            System.out.print("année : ");
            int year = Menu.choix(2100);
            Date dateArrivee = new MaDate(day, month, year);
            System.out.println(dateArrivee);

            System.out.print("Nombre de nuit(s) (max 60 jours) : ");
            int nbNuit = Menu.choix(MAX_NB_NUITS);

            System.out.print("Nombre de voyageurs : ");
            int nbVoyageurs = Menu.choix(logement.getNbVoyageursMax());

            Sejour sejour;

            if (nbNuit < NB_NUIT_POUR_SEJOUR_LONG) {
                //  sejour cours
                sejour = new SejourCourt(
                        dateArrivee,
                        nbNuit,
                        logement,
                        nbVoyageurs
                );
            } else {
                //  sejour long
                sejour = new SejourLong(
                        dateArrivee,
                        nbNuit,
                        logement,
                        nbVoyageurs);
            }

            try {
                Reservation newReservation = new Reservation(
                        sejour,
                        voyageur,
                        new MaDate());
                listeReservations.add(newReservation);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        listerReservation();
    }

    static void supprimerReservation() throws Exception {

        if (!listeReservations.isEmpty()) {
            System.out.println("Numéro de la reservation à supprimer :");
            int indexReservation = 0;
            if (Menu.listeHotes.size() > 1) {
                indexReservation = Menu.choix(Menu.listeReservations.size());
            }
            System.out.println("Etes-vous certains de supprimer la reservation n°" + indexReservation);
            System.out.print("(0 : non, 1 : oui)");
            int estSur = Menu.choix(1);
            if (estSur == 1) {
                listeReservations.remove(indexReservation);
            }
        } else {
            System.out.println("Aucune reservation à supprimer.");
        }

        listerReservation();
    }
}
