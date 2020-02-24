package com.richard.airbnb.models.utilisateurs;

import java.util.Objects;

public final class Hote extends Personne {

    private final int delaiDeReponse;

    /**
     * Constructeur d'une Personne.
     *
     * @param prenom son prenom
     * @param nom    son nom
     * @param age    son age
     */
    public Hote(String prenom, String nom, int age, int delaiDeReponse) throws IllegalArgumentException {
        super(prenom, nom, age);
        if (delaiDeReponse <= 0) {
            throw new IllegalArgumentException("Delai de réponse inférieur ou égal à 0.");
        }
        this.delaiDeReponse = delaiDeReponse;
    }

    public int getDelaiDeReponse() {
        return delaiDeReponse;
    }

    public void afficher() {
        super.afficher();
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += " qui s'engage à répondre dans ";
        s += (delaiDeReponse == 1 ? "l'heure." : "les " + delaiDeReponse + " heures.");
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Hote hote = (Hote) o;
        return (
                this.delaiDeReponse == hote.delaiDeReponse
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), delaiDeReponse);
    }

    @Override
    public int getValueToCompare() {
        return getDelaiDeReponse();
    }
}
