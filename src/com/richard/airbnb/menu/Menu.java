package com.richard.airbnb.menu;


import com.richard.airbnb.menu.gestions.GestionHotes;
import com.richard.airbnb.menu.gestions.GestionLogements;
import com.richard.airbnb.menu.gestions.GestionReservation;
import com.richard.airbnb.menu.gestions.GestionVoyageurs;
import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Personne;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.ASCIIArtGenerator;
import com.richard.airbnb.tools.AirBnBXMLParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public final class Menu {

    private static final String TITLE = "AirB&B";
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
            ASCIIArtGenerator.printTextArt(TITLE, ASCIIArtGenerator.ART_SIZE_SMALL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<Appartement> a = getLogementByName("Neverland12");
        a.ifPresent(Appartement::afficher);
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

    //  TODO ... Généricité

    /**
     * Retour un logement de la liste en fonction de son nom.
     *
     * @param name - le nom du logement.
     * @param <T>  - Type générique hérité de Logement.
     * @return un logement de son type.
     */
    public static <T extends Logement> Optional<T> getLogementByName(String name) {
        T logement = null;
        for (Logement l : logementList) {
            if (l.getNom().equals(name)) {
                logement = (T) l;
                break;
            }
        }
        return Optional.ofNullable(logement);
    }

    /**
     * @param name    - nom.
     * @param surname - prenom.
     * @param <T>     - Type générique hérité de Personne.
     * @return une personne de son type
     */
    public static <T extends Personne> Optional<T> getPersonneByNames(String name, String surname, ArrayList<Personne> list) {
        T personne = null;
        for (Personne p : list) {
            if (p.getNom().equals(name) && p.getPrenom().equals(surname)) {
                personne = (T) p;
                break;
            }
        }
        return Optional.ofNullable(personne);
    }
}
