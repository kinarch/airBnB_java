package richard.airbnb.reservations;

import richard.airbnb.logements.Logement;

import java.util.Date;

public abstract class Sejour implements SejourInterface {

    protected Date dateArrivee;
    protected Logement logement;
    protected int nbVoyageurs;
    protected int nbNuits;
    protected int tarif;

    public Sejour(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        this.dateArrivee = dateArrivee;
        this.nbNuits = nbNuits;
        this.logement = logement;
        this.nbVoyageurs = nbVoyageurs;
        miseAJourDuTarif();
    }

    protected abstract void miseAJourDuTarif();

    @Override
    public boolean verificationDateArrivee() {
        return new Date().getTime() < dateArrivee.getTime();
    }

    @Override
    public boolean verificationNombreDeVoyageurs() {
        return nbVoyageurs > 0 && nbVoyageurs <= logement.getNbVoyageursMax();
    }

    @Override
    public void afficher() {
        logement.afficher();
        System.out.println(
                "La date d'arrivée est le " + dateArrivee + " pour " + nbNuits + " nuits pour " + nbVoyageurs +
                        (nbVoyageurs > 1 ? " personnes" : " personne") + "."
        );
    }
}
