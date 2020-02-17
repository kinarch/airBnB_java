package com.richard.airbnb.menu.gestion;

import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class GestionHotes extends Gestion {

    public static final ArrayList<Hote> listeHotes = new ArrayList<>();

    private GestionHotes() {
    }

    /**
     * Menu des hotes
     */
    public static void listerHotes() {

        System.out.println("---------- ----------");
        System.out.println("Liste des hotes");

        if (listeHotes.size() > 0) {
            for (int i = 0; i < listeHotes.size(); i++) {
                Hote hote = listeHotes.get(i);
                System.out.print("* n°" + i + " : ");
                hote.afficher();
                System.out.println();
            }
        } else {
            System.out.println("Aucun hote enregistré.");
        }

        System.out.println("Saisir une option :");
        System.out.println("1 : Ajouter un hote");
        System.out.println("2 : Supprimer un hote");
        System.out.println("3 : Retour");

        try {
            switch (Menu.choix(3)) {
                case AJOUTER:
                    ajouterHote();
                    break;
                case SUPPRIMER:
                    supprimerHote();
                    break;
                case RETOUR:
                    Menu.listerMenu();
                    break;
            }
        } catch (InputMismatchException ex) {
            String error = Menu.scanner.next();
            System.out.println("Une erreur de saisie est survenue (" + error + ").");
        } catch (Exception ex) {
            System.out.println("Une erreur est survenue : " + ex.getMessage());
        } finally {
            listerHotes();
        }
    }

    /**
     * @throws Exception si l'utilisateur tente une saisie incorrect ou de créer un hote aux attributs impossible.
     */
    static void ajouterHote() throws Exception {

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
        listeHotes.add(newHote);
    }

    /**
     * Supprimer dans la liste.
     *
     * @throws IndexOutOfBoundsException si l'utilisateur tente d'entrer un numéro en dehors des index la liste
     * @throws InputMismatchException    si l'utilisateur tente une saisie alphabétique.
     */
    static void supprimerHote() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer un hôte.");

        if (!listeHotes.isEmpty()) {

            int index = 0;

            if (listeHotes.size() > 1) {
                System.out.println("Numéro ? (entre 0 et " + (listeHotes.size() - 1) + ") : ");
                index = Menu.scanner.nextInt();
            } else {
                System.out.println("Un seul hote enregistré.");
            }

            System.out.println("Etes-vous certains de supprimer l'hote n°" + index + " (0 : non | plus : oui) : ");
            if (Menu.scanner.nextInt() > 1) {
                listeHotes.remove(index);
            }
        } else {
            System.out.println("Aucun hote à supprimer.");
        }
    }
}
