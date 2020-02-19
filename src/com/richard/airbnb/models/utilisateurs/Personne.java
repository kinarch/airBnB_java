package com.richard.airbnb.models.utilisateurs;

import java.util.Objects;

public abstract class Personne {

    private static final int NOM_MAX_LENGHT = 100;
    private static final int AGE_MAJORITE = 18;
    private final String prenom;
    private final String nom;
    private final int age;

    /**
     * Constructeur d'une Personne.
     *
     * @param prenom son prenom
     * @param nom    son nom
     * @param age    son age
     */
    public Personne(String prenom, String nom, int age) throws Exception {
        if (prenom.isBlank()) {
            throw new Exception("Prenom vide.");
        }
        if (prenom.length() > NOM_MAX_LENGHT) {
            throw new Exception("Prenom trop grand.");
        }
        if (nom.isBlank()) {
            throw new Exception("Nom vide.");
        }
        if (nom.length() > NOM_MAX_LENGHT) {
            throw new Exception("Nom trop grand.");
        }
        if (age < AGE_MAJORITE) {
            throw new Exception("Seul les adultes (age supérieur ou égal à " + AGE_MAJORITE + ") peuvent être enregistré.");
        }
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    /**
     * Afficher dans la console le prenom, nom et age de la Personne.
     */
    public void afficher() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + age + "ans)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Personne personne = (Personne) o;
        return (
                this.age == personne.age &&
                        this.prenom.equals(personne.prenom) &&
                        this.nom.equals(personne.nom)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenom, nom, age);
    }
}
