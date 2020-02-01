package richard.airbnb.outils;

import java.util.Date;

public class MaDate extends Date {

    private static final char delimiter = '/';
    private static final int diffYear = 1900;
    public static final int maxDay = 31;
    public static final int maxMonth = 12;

    /**
     * Constructeur de MaDate avec les 3 entiers définissant l'année, le mois, le jour.
     * (e.g. 2020, 12, 25)
     *
     * @param y l'année
     * @param m le mois compris entre 1 et 12
     * @param d le jour compris entre 1 et le dernier jour du mois (e.g. 31)
     */
    public MaDate(int d, int m, int y) {
        super(y - diffYear, m - 1, d);
    }


    /**
     * @param timestamp
     */
    public MaDate(long timestamp) {
        super(timestamp);
    }


    /**
     * @param strDate chaine de caractère au format mm/dd/yyyy (e.g. 25/12/2020)
     * @return un objet de type Date à la date en entrée.
     */
    public MaDate(String strDate) {
        super(strDate);
    }

    /**
     * Creation d'une date par default (01/01/1970)
     */
    public MaDate() {
        super();
    }

    /**
     * Retourne une chaine de caractère formatée représentant la date
     *
     * @return String au format "dd/mm/yyyy"
     */
    public String toString() {

        //  recupurer chaque données numériques
        int y = getYear() + diffYear;
        int m = getMonth() + 1;
        int d = getDate();

        //  convertir chaque données en chaines de charactères formater
        String year = "" + (y > 9 ? y : "0" + y);
        String month = "" + (m > 9 ? m : "0" + m);
        String day = "" + (d > 9 ? d : "0" + d);

        //  retourner la concatenation des elements séparer par un délimiteur
        return day + delimiter + month + delimiter + year;
    }
}
