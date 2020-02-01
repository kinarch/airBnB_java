package richard.airbnb.menu;

import java.util.ArrayList;

public interface GestionInterface {

    static ArrayList<Object> list = null;

    /**
     * Methode d'initialisation de la gestion
     */
    void init() throws Exception;

    /**
     * Methode qui affiche la liste
     */
    static void lister() throws Exception {

    }

    /**
     * Methode pour ajouter dans la liste
     */
    void ajouter() throws Exception;

    /**
     * Methode pour ajouter dans la liste
     */
    void supprimer() throws Exception;

    /**
     * Methode pour ajouter dans la liste
     */
    void retour() throws Exception;
}
