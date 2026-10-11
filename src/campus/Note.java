package campus;

public class Note {
    private Etudiant etudiant;
    private Cours cours;
    private double valeur;

    public Note(Etudiant etudiant, Cours cours, double valeur) {
        this.etudiant = etudiant;
        this.cours = cours;
        this.valeur = valeur;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Note [Etudiant=" + etudiant.getNom() +
                ", Cours=" + cours.getTitre() +
                ", Valeur=" + valeur + "]";
    }
}