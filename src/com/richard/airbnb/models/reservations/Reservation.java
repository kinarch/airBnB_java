package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.MaDate;

import java.util.Date;

public class Reservation implements Cloneable {

    private static int identifiant = 0;

    private final int id;
    private final Sejour sejour;
    private final Voyageur voyageur;
    private final long dateDeReservation;
    private boolean estValidee;

    /**
     * @param sejour   Le sejour
     * @param voyageur La personne qui a reservé
     */
    public Reservation(Sejour sejour, Voyageur voyageur, Date dateDeReservation) throws IllegalArgumentException {

        if (sejour == null || voyageur == null) {
            throw new IllegalArgumentException("Un des arguments est nul.");
        }

        if (!sejour.verificationNombreDeNuits() || !sejour.verificationNombreDeVoyageurs() || !sejour.verificationDateArrivee()) {
            throw new IllegalArgumentException("Séjour non valide, réservation impossible.");
        }

        this.id = ++identifiant;
        this.sejour = sejour;
        this.voyageur = voyageur;
        this.dateDeReservation = dateDeReservation.getTime();
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
    Je renvoie un clone du sejour
 */
    public Voyageur getVoyageur() {
        try {
            return (Voyageur) voyageur.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
        Je retourne une nouvelle MaDate crée a partir du timestamp de la date.
     */
    public Date getDateDeReservation() {
        return new Date(dateDeReservation);
    }

    public boolean isEstValidee() {
        return estValidee;
    }

    public void setEstValidee(boolean estValidee) {
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
        return "Réservation n°" + identifiant + " le " + new MaDate(dateDeReservation) + ",\n\r" + voyageur.toString() + " a fait une réservation chez " + sejour.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return new Reservation((Sejour) sejour.clone(), (Voyageur) voyageur.clone(), new Date(dateDeReservation));
    }
}
