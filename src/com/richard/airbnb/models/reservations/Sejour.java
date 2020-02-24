package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.tools.MaDate;

import java.util.Date;

public abstract class Sejour implements SejourInterface, Cloneable {

    protected Date dateArrivee;
    protected Logement logement;
    protected int nbVoyageurs;
    protected int nbNuits;
    protected int tarif;

    public Sejour(Date dateArrivee, Logement logement, int nbNuits, int nbVoyageurs) throws IllegalArgumentException {
        if (nbNuits < 1) {
            throw new IllegalArgumentException("Nombre de nuit non valide.");
        }
        if (nbVoyageurs < 1) {
            throw new IllegalArgumentException("Nombre de voyageurs non valide.");
        }
        this.nbNuits = nbNuits;
        this.nbVoyageurs = nbVoyageurs;
        setDateArrivee(dateArrivee);
        setLogement(logement);
    }

    protected abstract void miseAJourDuTarif();

    /*
        Pour la date, je construit une nouvelle MaDate à partir du timestamp de la date en paramètre
     */
    public Date getDateArrivee() {
        return new MaDate(dateArrivee.getTime());
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = (MaDate) dateArrivee.clone();
    }

    public int getNbVoyageurs() {
        return nbVoyageurs;
    }

    public void setNbVoyageurs(int nbVoyageurs) {
        this.nbVoyageurs = nbVoyageurs;
    }

    public int getNbNuits() {
        return nbNuits;
    }

    public void setNbNuits(int nbNuits) {
        this.nbNuits = nbNuits;
        miseAJourDuTarif();
    }

    /*
        Le logement ne peut être null et met a jour le tarif du séjour.
     */
    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) throws IllegalArgumentException {
        if (logement == null) {
            throw new IllegalArgumentException();
        }
        this.logement = logement;
        miseAJourDuTarif();
    }

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
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String s = logement.toString() + "\n\r" +
                "La date d'arrivée est le " + dateArrivee + " pour " + nbNuits + " nuits pour " + nbVoyageurs +
                (nbVoyageurs > 1 ? " personnes" : " personne") + ".";
        return s;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Sejour clone = (Sejour) super.clone();
        clone.setDateArrivee(new MaDate(dateArrivee.getTime()));
        return clone;
    }
}
