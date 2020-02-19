package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionVoyageurs extends Gestion {

    private static final ArrayList<Voyageur> voyageurList = Menu.voyageurList;

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

//        if (!voyageurList.isEmpty()) {
//
//            int index = 0;
//
//            if (voyageurList.size() > 1) {
//                System.out.println("Numéro ? (entre 0 et " + (voyageurList.size() - 1) + ") : ");
//                index = Menu.scanner.nextInt();
//            } else {
//                System.out.println("Un seul voyageur enregistré.");
//            }
//
//            System.out.println("Etes-vous certains de supprimer le voyageur n°" + index + " (0 : non | plus : oui) : ");
//            if (Menu.scanner.nextInt() > 1) {
//                voyageurList.remove(index);
//            }
//        } else {
//            System.out.println("Aucun voyageur à supprimer.");
//        }
    }

    /**
     * Permet à l'utilisateur d'afficher la liste dans la console.
     */
    protected static void display() {
        Gestion.display(voyageurList);
//        if (voyageurList.size() > 0) {
//            for (int i = 0; i < voyageurList.size(); i++) {
//                Voyageur voyageur = voyageurList.get(i);
//                System.out.print("* n°" + i + " : ");
//                voyageur.afficher();
//            }
//        } else {
//            System.out.println("Aucun voyageur enregistré.");
//        }
    }

    /**
     * Retour au menu initial.
     */
    protected static void back() {
        Gestion.back();
    }
}
