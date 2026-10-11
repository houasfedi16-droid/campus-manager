package campus;

import java.util.ArrayList;

public class Cours {
    private String titre;
    private String code;
    private Professeur professeur;
    private ArrayList<Etudiant> etudiants;

    public Cours(String titre, String code, Professeur professeur) {
        this.titre = titre;
        this.code = code;
        this.professeur = professeur;
        this.etudiants = new ArrayList<>();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void inscrireEtudiant(Etudiant etudiant) throws DuplicateEnrollmentException {
        if (etudiants.contains(etudiant)) {
            throw new DuplicateEnrollmentException("Cet étudiant est déjà inscrit à ce cours.");
        }

        etudiants.add(etudiant);
    }

    public double calculerMoyenneCours() {
        double somme = 0;
        int compteur = 0;

        for (Etudiant etudiant : etudiants) {
            for (Note note : etudiant.getNotes()) {
                if (note.getCours() == this) {
                    somme += note.getValeur();
                    compteur++;
                }
            }
        }

        if (compteur == 0) {
            return 0;
        }

        return somme / compteur;
    }

    @Override
    public String toString() {
        return "Cours [Titre=" + titre +
                ", Code=" + code +
                ", Professeur=" + professeur.getNom() + " " + professeur.getPrenom() + "]";
    }
}