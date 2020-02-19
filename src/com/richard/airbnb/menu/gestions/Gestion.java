package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static java.lang.System.out;

abstract class Gestion {

    //  OPTIONS
    static final int N_OPTIONS = 4;
    static final int ADD = 1;
    static final int DELETE = 2;
    static final int DISPLAY = 3;
    static final int BACK = 4;

    protected static void displayOptions() {
        out.println("Saisir une option :");
        out.println(ADD + " : Ajouter");
        out.println(DELETE + " : Supprimer");
        out.println(DISPLAY + " : Afficher");
        out.println(BACK + " : Retour");
    }

    /**
     * Ajoute un élément à la liste
     *
     * @param list
     * @param item
     * @param <T>
     */
    protected static <T> void add(ArrayList<T> list, T item) {
        list.add(item);
    }

    /**
     * Supprime un éléments de la liste
     *
     * @param list
     * @param <T>
     * @throws IndexOutOfBoundsException
     * @throws InputMismatchException
     */
    protected static <T> void delete(ArrayList<T> list) throws IndexOutOfBoundsException, InputMismatchException {
        if (!list.isEmpty()) {
            int index = 0;
            if (list.size() > 1) {
                out.println("Numéro ? (entre 0 et " + (list.size() - 1) + ") : ");
                index = Menu.scanner.nextInt();
            } else {
                out.println("Une seul donnée enregistrée.");
            }
            out.println("Supprimer le numéro " + index + " (0 : non | plus : oui) ?");
            if (Menu.scanner.nextInt() >= 1) {
                T deletedItem = list.remove(index);
                out.println("Suppression du numéro " + index + " :");
                out.println(deletedItem);
            } else {
                out.println("Action annulée.");
            }
        } else {
            out.println("Rien à supprimer.");
        }
    }

    /**
     * Affichage de la liste en console.
     *
     * @param list - la liste générique
     * @param <T>  - Generique type
     */
    protected static <T> void display(ArrayList<T> list) {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                T item = list.get(i);
                out.print("* n°" + i + " : ");
                out.println(item.toString());
            }
        } else {
            out.println("Rien à afficher.");
        }
    }

    /**
     * Retour au meni initial.
     */
    protected static void back() {
        Menu.init();
    }
}