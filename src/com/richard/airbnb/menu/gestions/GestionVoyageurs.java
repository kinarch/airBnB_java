package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public final class GestionVoyageurs extends Gestion {

    public static final ArrayList<Voyageur> voyageurList = Menu.voyageurList;

    private GestionVoyageurs() {
    }

    public static void listerVoyageur() {


        System.out.println("---------- ----------");
        System.out.println("Liste des voyageurs");

        if (voyageurList.size() > 0) {
            for (int i = 0; i < voyageurList.size(); i++) {
                Voyageur voyageur = voyageurList.get(i);
                System.out.print("* n°" + i + " : ");
                voyageur.afficher();
                System.out.println();
            }
        } else {
            System.out.println("Aucun voyageur enregistré.");
        }

        System.out.println("Saisir une option :");
        System.out.println("1 : Ajouter un voyageur");
        System.out.println("2 : Supprimer un voyageur");
        System.out.println("3 : Retour");

        try {
            switch (Menu.choix(3)) {
                case AJOUTER:
                    ajouterVoyageur();
                    listerVoyageur();
                    break;
                case SUPPRIMER:
                    supprimerVoyageur();
                    listerVoyageur();
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
        }
    }

    static void ajouterVoyageur() throws Exception {

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

        Voyageur newVoyageur = new Voyageur(prenom, nom, age);
        voyageurList.add(newVoyageur);
    }

    static void supprimerVoyageur() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("=> Supprimer un voyageur.");

        if (!voyageurList.isEmpty()) {

            int index = 0;

            if (voyageurList.size() > 1) {
                System.out.println("Numéro ? (entre 0 et " + (voyageurList.size() - 1) + ") : ");
                index = Menu.scanner.nextInt();
            } else {
                System.out.println("Un seul voyageur enregistré.");
            }

            System.out.println("Etes-vous certains de supprimer le voyageur n°" + index + " (0 : non | plus : oui) : ");
            if (Menu.scanner.nextInt() > 1) {
                voyageurList.remove(index);
            }
        } else {
            System.out.println("Aucun voyageur à supprimer.");
        }
    }

}
