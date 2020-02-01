package richard.airbnb.logements;

import richard.airbnb.utilisateurs.Hote;

public class Appartement extends Logement {

    private int numeroEtage;
    private int superficieBalcon;

    public Appartement(Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax, int numeroEtage, int superficieBalcon) {
        super(hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
        this.numeroEtage = numeroEtage;
        this.superficieBalcon = superficieBalcon;
    }

    @Override
    public int getSuperficieTotal() {
        return super.getSuperficie() + superficieBalcon;
    }

    @Override
    public void afficher() {
        getHote().afficher();
        System.out.println();
        System.out.println("Le logement est un appartement situé " + getAdresse() + ".");
        System.out.println("superficie : " + getSuperficieTotal() + "m²");
        System.out.print("Balcon : ");
        if (superficieBalcon > 0) {
            System.out.println("Oui (" + superficieBalcon + "m²).");
        } else {
            System.out.println("Non.");
        }
    }
}
