package richard.airbnb.utilisateurs;

public class Hote extends Personne {

    private int delaiDeReponse;

    /**
     * Constructeur d'une Personne.
     *
     * @param prenom son prenom
     * @param nom    son nom
     * @param age    son age
     */
    public Hote(String prenom, String nom, int age, int delaiDeReponse) {
        super(prenom, nom, age);
        this.delaiDeReponse = delaiDeReponse;
    }

    public void afficher() {
        super.afficher();
        System.out.print(" qui s'engage à répondre dans ");
        if (delaiDeReponse <= 1) {
            System.out.print("l'heure.");
        }
        else {
            System.out.print("les " + delaiDeReponse + " heures.");
        }
    }
}
