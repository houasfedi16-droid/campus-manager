package campus;

import java.util.ArrayList;

public class Etudiant extends Personne {
    private String numeroEtudiant;
    private ArrayList<Note> notes;

    public Etudiant(String nom, String prenom, String email, String numeroEtudiant) {
        super(nom, prenom, email);
        this.numeroEtudiant = numeroEtudiant;
        this.notes = new ArrayList<>();
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void ajouterNote(Note note) {
        notes.add(note);
    }

    public double calculerMoyenne() {
        if (notes.isEmpty()) {
            return 0;
        }

        double somme = 0;
        for (Note note : notes) {
            somme += note.getValeur();
        }

        return somme / notes.size();
    }

    @Override
    public String toString() {
        return "Etudiant [Nom=" + getNom() +
                ", Prenom=" + getPrenom() +
                ", Email=" + getEmail() +
                ", Numero=" + numeroEtudiant + "]";
    }
}