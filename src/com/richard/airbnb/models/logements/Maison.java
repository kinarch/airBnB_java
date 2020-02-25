package com.richard.airbnb.models.logements;

import com.richard.airbnb.models.utilisateurs.Hote;

import java.util.Objects;

public final class Maison extends Logement {

    private final int superficieJardin;
    private final boolean piscine;

    public Maison(String nom, Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax, int superficieJardin, boolean piscine) throws Exception {
        super(nom, hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
        if (superficieJardin < 0) {
            throw new Exception("Impossible pour une maison de posséder un jardin avec un superficie négative.");
        }
        this.superficieJardin = superficieJardin;
        this.piscine = piscine;
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
        s += "Piscine : " + (piscine ? "Oui." : "Non.") + "\n\r";
        s += "Tarif par nuit : " + getTarifParNuit() + "€.";
        return s;
    }

    @Override
    public int getSuperficieTotal() {
        return super.getSuperficie() + superficieJardin;
    }

    public int getSuperficieJardin() {
        return superficieJardin;
    }

    public boolean hasPiscine() {
        return piscine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Maison maison = (Maison) o;
        return superficieJardin == maison.superficieJardin &&
                piscine == maison.piscine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), superficieJardin, piscine);
    }
}
