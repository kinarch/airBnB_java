package richard.airbnb.logements;

import richard.airbnb.utilisateurs.Hote;

public abstract class Logement {

    private Hote hote;
    private String adresse;
    private int tarifParNuit;
    private int superficie;
    private int nbVoyageursMax;

    public Logement(Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax) {

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
