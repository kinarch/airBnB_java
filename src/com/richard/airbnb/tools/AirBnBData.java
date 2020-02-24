package com.richard.airbnb.tools;

import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.reservations.Reservation;
import com.richard.airbnb.models.utilisateurs.Hote;
import com.richard.airbnb.models.utilisateurs.Voyageur;

import java.util.ArrayList;

import static java.lang.System.out;

public class AirBnBData {

    public final ArrayList<Hote> hoteList;
    public final ArrayList<Voyageur> voyageurList;
    public final ArrayList<Logement> logementList;
    public final ArrayList<Reservation> reservationList;

    private AirBnBData() throws Exception {
        out.println("Load data.");
        hoteList = new ArrayList<>();
        voyageurList = new ArrayList<>();
        logementList = new ArrayList<>();
        AirBnBXMLParser.parseListDOM("res/logements.xml", hoteList, logementList);
        voyageurList.add(new Voyageur("Voyageur", "du Temp", 30));
        voyageurList.add(new Voyageur("Doctor", "Who", 100));
        reservationList = new ArrayList<>();
    }

    private static AirBnBData instance;

    static {
        try {
            instance = new AirBnBData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AirBnBData getInstance() {
        return instance;
    }
}
