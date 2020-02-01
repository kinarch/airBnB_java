package richard.airbnb.reservations;

import richard.airbnb.logements.Logement;

import java.util.Date;

public class SejourLong extends Sejour implements ConditionsTarifairesInterface {

    private static final int PROMOTION_EN_POURCENTAGE = 20;
    private int promotion;

    /**
     * @param dateArrivee la date du sejour de type Date
     * @param nbNuits     le nombre de nuits du sejour de type int
     * @param logement    Le logement du sejour de type Logement
     * @param nbVoyageurs Le nombre de voyageurs durant le sejour
     */
    public SejourLong(Date dateArrivee, int nbNuits, Logement logement, int nbVoyageurs) {
        super(dateArrivee, nbNuits, logement, nbVoyageurs);
    }

    @Override
    protected void miseAJourDuTarif() {
        int tarifInitiial = logement.getTarifParNuit() * nbNuits;
        this.promotion = tarifInitiial * PROMOTION_EN_POURCENTAGE / 100;
        this.tarif = tarifInitiial - promotion;
    }

    /**
     * @return s'il beneficie ou pas d'une promotion
     */
    @Override
    public boolean beneficiePromotion() {
        return nbNuits > 5;
    }

    /**
     * @return le tarif
     */
    @Override
    public int getTarif() {
        return tarif;
    }


    /**
     * @return si le nombre de nuit est compris entre 0 et 32
     */
    @Override
    public boolean verificationNombreDeNuits() {
        return nbNuits > 0 && nbNuits < 32;
    }

    /**
     * Affiche dans la console un message relatif au attributs de la classe
     */
    @Override
    public void afficher() {
        super.afficher();
        System.out.print("Le prix de se séjour est de " + getTarif() + "€.");
        if (beneficiePromotion()) {
            System.out.println(" (" + promotion + "€ de promotion)");
        }
        System.out.println("--->    Sejour long");
    }
}
