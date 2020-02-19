package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static java.lang.System.out;

abstract class Gestion {

    //  OPTIONS
    protected static final int N_OPTIONS = 4;
    protected static final int ADD = 1;
    protected static final int DELETE = 2;
    protected static final int DISPLAY = 3;
    protected static final int BACK = 4;

    /**
     * Affichage des options en console
     */
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
     * @throws IndexOutOfBoundsException
     * @throws InputMismatchException
     */
    protected static void delete(ArrayList<?> list) throws IndexOutOfBoundsException, InputMismatchException {
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
                Object deletedItem = list.remove(index);
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
    protected static void display(ArrayList<?> list) {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
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