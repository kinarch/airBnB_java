package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.menu.Menu;
import com.richard.airbnb.models.utilisateurs.Hote;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionHotes extends Gestion {

    public static final ArrayList<Hote> hoteList = Menu.hoteList;

    private GestionHotes() {
    }

    /**
     * Initialise le menu de la gestion des hotes
     */
    public static void init() {

        System.out.println("---------- ----------");
        System.out.println("Gestion des hotes");
//        GestionHotes g = new GestionHotes();

        System.out.println("Saisir une option :");
        System.out.println(ADD + " : Ajouter un hote");
        System.out.println(DELETE + " : Supprimer un hote");
        System.out.println(DISPLAY + " : Afficher la liste");
        System.out.println(BACK + " : Retour");

        try {
            switch (Menu.choose(N_OPTIONS)) {
                case ADD:
                    add();
                    break;
                case DELETE:
                    delete();
                    break;
                case DISPLAY:
                    display();
                    break;
                case BACK:
                    back();
                    break;
            }
        } catch (InputMismatchException ex) {
            String error = Menu.scanner.next();
            System.out.println("Une erreur de saisie est survenue (" + error + ").");
        } catch (Exception ex) {
            System.out.println("Une erreur est survenue : " + ex.getMessage());
        } finally {
            init();
        }
    }

    /**
     * Permet a l'utilisateur d'ajouter un hote à la liste
     *
     * @throws Exception si l'utilisateur tente une saisie incorrect ou de créer un hote aux attributs impossible.
     */
    static void add() throws Exception {

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

        Hote newHote = new Hote(prenom, nom, age, delaiReponse);
        hoteList.add(newHote);
    }

    /**
     * Permet à l'utilisateur de choisir l'index de l'hote à supprimer de la liste.
     *
     * @throws IndexOutOfBoundsException si l'utilisateur tente d'entrer un numéro en dehors des index la liste
     * @throws InputMismatchException    si l'utilisateur tente une saisie alphabétique.
     */
    static void delete() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer un hôte.");
        if (!hoteList.isEmpty()) {
            int index = 0;
            if (hoteList.size() > 1) {
                System.out.println("Numéro ? (entre 0 et " + (hoteList.size() - 1) + ") : ");
                index = Menu.scanner.nextInt();
            } else {
                System.out.println("Un seul hote enregistré.");
            }
            System.out.println("Etes-vous certains de supprimer l'hote n°" + index + " (0 : non | plus : oui) : ");
            if (Menu.scanner.nextInt() > 1) {
                hoteList.remove(index);
            }
        } else {
            System.out.println("Aucun hote à supprimer.");
        }
    }

    /**
     * Permet à l'utilisateur d'afficher la liste dans la console
     */
    private static void display() {
        if (hoteList.size() > 0) {
            for (int i = 0; i < hoteList.size(); i++) {
                Hote hote = hoteList.get(i);
                System.out.print("* n°" + i + " : ");
                hote.afficher();
                System.out.println();
            }
        } else {
            System.out.println("Aucun hote enregistré.");
        }
    }

    /**
     * Retour au menu initial.
     */
    private static void back() {
        Menu.init();
    }


//
//    @Override
//    public void add(ArrayList<Object> list) throws Exception {
//
//        System.out.println("=> Ajouter un hôte.");
//
//        String prenom;
//        String nom;
//        int age;
//        int delaiReponse;
//
//        System.out.print("Prénom : ");
//        prenom = Menu.scanner.next();
//
//        System.out.print("Nom : ");
//        nom = Menu.scanner.next();
//
//        System.out.print("Age : ");
//        age = Menu.scanner.nextInt();
//
//        System.out.print("Delai de réponse : ");
//        delaiReponse = Menu.scanner.nextInt();
//
//        Hote newHote = new Hote(prenom, nom, age, delaiReponse);
//    }
//
//    @Override
//    public void delete(ArrayList<Object> list) {
//        System.out.println("=> Supprimer un hôte.");
//        super.delete(list);
//    }
//
//    @Override
//    public void display() {
//
//    }
//
//    @Override
//    public void back() {
//
//    }
}
