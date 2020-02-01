package richard.airbnb.logements;

import richard.airbnb.utilisateurs.Hote;

public class Maison extends Logement {

    private int superficieJardin;
    private boolean possedePiscine;

    public Maison(Hote hote, String adresse, int tarifParNuit, int superficie, int nbVoyageursMax, int superficieJardin, boolean possedePiscine) {
        super(hote, adresse, tarifParNuit, superficie, nbVoyageursMax);
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
