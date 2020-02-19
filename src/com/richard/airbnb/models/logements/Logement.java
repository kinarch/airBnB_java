package com.richard.airbnb.models.logements;

import com.richard.airbnb.models.utilisateurs.Hote;

import java.util.Objects;

public abstract class Logement {

    private final Hote hote;
    private final String adresse;
    private final int tarifParNuit;
    private final int superficie;
    private final int nbVoyageursMax;
    private String nom;

    public Logement(Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax) throws Exception {
        if (hote == null) {
            throw new Exception("L'hote n'existe pas, le logement ne peut poursuivre sa construction.");
        }
        if (adresse.isBlank()) {
            throw new Exception("Impossible pour un logement d'avoir une adresse vide.");
        }
        if (tarifParNuit < 0) {
            throw new Exception("Impossible pour un logement d'être sur un tarif négatif.");
        }
        if (superficie < 0) {
            throw new Exception("Impossible pour un logement de posséder une superficie négatif.");
        }
        if (nbVoyageursMax <= 0) {
            throw new Exception("Impossible pour un logement de supporter aucun ou un nombre négatif de voyageur.");
        }
        this.hote = hote;
        this.adresse = adresse;
        this.tarifParNuit = tarifParNuit;
        this.superficie = superficie;
        this.nbVoyageursMax = nbVoyageursMax;
    }

    public abstract void afficher();

    public abstract int getSuperficieTotal();

    public Hote getHote() {
        return hote;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getTarifParNuit() {
        return tarifParNuit;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getNbVoyageursMax() {
        return nbVoyageursMax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logement logement = (Logement) o;
        return tarifParNuit == logement.tarifParNuit &&
                superficie == logement.superficie &&
                nbVoyageursMax == logement.nbVoyageursMax &&
                hote.equals(logement.hote) &&
                adresse.equals(logement.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
    }
}
