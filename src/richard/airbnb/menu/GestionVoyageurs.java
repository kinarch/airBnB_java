package richard.airbnb.menu;

import richard.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

class GestionVoyageurs extends Gestion {

    private static final ArrayList<Voyageur> listeVoyageurs = Menu.listeVoyageurs;

    private GestionVoyageurs() {
    }

    static void listerVoyageur() {


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

        switch (Menu.choix(NB_OPTIONS)) {
            case AJOUTER:
                try {
                    ajouterVoyageur();
                } catch (Exception e) {

                }
                break;
            case SUPPRIMER:
                try {
                    supprimerVoyageur();
                } catch (Exception e) {
                    
                }
                break;
            case RETOUR:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterVoyageur() throws Exception {

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
