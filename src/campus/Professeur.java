package campus;

public class Professeur extends Personne {
    private String specialite;

    public Professeur(String nom, String prenom, String email, String specialite) {
        super(nom, prenom, email);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Professeur [Nom=" + getNom() +
                ", Prenom=" + getPrenom() +
                ", Email=" + getEmail() +
                ", Specialite=" + specialite + "]";
    }
}