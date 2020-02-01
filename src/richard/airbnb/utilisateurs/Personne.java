package richard.airbnb.utilisateurs;

public class Personne {

    private final String prenom;
    private final String nom;
    private final int age;

    /**
     * Constructeur d'une Personne.
     *
     * @param prenom son prenom
     * @param nom    son nom
     * @param age    son age
     */
    public Personne(String prenom, String nom, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    /**
     * Afficher dans la console le prenom, nom et age de la Personne.
     */
    public void afficher() {
        System.out.print(prenom + " " + nom + " (" + age + "ans)");
    }
}
