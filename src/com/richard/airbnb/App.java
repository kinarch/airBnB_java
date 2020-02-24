package com.richard.airbnb;

import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Maison;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.reservations.Sejour;
import com.richard.airbnb.models.reservations.SejourCourt;
import com.richard.airbnb.models.reservations.SejourLong;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Voyageur;
import com.richard.airbnb.tools.MaDate;

import java.util.Date;

public class App {

    public static void main(String[] args) throws Exception {

        //  personne : voyageur
        System.out.println("# Voyageur");
        Voyageur voyageur = new Voyageur("Diogène", "de Sinope", 29);
        voyageur.afficher();
        System.out.println();

        //  personne : voyageur 2
        System.out.println("# Voyageur 2");
        Voyageur voyageur2 = new Voyageur("Friedrich", "Nietzsche", 34);
        voyageur2.afficher();
        System.out.println();

        //  personne : voyageur 3
        System.out.println("# Voyageur 3");
        Voyageur voyageur3 = new Voyageur("Jim", "Jones", 34);
        voyageur2.afficher();
        System.out.println();

        //  personne : hote
        System.out.println("# Hote");
        Hote hote = new Hote("Peter", "Bardu", 28, 12);
        hote.afficher();
        System.out.println();

        //  personne : hote2
        System.out.println("# Hote 2");
        Hote hote2 = new Hote("Jeremy", "Pasco", 29, 1);
        hote2.afficher();
        System.out.println();

        //  appartement de hote
        Appartement appartement = new Appartement(
                "App 1",
                hote,
                "46 rue des Canoniers, 59800, Lille",
                40,
                72,
                3,
                3,
                12);

        //  maison de hote
        Maison maison = new Maison(
                "Maison 1",
                hote,
                "81 rue Colbert, 37000, Tour",
                50,
                100,
                5,
                500,
                false);

        //  maison de hote
        Maison maison2 = new Maison(
                "Maison 2",
                hote2,
                "25 rue Imaginaire, 00000, Neverland",
                75,
                200,
                10,
                0,
                true);

        Date date = new MaDate(25, 12, 2021);
        Date date2 = new MaDate(1, 1, 2021);
        Date date3 = new MaDate(11, 10, 2020);

        Reservation r1 = tpReservartion(voyageur, appartement, 10, date, 10);
        Reservation r2 = tpReservartion(voyageur2, maison, 5, date2, 8);
        Reservation r3 = tpReservartion(voyageur3, maison2, 2, date3, 2);
    }

    private static Reservation tpReservartion(Voyageur voyageur, Logement logement, int nbNuitVoyageur, Date dateArrive, int nbVoyageurs) throws Exception {

        Sejour sejour;

        if (nbNuitVoyageur < 6) {
            //  sejour cours
            sejour = new SejourCourt(
                    dateArrive,
                    logement,
                    nbNuitVoyageur,
                    nbVoyageurs
            );
        } else {
            //  sejour long
            sejour = new SejourLong(
                    dateArrive,
                    logement,
                    nbNuitVoyageur,
                    nbVoyageurs);
        }

        return new Reservation(sejour, voyageur, new Date());
    }
}
