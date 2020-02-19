package com.richard.airbnb.menu;


import com.richard.airbnb.menu.gestions.GestionHotes;
import com.richard.airbnb.menu.gestions.GestionLogements;
import com.richard.airbnb.menu.gestions.GestionReservation;
import com.richard.airbnb.menu.gestions.GestionVoyageurs;
import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Maison;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.ASCIIArtGenerator;
import com.richard.airbnb.tools.AirBnBXMLParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Menu {

    private static final String TITLE = "AirBnB";
    public static Scanner scanner;

    //  la liste pour chaque gestion
    public static ArrayList<Hote> hoteList = new ArrayList<>();
    public static ArrayList<Logement> logementList = new ArrayList<>();
    public static ArrayList<Voyageur> voyageurList = new ArrayList<>();
    public static ArrayList<Reservation> reservationList = new ArrayList<>();

    private Menu() {
    }


    /*
        TODO ...
        -   Faire la methode qui parse le XML avec XPath ? Recursive ?
        -   Faire la methode qui écrit dans un fichier texte les réservations.
     */

    /**
     * [ M A I N ]
     * Methode principale a l'execution de l'application du Menu
     *
     * @param args
     */
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        //  Parser le fichier xml et préparer les listes
        try {
            AirBnBXMLParser.parseListDOM("res/logements.xml", hoteList, logementList);
            /*
                exemple voyageurs
             */
            voyageurList.add(new Voyageur("Voyageur", "du Temp", 30));
            voyageurList.add(new Voyageur("Doctor", "Who", 100));
        } catch (Exception ex) {
            System.out.println("[Error] " + ex.getMessage());
            ex.printStackTrace();
        }

        try {
            ASCIIArtGenerator.printTextArt("A i r B & B", 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Maison maison = getMaisonByName("Maison 1234");
        Appartement appartement = getAppartementByName("Neverland");

        Maison logement = (Maison) getLogementByName("Maison 1");
        Maison maison2 = getLogementByNameWithGenericity("Maison 2");
        Appartement appartement2 = getLogementByNameWithGenericity("Appartement 1234");

        System.out.println();
        System.out.println("MAISON 1 : getMaisonByName(Maison 1)");
        if (maison != null) {
            maison.afficher();
        } else {
            System.out.println("Pas de maison");
        }

        System.out.println();
        System.out.println("MAISON 1 : getLogementByName(Maison 1)");
        if (logement != null) {
            logement.afficher();
        } else {
            System.out.println("Pas de logement");
        }


        System.out.println();
        System.out.println("APP 1 : getAppartementByName(Neverland)");
        if (appartement != null) {
            appartement.afficher();
        } else {
            System.out.println("Pas d'appartement");
        }

        System.out.println();
        System.out.println("MAISON 2 : getLogementByNameWithGenericity(Maison 2)");
        if (maison2 != null) {
            maison2.afficher();
        } else {
            System.out.println("Pas de logement générique");
        }

        System.out.println();
        System.out.println("APP 2 : getLogementByNameWithGenericity(Appartement 2)");
        if (appartement2 != null) {
            appartement2.afficher();
        } else {
            System.out.println("Pas de logement générique");
        }

        System.out.println("********** " + TITLE + " **********");
        init();
        scanner.close();
    }

    /**
     * Initialise le menu
     */
    public static void init() {

        //  console list display
        System.out.println("# MENU");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Liste des hotes");
        System.out.println("2 : Liste des logements");
        System.out.println("3 : Liste des voyageurs");
        System.out.println("4 : Liste des reservations");
        System.out.println("5 : Fermer le programme");

        switch (choose(5)) {
            case 1:
                GestionHotes.init();
                break;
            case 2:
                GestionLogements.init();
                break;
            case 3:
                GestionVoyageurs.init();
                break;
            case 4:
                GestionReservation.init();
                break;
            case 5:
                System.out.println("A bientôt.");
                break;
        }
    }


    /**
     * Permet à l'utilisateur de choisir un entier compris entre 1 et maxValue.
     * Retourne ce choix.
     *
     * @param maxValue int
     * @return userInput int
     */
    public static int choose(int maxValue) {

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

    /*
        TODO ... Ecrire dans un fichier texte
     */

    /**
     * Ecrit un nouveau fichier texte avec les réservations.
     *
     * @param filePath le chemin du fichier texte.
     * @param append   si le fichier doit être écraser si celui ci existe.
     */
    public static void writeReservation(String filePath, boolean append) {

        String nVoyageurTitle = "Numéro du Voyageur : ";
        String nLogementTitle = "Numéro du Logement : ";
        String dateArriveTitle = "Date d'arrivée (DD/MM/YYYY) : ";
        String nNuitTitle = "Nombre de nuits : ";
        String nPersonneTitle = "Nombre de personnes : ";

        try {

            FileWriter writer = new FileWriter(filePath, append);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Reservation r : reservationList) {
                bufferedWriter.write(nVoyageurTitle);
                bufferedWriter.write(nLogementTitle);
                bufferedWriter.write(dateArriveTitle);
                bufferedWriter.write(nNuitTitle);
                bufferedWriter.write(nPersonneTitle);
//                bufferedWriter.write("\r\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecrit un nouveau fichier texte avec les réservations.
     * Si le fichier existe, l'écrase.
     */
    public static void writeReservation(String filePath) {
        writeReservation(filePath, false);
    }

    /*
        TODO ... GENERICITY
     */

    public static Maison getMaisonByName(String name) {
        for (Logement l : logementList) {
            if (l.getNom().equals(name) && l instanceof Maison) {
                return (Maison) l;
            }
        }
        return null;
    }

    public static Appartement getAppartementByName(String name) {
        for (Logement l : logementList) {
            if (l.getNom().equals(name) && l instanceof Appartement) {
                return (Appartement) l;
            }
        }
        return null;
    }

    public static Logement getLogementByName(String name) {
        for (Logement l : logementList) {
            if (l.getNom().equals(name)) {
                return l;
            }
        }
        return null;
    }

    public static <T extends Logement> T getLogementByNameWithGenericity(String name) {
        for (Logement l : logementList) {
            if (l.getNom().equals(name)) {
                return (T) l;
            }
        }
        return null;
    }
}
