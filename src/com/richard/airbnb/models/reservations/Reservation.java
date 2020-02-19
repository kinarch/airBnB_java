package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.utilisateurs.Voyageur;

import java.util.Date;

public class Reservation {

    private static int id = 0;

    private int identifiant;
    private Sejour sejour;
    private Voyageur voyageur;
    private boolean estValidee;
    private Date dateDeReservation;

    /**
     * @param sejour            Le sejour
     * @param voyageur          La personne qui a reservé
     * @param dateDeReservation La date de réservation
     */
    public Reservation(Sejour sejour, Voyageur voyageur, Date dateDeReservation) throws Exception {

        if (sejour == null || voyageur == null || dateDeReservation == null) {
            throw new Exception("Impossible de créer une réservation car l'un des arguments est nul.");
        }

        if (!sejour.verificationDateArrivee()) {
            throw new Exception("Impossible d'effectuer une réservation avant la date actuelle.");
        }

        if (!sejour.verificationNombreDeVoyageurs()) {
            throw new Exception("Impossible d'effectuer une réservation avec " + sejour.nbVoyageurs + " voyageurs, supérieur à " + sejour.logement.getNbVoyageursMax() + " nombre de voyageurs max.");
        }

        if (!sejour.verificationNombreDeNuits()) {
            throw new Exception("Impossible d'effectuer une réservation car le nombre de nuit est invalide");
        }

        this.identifiant = ++id;
        this.estValidee = false;
        this.sejour = sejour;
        this.voyageur = voyageur;
        this.dateDeReservation = dateDeReservation;
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
}
