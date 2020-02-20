package com.richard.airbnb.menu;


import com.richard.airbnb.menu.gestions.GestionHotes;
import com.richard.airbnb.menu.gestions.GestionLogements;
import com.richard.airbnb.menu.gestions.GestionReservation;
import com.richard.airbnb.menu.gestions.GestionVoyageurs;
import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.ASCIIArtGenerator;
import com.richard.airbnb.tools.AirBnBXMLParser;
import com.richard.airbnb.tools.MyListComparator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;

public final class Menu {

    private static final String TITLE = "AirB&B";
    public static Scanner scanner;

    //  la liste pour chaque gestion
    public static final ArrayList<Hote> hoteList = new ArrayList<>();
    public static final ArrayList<Logement> logementList = new ArrayList<>();
    public static final ArrayList<Voyageur> voyageurList = new ArrayList<>();
    public static final ArrayList<Reservation> reservationList = new ArrayList<>();

    private Menu() {
    }

    /*
        TODO ...
        -   Faire la methode qui parse le XML avec XPath ? Recursive ?
        -   Faire la methode qui écrit dans un fichier texte les réservations.
     */

    /**
     * Methode principale a l'execution de l'application du Menu
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in).useDelimiter("\n");

        init();

        scanner.close();
    }


    /**
     * Initialise le menu
     */
    public static void init() {

        //  Parser le fichier xml et préparer les listes
        try {
            AirBnBXMLParser.parseListDOM("res/logements.xml", hoteList, logementList);
            /*
                exemple voyageurs
             */
            voyageurList.add(new Voyageur("Voyageur", "du Temp", 30));
            voyageurList.add(new Voyageur("Doctor", "Who", 100));
        } catch (Exception ex) {
            out.println("[Error] " + ex.getMessage());
            ex.printStackTrace();
        }

        try {
            ASCIIArtGenerator.printTextArt(TITLE, ASCIIArtGenerator.ART_SIZE_SMALL);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Optional<Appartement> a = GestionLogements.getLogementByName("Truc");
//        a.ifPresent(Appartement::afficher);
//
//        MyListComparator<Hote> myListComparator = new MyListComparator<>(hoteList);
//        try {
//            out.println(myListComparator.getHigher());
//            out.println(myListComparator.getLower());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        displayOptions();
    }

    public static void displayOptions() {

        out.println("# MENU");
        out.println("Saisir une option : ");
        out.println("1 : Liste des hotes");
        out.println("2 : Liste des logements");
        out.println("3 : Liste des voyageurs");
        out.println("4 : Liste des reservations");
        out.println("5 : Fermer le programme");

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
                out.println("A bientôt.");
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
                out.println(s + " est une valeur incorecte.");
            }
        } while (userInput < 1 || userInput > maxValue);

        return userInput;
    }

    //  TODO ... Reflechir pour écrire dans le fichier texte (réunir les bonnes informations)

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
}
