package com.richard.airbnb.models.utilisateurs;

import java.util.Objects;

public class Hote extends Personne {

    private int delaiDeReponse;

    /**
     * Constructeur d'une Personne.
     *
     * @param prenom son prenom
     * @param nom    son nom
     * @param age    son age
     */
    public Hote(String prenom, String nom, int age, int delaiDeReponse) throws Exception {
        super(prenom, nom, age);
        if (delaiDeReponse < 0) {
            throw new Exception("Delai de réponse négatif.");
        }
        this.delaiDeReponse = delaiDeReponse;
    }

    public void afficher() {
        super.afficher();
        System.out.print(" qui s'engage à répondre dans ");
        if (delaiDeReponse <= 1) {
            System.out.print("l'heure.");
        }
        else {
            System.out.print("les " + delaiDeReponse + " heures.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Hote hote = (Hote) o;
        return delaiDeReponse == hote.delaiDeReponse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), delaiDeReponse);
    }
}