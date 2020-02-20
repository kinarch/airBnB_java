package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.MaDate;

import java.util.Date;

public class Reservation implements Cloneable {

    private static int identifiant = 0;

    private final int id;
    private final Sejour sejour;
    private final Voyageur voyageur;
    private final Date dateDeReservation;
    private boolean estValidee;

    /**
     * @param sejour   Le sejour
     * @param voyageur La personne qui a reservé
     */
    public Reservation(Sejour sejour, Voyageur voyageur) throws IllegalArgumentException {

        if (sejour == null || voyageur == null) {
            throw new IllegalArgumentException("Un des arguments est nul.");
        }

        this.id = ++identifiant;
        this.sejour = sejour;
        this.voyageur = voyageur;
        this.dateDeReservation = new MaDate();
    }

    public int getId() {
        return id;
    }

    /*
        Je renvoie un clone du sejour
     */
    public Sejour getSejour() {
        try {
            return (Sejour) sejour.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        Le voyageur ne peut changer
     */
    public Voyageur getVoyageur() {
        return voyageur;
    }

    /*
        Je retourne une nouvelle MaDate crée a partir du timestamp de la date.
     */
    public MaDate getDateDeReservation() {
        return (MaDate) dateDeReservation.clone();
    }

    public boolean isEstValidee() {
        return estValidee;
    }

    /*
        N'est valide qu'une réservation avec un sejour valide
     */
    public void setEstValidee(boolean estValidee) {
//        estValidee = (
//                sejour.verificationDateArrivee()
//                        && sejour.verificationNombreDeVoyageurs()
//                        && sejour.verificationNombreDeNuits()
//        );
        this.estValidee = estValidee;
    }

    /**
     * Affiche les information du voyageur et du sejour
     */
    public void afficher() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Réservation n°" + identifiant + "\n\r" + voyageur.toString() + " a fait une réservation chez " + sejour.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
