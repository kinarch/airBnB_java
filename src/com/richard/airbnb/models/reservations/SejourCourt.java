package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.tools.MaDate;

import java.util.Date;

public class SejourCourt extends Sejour implements ConditionsTarifairesInterface {

    public static final int MAX_NUITS = 6;

    public SejourCourt(Date dateArrivee, Logement logement, int nbNuits, int nbVoyageurs) throws Exception {
        super(dateArrivee, logement, nbNuits, nbVoyageurs);
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
        return nbNuits > 0 && nbNuits <= MAX_NUITS;
    }

    @Override
    public boolean beneficiePromotion() {
        return false;
    }

    @Override
    public void afficher() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "\n\r" + "Le prix de se séjour est de " + getTarif() + "€.";
    }
}
