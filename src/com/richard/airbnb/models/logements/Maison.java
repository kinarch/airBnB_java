package com.richard.airbnb.models.logements;

import com.richard.airbnb.models.utilisateurs.Hote;

import java.util.Objects;

public final class Maison extends Logement {

    private final int superficieJardin;
    private final boolean possedePiscine;

    public Maison(String nom, Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax, int superficieJardin, boolean possedePiscine) throws Exception {
        super(nom, hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
        if (superficieJardin < 0) {
            throw new Exception("Impossible pour une maison de posséder un jardin avec un superficie négative.");
        }
        this.superficieJardin = superficieJardin;
        this.possedePiscine = possedePiscine;
    }

    @Override
    public void afficher() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String s = getHote().toString() + "\n\r";
        s += "Le logement est une maison situé " + getAdresse() + "." + "\n\r";
        s += "superficie : " + getSuperficieTotal() + "m²" + "\n\r";
        s += "Jardin : " + (superficieJardin > 0 ? "Oui (" + superficieJardin + "m²)." : "Non.") + "\n\r";
        s += "Piscine : " + (possedePiscine ? "Oui." : "Non.");
        return s;
    }

    @Override
    public int getSuperficieTotal() {
        return super.getSuperficie() + superficieJardin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Maison maison = (Maison) o;
        return superficieJardin == maison.superficieJardin &&
                possedePiscine == maison.possedePiscine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), superficieJardin, possedePiscine);
    }
}
