package richard.airbnb.reservations;

import richard.airbnb.utilisateurs.Personne;
import richard.airbnb.utilisateurs.Voyageur;

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
        if (!sejour.verificationDateArrivee()) {
            throw new Exception("RESERVATION REFUSER : IMPOSSIBLE DE RESERVER DANS LE PASSE");
        }
        if (!sejour.verificationNombreDeVoyageurs()) {
            throw new Exception("RESERVATION REFUSER : TROP DE VOYAGEURS");
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

//        if (sejour instanceof SejourLong) {
        System.out.println("Reservation n°" + identifiant);
//        }
        voyageur.afficher();
        System.out.print(" a fait une réserversation chez ");
        sejour.afficher();
    }
}
