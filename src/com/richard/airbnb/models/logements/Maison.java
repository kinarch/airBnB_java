package com.richard.airbnb.models.logements;

import com.richard.airbnb.models.utilisateurs.Hote;

public class Maison extends Logement {

    private final int superficieJardin;
    private final boolean possedePiscine;

    public Maison(Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax, int superficieJardin, boolean possedePiscine) throws Exception {
        super(hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
        if (superficieJardin < 0) {
            throw new Exception("Impossible pour une maison de posséder un jardin avec un superficie négative.");
        }
        this.superficieJardin = superficieJardin;
        this.possedePiscine = possedePiscine;
    }

    @Override
    public void afficher() {
        getHote().afficher();
        System.out.println();
        System.out.println("Le logement est une maison situé " + getAdresse() + ".");
        System.out.println("superficie : " + getSuperficieTotal() + "m²");
        System.out.print("Jardin : ");
        if (superficieJardin > 0) {
            System.out.println("Oui (" + superficieJardin + "m²).");
        } else {
            System.out.println("Non.");
        }
        System.out.println("Piscine : " + (possedePiscine ? "Oui." : "Non."));
    }

    @Override
    public int getSuperficieTotal() {
        return super.getSuperficie() + superficieJardin;
    }
}
