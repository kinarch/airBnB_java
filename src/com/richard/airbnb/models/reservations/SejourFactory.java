package com.richard.airbnb.models.reservations;

import com.richard.airbnb.models.logements.Logement;

import java.util.Date;

public class SejourFactory {

    private static final int MAX_SHORT_SEJOUR_DAYS = 5;

    public static Sejour getSejour(Date dateArrivee, Logement logement, int nbNuits, int nbVoyageurs) throws Exception {
        if (nbNuits <= MAX_SHORT_SEJOUR_DAYS) {
            return new SejourCourt(dateArrivee, logement, nbNuits, nbVoyageurs);
        }
        return new SejourLong(dateArrivee, logement, nbNuits, nbVoyageurs);
    }

}
