package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.tools.MaDate;

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
    public SejourLong(Date dateArrivee, Logement logement, int nbNuits, int nbVoyageurs) {
        super(dateArrivee, logement, nbNuits, nbVoyageurs);
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
        return nbNuits > 6;
    }

    /**
     * Affiche dans la console un message relatif au attributs de la classe
     */
    @Override
    public void afficher() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String s = super.toString() + "\n\r" + "Le prix de se séjour est de " + getTarif() + "€.";
        s += (beneficiePromotion() ? " (" + promotion + "€ de promotion)" : "");
        return s;
    }
}
