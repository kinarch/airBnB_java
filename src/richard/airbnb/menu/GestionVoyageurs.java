package richard.airbnb.menu;

import richard.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class GestionVoyageurs {

    private static final ArrayList<Voyageur> listeVoyageurs = Menu.listeVoyageurs;
    private static final int MAX_VALUE_OPTION = 3;

    static void listerVoyageur() throws Exception {


        System.out.println("--------------------");
        System.out.println("Liste des voyageurs");
        if (listeVoyageurs.size() > 0) {
            for (int i = 0; i < listeVoyageurs.size(); i++) {
                Voyageur voyageur = listeVoyageurs.get(i);
                System.out.print("n°" + i + " : ");
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

        switch (Menu.choix(MAX_VALUE_OPTION)) {
            case 1:
                ajouterVoyageur();
                break;
            case 2:
                supprimerVoyageur();
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterVoyageur() throws Exception {

        String prenom;
        String nom;
        int age;

        System.out.println("Prénom : ");
        prenom = Menu.scanner.next();

        System.out.println("Nom : ");
        nom = Menu.scanner.next();

        System.out.println("Age : ");
        age = Menu.choix(140);

        Voyageur newVoyageur = new Voyageur(prenom, nom, age);
        listeVoyageurs.add(newVoyageur);

        listerVoyageur();
    }

    static void supprimerVoyageur() throws Exception {

        if (!listeVoyageurs.isEmpty()) {
            System.out.println("Numéro du voyageur à supprimer :");
            int indexVoyageur = 0;
            if (listeVoyageurs.size() > 1) {
                indexVoyageur = Menu.choix(listeVoyageurs.size() - 1);
            }
            System.out.println("Etes-vous certains de supprimer la reservation n°" + indexVoyageur);
            System.out.print("(0 : non, 1 : oui) ");
            int estSur = Menu.choix(1);
            if (estSur == 1) {
                listeVoyageurs.remove(indexVoyageur);
            }
        } else {
            System.out.println("Aucun voyageur à supprimer.");
        }

        listerVoyageur();
    }

}
