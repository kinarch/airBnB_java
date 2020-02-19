package com.richard.airbnb.models.utilisateurs;

public class Voyageur extends Personne {

    /**
     * Constructeur d'une Personne.
     *
     * @param prenom son prenom
     * @param nom    son nom
     * @param age    son age
     */
    public Voyageur(String prenom, String nom, int age) throws Exception {
        super(prenom, nom, age);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
