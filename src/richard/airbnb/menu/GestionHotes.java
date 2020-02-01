package richard.airbnb.menu;

import richard.airbnb.utilisateurs.Hote;

import java.util.ArrayList;

class GestionHotes {

    private static ArrayList<Hote> listeHotes = Menu.listeHotes;
    private static int MAX_VALUE_OPTION = 3;

    static void listerHotes() throws Exception {

        System.out.println("--------------------");
        if (listeHotes.size() > 0) {
            for (int i = 0; i < listeHotes.size(); i++) {
                Hote hote = listeHotes.get(i);
                System.out.print("n°" + i + " : ");
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

        switch (Menu.choix(MAX_VALUE_OPTION)) {
            case 1:
                ajouterHote();
                break;
            case 2:
                supprimerHote();
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterHote() throws Exception {

        String prenom;
        String nom;
        int age;
        int delaiReponse;

        System.out.println("Prénom : ");
        prenom = Menu.scanner.next();

        System.out.println("Nom : ");
        nom = Menu.scanner.next();

        System.out.println("Age : ");
        age = Menu.choix(140);

        System.out.println("Delai de réponse : ");
        delaiReponse = Menu.choix(72);

        Hote newHote = new Hote(prenom, nom, age, delaiReponse);
        listeHotes.add(newHote);

        listerHotes();
    }

    static void supprimerHote() throws Exception {

        if (!listeHotes.isEmpty()) {
            System.out.println("Numéro de l'hote à supprimer :");
            int indexHote = 0;
            if (listeHotes.size() > 1) {
                indexHote = Menu.choix(listeHotes.size() - 1);
            }
            System.out.println("Etes-vous certains de supprimer l'hote n°" + indexHote);
            System.out.print("(0 : non, 1 : oui) ");
            int estSur = Menu.choix(1);
            if (estSur == 1) {
                listeHotes.remove(indexHote);
            }
        } else {
            System.out.println("Aucun hote à supprimer.");
        }

        listerHotes();
    }
}
