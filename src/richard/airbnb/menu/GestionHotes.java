package richard.airbnb.menu;

import richard.airbnb.utilisateurs.Hote;

import java.util.ArrayList;
import java.util.InputMismatchException;

class GestionHotes extends Gestion {

    private static ArrayList<Hote> listeHotes = Menu.listeHotes;

    private GestionHotes() {
    }

    static void listerHotes() {

        System.out.println("--------------------");
        System.out.println("Liste des hotes");

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

        switch (Menu.choix(NB_OPTIONS)) {
            case AJOUTER:
                try {
                    ajouterHote();
                }  catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    Menu.scanner.next();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    listerHotes();
                }
                break;
            case SUPPRIMER:
                try {
                    supprimerHote();
                } catch (InputMismatchException e) {
                    Menu.scanner.next();
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    listerHotes();
                }
                break;
            case RETOUR:
                Menu.listerMenu();
                break;
        }
    }

    static void ajouterHote() throws Exception {

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

    static void supprimerHote() throws Exception {

        if (!listeHotes.isEmpty()) {
            System.out.println("Numéro de l'hote à supprimer (entre 0 et " + (listeHotes.size() - 1) + ") : ");
            int indexHote = Menu.scanner.nextInt();
            if (indexHote >= listeHotes.size()) {
                throw new Exception("Aucun hote ne correspond au numéro " + indexHote + ".");
            }
            System.out.println("Etes-vous certains de supprimer l'hote n°" + indexHote + " (0 : non, 1 : oui) : ");
            int estSur = Menu.scanner.nextInt();
            if (estSur > 1) {
                listeHotes.remove(indexHote);
            }
        } else {
            System.out.println("Aucun hote à supprimer.");
        }
    }
}
