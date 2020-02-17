package com.richard.airbnb.models.logements;

import com.richard.airbnb.models.utilisateurs.Hote;

public abstract class Logement {

    private final Hote hote;
    private final String adresse;
    private final int tarifParNuit;
    private final int superficie;
    private final int nbVoyageursMax;

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
}
