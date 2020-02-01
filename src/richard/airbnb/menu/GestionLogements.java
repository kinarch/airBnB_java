package richard.airbnb.menu;

import richard.airbnb.logements.Appartement;
import richard.airbnb.logements.Logement;
import richard.airbnb.logements.Maison;
import richard.airbnb.utilisateurs.Hote;

import java.util.ArrayList;

public class GestionLogements {

    private static final int MAX_VALUE_OPTION = 3;
    private static final int TYPE_MAISON = 1;
    private static final int TYPE_APPARTEMENT = 2;
    private static final int RETOUR = 3;
    private static final ArrayList<Logement> listeLogements = Menu.listeLogements;
    private static final ArrayList<Hote> listeHotes = Menu.listeHotes;

    static void listerLogement() throws Exception {

        System.out.println("--------------------");
        System.out.println("Liste des Logements");

        if (listeLogements.size() > 0) {
            for (int i = 0; i < listeLogements.size(); i++) {
                Logement logement = listeLogements.get(i);
                System.out.print("n°" + i + " : ");
                logement.afficher();
            }
        } else {
            System.out.println("Aucun logement enregistré.");
        }

        System.out.println("Saisir une option :");
        System.out.println("1 : Ajouter un logement");
        System.out.println("2 : Supprimer un logement");
        System.out.println("3 : Retour");

        switch (Menu.choix(MAX_VALUE_OPTION)) {
            case 1:
                ajouterLogement();
                break;
            case 2:
                supprimerLogement();
                break;
            case 3:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterLogement() throws Exception {

        if (listeHotes.isEmpty()) {
            System.out.println("Impossible d'ajouter un logement, aucun hote enregistré.");
        } else {

            Hote hote;
            int indexHote;
            int typeLogement;
            int superficie;
            int tarifParNuit;
            int voyageurMax;

            System.out.println("Saisir le type de logement :");
            System.out.println("1 : Maison");
            System.out.println("2 : Appartement");
            System.out.println("3 : Retour");

            typeLogement = Menu.choix(3);

            if (typeLogement == RETOUR) {
                listerLogement();
            }

            if (listeHotes.size() == 1) {
                indexHote = 0;
                System.out.println("Un seul hote trouvé");
            } else {
                System.out.println("Numéro de l'hote : ");
                indexHote = Menu.choix(listeLogements.size());
            }
            hote = Menu.listeHotes.get(indexHote);
            hote.afficher();
            System.out.println();

            System.out.print("Adresse : ");
            String adresse = Menu.scanner.next();

            System.out.print("Superficie : ");
            superficie = Menu.choix();

            System.out.print("Tarif journalier : ");
            tarifParNuit = Menu.choix();

            System.out.print("Nombre maximum de voyageurs :");
            voyageurMax = Menu.choix();

            Logement newLogement = null;
            switch (typeLogement) {
                case TYPE_MAISON:
                    System.out.print("superficie du jardin :");
                    int superficieJardin = Menu.choix();
                    System.out.println();

                    System.out.print("Piscine ? (0 non, 1 oui)");
                    boolean possedePiscine = Menu.choix(1) > 0;
                    System.out.println();

                    listeLogements.add(new Maison(hote, adresse, tarifParNuit, superficie, voyageurMax, superficieJardin, possedePiscine));
                    System.out.println("Votre maison a été ajouté avec succès");
                    break;
                case TYPE_APPARTEMENT:
                    System.out.print("Saisissez le numéro de l'étage :");
                    int numeroEtage = Menu.choix();
                    System.out.println();

                    System.out.print("Superficie du balcon ? (0 : pas de balcon)");
                    int superficieBalcon = Menu.choix();
                    System.out.println();

                    listeLogements.add(new Appartement(hote, adresse, tarifParNuit, superficie, voyageurMax, numeroEtage, superficieBalcon));
                    System.out.println("Votre appartement a été ajouté avec succès");
                    break;
            }
        }
        listerLogement();
    }

    static void supprimerLogement() throws Exception {

        if (!listeLogements.isEmpty()) {
            System.out.println("Numéro du logement à supprimer :");
            int indexLogement = 0;
            if (listeLogements.size() > 1) {
                indexLogement = Menu.choix(listeLogements.size() - 1);
            }
            System.out.println("Etes-vous certains de supprimer le logement n°" + indexLogement);
            System.out.print("(0 : non, 1 : oui) ");
            int estSur = Menu.choix(1);
            if (estSur == 1) {
                listeLogements.remove(indexLogement);
            }
        } else {
            System.out.println("Aucun logement à supprimer.");
        }

        listerLogement();
    }
}
