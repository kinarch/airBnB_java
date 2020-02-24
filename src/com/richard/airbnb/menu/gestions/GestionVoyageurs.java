package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.menu.Menu;
import com.richard.airbnb.tools.AirBnBData;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionVoyageurs extends Gestion {

    private static ArrayList<Voyageur> voyageurList;

    private GestionVoyageurs() {
    }

    /**
     * Initialise le menu de la gestion des voyageurs.
     */
    public static void init() {


        System.out.println("---------- ----------");
        System.out.println("# Gestion des voyageurs");
        Gestion.displayOptions();


        int userInput = Menu.choose(N_OPTIONS);
        if (userInput == BACK) {
            back();
        } else {
            try {

                voyageurList = AirBnBData.getInstance().voyageurList;

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
     * Permet à l'utilisateur d'ajouter un voyageur à la liste.
     *
     * @throws Exception
     */
    protected static void add() throws Exception {

        System.out.println("=> Ajouter un voyageur.");

        String prenom;
        String nom;
        int age;

        System.out.print("Prénom : ");
        prenom = Menu.scanner.next();

        System.out.print("Nom : ");
        nom = Menu.scanner.next();

        System.out.print("Age : ");
        age = Menu.scanner.nextInt();

        Voyageur voyageur = new Voyageur(prenom, nom, age);
        Gestion.add(voyageurList, voyageur);
    }

    /**
     * Permet à l'utlisateur de choisir l'index du voyageur à supprimer de la liste.
     *
     * @throws IndexOutOfBoundsException
     * @throws InputMismatchException
     */
    protected static void delete() throws IndexOutOfBoundsException, InputMismatchException {
        System.out.println("=> Supprimer un voyageur.");
        Gestion.delete(voyageurList);
    }

    /**
     * Permet à l'utilisateur d'afficher la liste dans la console.
     */
    protected static void display() {
        System.out.println("=> Afficher les voyageurs.");
        Gestion.display(voyageurList);
    }

    /**
     * Retour au menu initial.
     */
    protected static void back() {
        System.out.println("=> Retour.");
        Gestion.back();
    }
}
