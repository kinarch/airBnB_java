package com.richard.airbnb.menu;


import com.richard.airbnb.menu.gestions.GestionHotes;
import com.richard.airbnb.menu.gestions.GestionLogements;
import com.richard.airbnb.menu.gestions.GestionReservation;
import com.richard.airbnb.menu.gestions.GestionVoyageurs;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.AirBnBXMLParser;


import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static Scanner scanner;
    public static ArrayList<Hote> hoteList = new ArrayList<>();
    public static ArrayList<Logement> logementList = new ArrayList<>();
    public static ArrayList<Voyageur> voyageurList = new ArrayList<>();
    public static ArrayList<Reservation> reservationList = new ArrayList<>();

    private Menu() {
    }

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        try {
            AirBnBXMLParser.parseList("res/logements.xml", hoteList, logementList);
            /*
                exemple voyageurs
             */
            voyageurList.add(new Voyageur("Voyageur", "du Temp", 30));
            voyageurList.add(new Voyageur("Doctor", "Who", 100));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //  Affichage en console

        System.out.println("HOTES LIST");
        for (Hote h : hoteList) {
            System.out.print("* ");
            h.afficher();
            System.out.println();
        }

        System.out.println();

        System.out.println("LOGEMENTS LIST");
        for (Logement l : logementList) {
            System.out.print("* ");
            l.afficher();
            System.out.println();
        }

        System.out.println();

        System.out.println("Bienvenue chez AirBnB");
        listerMenu();

        scanner.close();
    }


    /**
     * Permet à l'utilisateur de choisir un entier compris entre 1 et maxValue.
     * Retourne ce choix.
     *
     * @param maxValue int
     * @return userInput int
     */
    public static int choix(int maxValue) {

        int userInput = 0;

        do {
            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                String s = scanner.next();
                System.out.println(s + " est une valeur incorecte.");
            }
        } while (userInput < 1 || userInput > maxValue);

        return userInput;
    }

    public static void listerMenu() {

        //  console list display
        System.out.println("Saisir une option : ");
        System.out.println("1 : Liste des hotes");
        System.out.println("2 : Liste des logements");
        System.out.println("3 : Liste des voyageurs");
        System.out.println("4 : Liste des reservations");
        System.out.println("5 : Fermer le programme");

        switch (choix(5)) {
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
