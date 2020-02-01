package richard.airbnb.reservations;

import richard.airbnb.logements.Logement;

import java.util.Date;

public class SejourCourt extends Sejour implements ConditionsTarifairesInterface {

    public SejourCourt(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        super(dateArrivee, nbNuits, logement, nbVoyageurs);
    }

    @Override
    public int getTarif() {
        return tarif;
    }

    @Override
    protected void miseAJourDuTarif() {
        this.tarif = logement.getTarifParNuit() * nbNuits;
    }

    @Override
    public boolean verificationNombreDeNuits() {
        return nbNuits > 0 && nbNuits < 6;
    }

    @Override
    public boolean beneficiePromotion() {
        return false;
    }

    @Override
    public void afficher() {
        System.out.println();
        super.afficher();
        System.out.println("Le prix de se séjour est de " + getTarif() + "€.");
        System.out.println("--->    Sejour court");
    }
}
