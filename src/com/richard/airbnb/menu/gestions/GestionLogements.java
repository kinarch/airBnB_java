package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Maison;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionLogements extends Gestion {

    private static final ArrayList<Logement> logementList = Menu.logementList;
    private static final int TYPE_MAISON = 1;
    private static final int TYPE_APPARTEMENT = 2;

    private GestionLogements() {
    }

    /**
     * Initialise le menu de la gestion des logements.
     */
    public static void init() {

        System.out.println("---------- ----------");
        System.out.println("# Gestion des Logements");
        Gestion.displayOptions();

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
     * Permet à l'utilisateur d'ajouter un logement a la liste.
     *
     * @throws Exception si l'utilisateur tente une saisie incorrect ou de créer un hote aux attributs impossible.
     */
    private static void add() throws Exception {

        System.out.println("=> Ajouter un logement.");

        final ArrayList<Hote> hoteList = Menu.hoteList;

        if (!hoteList.isEmpty()) {

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

            if (hoteList.size() == 1) {
                System.out.println("Un seul hote trouvé. L'enregister ? (0 : non, 1 ou plus : oui).");
                if (Menu.scanner.nextInt() > 0) {
                    indexHote = 0;
                } else {
                    return;
                }
            } else {
                System.out.println("Numéro de l'hôte entre 0 et " + (hoteList.size() - 1) + " : ");
                indexHote = Menu.scanner.nextInt();
            }

            hote = hoteList.get(indexHote);
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

                    Gestion.add(logementList, new Maison(hote, adresse, tarifParNuit, superficie, voyageurMax, superficieJardin, possedePiscine));
                    break;
                case TYPE_APPARTEMENT:
                    System.out.print("Saisissez le numéro de l'étage :");
                    int numeroEtage = Menu.scanner.nextInt();

                    System.out.print("Superficie du balcon ? (0 : pas de balcon)");
                    int superficieBalcon = Menu.scanner.nextInt();

                    Gestion.add(logementList, new Appartement(hote, adresse, tarifParNuit, superficie, voyageurMax, numeroEtage, superficieBalcon));
                    break;
            }
        } else {
            System.out.println("Impossible d'ajouter un logement, aucun hote enregistré.");
        }
    }

    /**
     * Permet a l'utilisateur de choisir l'index du logement à supprimer de la liste.
     *
     * @throws IndexOutOfBoundsException
     * @throws InputMismatchException
     */
    private static void delete() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer un logement.");
        Gestion.delete(logementList);
    }

    /**
     * Permet a l'utilisateur d'afficher la liste dans la console
     */
    private static void display() {
        System.out.println("=> Afficher les logements.");
        Gestion.display(logementList);
    }

    /**
     * Retour au menu initial.
     */
    protected static void back() {
        System.out.println("=> Retour.");
        Gestion.back();
    }
}
