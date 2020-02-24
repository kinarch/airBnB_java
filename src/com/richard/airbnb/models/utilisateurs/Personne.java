package com.richard.airbnb.models.utilisateurs;

import com.richard.airbnb.models.MyComparable;

import java.util.Objects;

public abstract class Personne implements MyComparable<Personne>, Cloneable {

    //  constantes
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
    public Personne(String prenom, String nom, int age) throws IllegalArgumentException {
        if (prenom == null || nom == null) {
            throw new IllegalArgumentException("Arguments null.");
        }
        if (prenom.isBlank()) {
            throw new IllegalArgumentException("Prenom vide.");
        }
        if (prenom.length() > NOM_MAX_LENGHT) {
            throw new IllegalArgumentException("Prenom trop grand.");
        }
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Nom vide.");
        }
        if (nom.length() > NOM_MAX_LENGHT) {
            throw new IllegalArgumentException("Nom trop grand.");
        }
        if (age < AGE_MAJORITE) {
            throw new IllegalArgumentException("Seul les adultes (age supérieur ou égal à " + AGE_MAJORITE + ") peuvent être enregistré.");
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

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
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

    @Override
    public int getValueToCompare() {
        return getAge();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
