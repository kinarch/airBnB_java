package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.menu.Menu;
import com.richard.airbnb.models.utilisateurs.Hote;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionHotes extends Gestion {

    private static final ArrayList<Hote> hoteList = Menu.hoteList;

    private GestionHotes() {
    }

    /**
     * Initialise le menu de la gestion des hotes
     */
    public static void init() {

        System.out.println("---------- ----------");
        System.out.println("# Gestion des hotes");
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
     * Permet a l'utilisateur d'ajouter un hote à la liste
     *
     * @throws Exception si l'utilisateur tente une saisie incorrect ou de créer un hote aux attributs impossible.
     */
    protected static void add() throws Exception {

        System.out.println("=> Ajouter un hôte.");

        String prenom;
        String nom;
        int age;
        int delaiReponse;

        System.out.print("Prénom : ");
        prenom = Menu.scanner.next();

        System.out.print("Nom : ");
        nom = Menu.scanner.next();

        System.out.print("Age : ");
        age = Menu.scanner.nextInt();

        System.out.print("Delai de réponse : ");
        delaiReponse = Menu.scanner.nextInt();

        Hote hote = new Hote(prenom, nom, age, delaiReponse);
        Gestion.add(hoteList, hote);
    }

    /**
     * Permet à l'utilisateur de choisir l'index de l'hote à supprimer de la liste.
     *
     * @throws IndexOutOfBoundsException si l'utilisateur tente d'entrer un numéro en dehors des index la liste
     * @throws InputMismatchException    si l'utilisateur tente une saisie alphabétique.
     */
    protected static void delete() throws IndexOutOfBoundsException, InputMismatchException {
        System.out.println("=> Supprimer un hôte.");
        Gestion.delete(hoteList);
    }

    /**
     * Permet à l'utilisateur d'afficher la liste dans la console
     */
    protected static void display() {
        System.out.println("=> Affichage des hotes.");
        Gestion.display(hoteList);
    }

    /**
     * Retour au menu initial.
     */
    protected static void back() {
        System.out.println("=> Retour.");
        Gestion.back();
    }
}
