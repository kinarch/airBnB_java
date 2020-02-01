package richard.airbnb.outils;

import jdk.jshell.execution.Util;

import java.util.Date;

/**
 * La boite à outils du projet
 *
 * @author RichardKranich
 * @since 28.01.2020
 */
public class Utile {

    private static final char delimiter = '/';
    private static final int maxDay = 31;
    private static final int maxMonth = 12;
    private static final int diffYear = 1900;

    private Utile(){
    }

    /**
     * Methode pour construire une Date avec les 3 entiers définissant l'année, le mois, le jour.
     * (e.g. 2020, 12, 25)
     *
     * @param y l'année
     * @param m le mois au
     * @param d is the date of the date
     * @return the created date
     */
    public static Date buildDate(int y, int m, int d) {
        d = d >= 0 && d <= maxDay  ? d : 0;
        m = m >= 0 && m <= maxDay ? m - 1 : 0;
        y = y - diffYear;
        return new Date(y, m, d);
    }

    /**
     * @param strDate chaine de caractère au format mm/dd/yyyy (e.g. 25/12/2020)
     * @return un objet de type Date à la date en entrée.
     */
    public static Date buildDate(String strDate) {
        return new Date(strDate);
    }

    /**
     * Methode qui retourne une chaine de caractère formatée à partir de
     * du jour, du mois et de l'année de la date entrée en paramètre.
     *
     * @param date type Date
     * @return String au format "dd/mm/yyyy"
     */
    public static String formatDate(Date date) {

        //  recupurer chaque données numériques
        int y = date.getYear() + diffYear;
        int m = date.getMonth() + 1;
        int d = date.getDate();

        //  convertir chaque données en chaines de charactères formater
        String year = "" + y;
        String month = "" + (m > 9 ? m : "0" + m);
        String day = "" + (d > 9 ? d : "0" + d);

        //  retourner la concatenation des elements séparer par un délimiteur
        return day + delimiter + month + delimiter + year;
    }
}
