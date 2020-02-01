package richard.airbnb.menu;

import richard.airbnb.logements.Logement;
import richard.airbnb.logements.Maison;
import richard.airbnb.reservations.Reservation;
import richard.airbnb.utilisateurs.Hote;
import richard.airbnb.utilisateurs.Voyageur;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    static Scanner scanner;
    static final ArrayList<Hote> listeHotes = new ArrayList<Hote>();
    static final ArrayList<Logement> listeLogements = new ArrayList<>();
    static final ArrayList<Voyageur> listeVoyageurs = new ArrayList<>();
    static final ArrayList<Reservation> listeReservations = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue chez AirBnB");

        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        //  EXEMPLES
        listeHotes.add(new Hote("truc","bidule", 20, 48));
        listeLogements.add(new Maison(listeHotes.get(0), "quelque part", 40, 200, 5, 200, true));
        listeVoyageurs.add(new Voyageur("voyageur", "du temp", 30));
        listeVoyageurs.add(new Voyageur("Doctor", "Who", 100));

        listerMenu();

        scanner.close();
    }

    /**
     * Permet à l'utilisateur de choisir un entier compris entre 1 et maxValue
     * Retourne ce choix.
     *
     * @param maxValue int
     * @return userInput int
     */
    static int choix(int maxValue) {

        int minValue = maxValue == 1 ? 0 : 1;
        int userInput = 0;
        boolean isAcceptedValue = false;

        do {
            try {
                System.out.println("Entrez un chiffre entre " + minValue + " et " + maxValue + " : ");
                userInput = scanner.nextInt();
                isAcceptedValue = (userInput <= maxValue && userInput >= minValue);
            } catch (Exception e) {
                String s = scanner.next();
                System.out.println("Désolé, '" + s + "' est une valeur incorecte.");
            }
        } while (!isAcceptedValue);

        return userInput;
    }

    static void listerMenu() throws Exception {

        final int maxOptionValue = 5;

        //  console list display
        System.out.println("Saisir une option : ");
        System.out.println("1 : Liste des hotes");
        System.out.println("2 : Liste des logements");
        System.out.println("3 : Liste des voyageurs");
        System.out.println("4 : Liste des reservations");
        System.out.println("5 : Fermer le programme");

        switch (choix(maxOptionValue)) {
            case 1:
                GestionHotes.listerHotes();
                break;
            case 2:
                GestionLogements.listerLogement();
                break;
            case 3:
                GestionVoyageurs.listerVoyageur();
                break;
            case 4:
                GestionReservation.listerReservation();
                break;
            case 5:
                System.out.println("A bientôt.");
                break;
        }
    }
}
