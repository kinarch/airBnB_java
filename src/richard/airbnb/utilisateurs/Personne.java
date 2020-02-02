package richard.airbnb.utilisateurs;

public class Personne {

    private static final int NOM_MAX_LENGHT = 100;
    private static final int AGE_MAJORITE = 18;
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
    public Personne(String prenom, String nom, int age) throws Exception {
        if (prenom.length() == 0) {
            throw new Exception("Prenom vide.");
        }
        if (prenom.length() > NOM_MAX_LENGHT) {
            throw new Exception("Prenom trop grand.");
        }
        if (nom.length() == 0) {
            throw new Exception("Nom vide.");
        }
        if (nom.length() > NOM_MAX_LENGHT) {
            throw new Exception("Nom trop grand.");
        }
        if (age < 0) {
            throw new Exception("Age négatif.");
        }
        if (age < AGE_MAJORITE) {
            throw new Exception("Seul les adultes peuvent être enregistré.");
        }
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
