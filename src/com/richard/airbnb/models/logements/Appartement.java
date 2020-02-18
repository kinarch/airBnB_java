package com.richard.airbnb.models.logements;

import com.richard.airbnb.models.utilisateurs.Hote;

import java.util.Objects;

public class Appartement extends Logement {

    private final int numeroEtage;
    private final int superficieBalcon;

    public Appartement(Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax, int numeroEtage, int superficieBalcon) throws Exception {
        super(hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
        if (superficieBalcon < 0) {
            throw new Exception("Impossible pour un appartement de posséder un balcon avec superficie négative.");
        }
        this.numeroEtage = numeroEtage;
        this.superficieBalcon = superficieBalcon > 0 ? superficieBalcon : 0;
    }

    @Override
    public int getSuperficieTotal() {
        return super.getSuperficie() + superficieBalcon;
    }

    @Override
    public void afficher() {
        getHote().afficher();
        System.out.println();
        System.out.println("Le logement est un appartement situé " + getAdresse() + " à l'étage " + numeroEtage + ".");
        System.out.println("superficie : " + getSuperficieTotal() + "m²");
        System.out.print("Balcon : ");
        if (superficieBalcon > 0) {
            System.out.println("Oui (" + superficieBalcon + "m²).");
        } else {
            System.out.println("Non.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Appartement that = (Appartement) o;
        return numeroEtage == that.numeroEtage &&
                superficieBalcon == that.superficieBalcon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroEtage, superficieBalcon);
    }
}
