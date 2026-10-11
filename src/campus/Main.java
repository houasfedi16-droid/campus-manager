package campus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Etudiant> etudiants = new ArrayList<>();
    static ArrayList<Professeur> professeurs = new ArrayList<>();
    static ArrayList<Cours> coursList = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    ajouterProfesseur();
                    break;
                case 3:
                    ajouterCours();
                    break;
                case 4:
                    inscrireEtudiant();
                    break;
                case 5:
                    ajouterNote();
                    break;
                case 6:
                    afficherEtudiants();
                    break;
                case 7:
                    afficherCours();
                    break;
                case 8:
                    moyenneEtudiant();
                    break;
                case 9:
                    moyenneCours();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);
    }

    public static void afficherMenu() {
        System.out.println("\n===== CAMPUS MANAGER =====");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Ajouter un professeur");
        System.out.println("3. Ajouter un cours");
        System.out.println("4. Inscrire un étudiant à un cours");
        System.out.println("5. Ajouter une note");
        System.out.println("6. Afficher les étudiants");
        System.out.println("7. Afficher les cours");
        System.out.println("8. Calculer moyenne étudiant");
        System.out.println("9. Calculer moyenne cours");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void ajouterEtudiant() {
        try {
            System.out.print("Nom : ");
            String nom = reader.readLine();

            System.out.print("Prénom : ");
            String prenom = reader.readLine();

            System.out.print("Email : ");
            String email = reader.readLine();

            System.out.print("Numéro étudiant : ");
            String numero = reader.readLine();

            Etudiant e = new Etudiant(nom, prenom, email, numero);
            etudiants.add(e);

            System.out.println("Étudiant ajouté avec succès.");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void ajouterProfesseur() {
        try {
            System.out.print("Nom : ");
            String nom = reader.readLine();

            System.out.print("Prénom : ");
            String prenom = reader.readLine();

            System.out.print("Email : ");
            String email = reader.readLine();

            System.out.print("Spécialité : ");
            String specialite = reader.readLine();

            Professeur p = new Professeur(nom, prenom, email, specialite);
            professeurs.add(p);

            System.out.println("Professeur ajouté avec succès.");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void ajouterCours() {
        try {
            if (professeurs.isEmpty()) {
                System.out.println("Aucun professeur disponible. Ajoutez d'abord un professeur.");
                return;
            }

            System.out.print("Titre du cours : ");
            String titre = reader.readLine();

            System.out.print("Code du cours : ");
            String code = reader.readLine();

            afficherProfesseurs();
            System.out.print("Choisir le numéro du professeur : ");
            int indexProf = scanner.nextInt();
            scanner.nextLine();

            if (indexProf < 0 || indexProf >= professeurs.size()) {
                System.out.println("Professeur invalide.");
                return;
            }

            Cours c = new Cours(titre, code, professeurs.get(indexProf));
            coursList.add(c);

            System.out.println("Cours ajouté avec succès.");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void inscrireEtudiant() {
        if (etudiants.isEmpty() || coursList.isEmpty()) {
            System.out.println("Il faut avoir au moins un étudiant et un cours.");
            return;
        }

        afficherEtudiants();
        System.out.print("Choisir le numéro de l'étudiant : ");
        int indexEtudiant = scanner.nextInt();

        afficherCours();
        System.out.print("Choisir le numéro du cours : ");
        int indexCours = scanner.nextInt();
        scanner.nextLine();

        if (indexEtudiant < 0 || indexEtudiant >= etudiants.size()
                || indexCours < 0 || indexCours >= coursList.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        try {
            coursList.get(indexCours).inscrireEtudiant(etudiants.get(indexEtudiant));
            System.out.println("Étudiant inscrit avec succès.");
        } catch (DuplicateEnrollmentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void ajouterNote() {
        if (etudiants.isEmpty() || coursList.isEmpty()) {
            System.out.println("Il faut avoir au moins un étudiant et un cours.");
            return;
        }

        afficherEtudiants();
        System.out.print("Choisir le numéro de l'étudiant : ");
        int indexEtudiant = scanner.nextInt();

        afficherCours();
        System.out.print("Choisir le numéro du cours : ");
        int indexCours = scanner.nextInt();

        System.out.print("Entrer la note : ");
        double valeur = scanner.nextDouble();
        scanner.nextLine();

        if (indexEtudiant < 0 || indexEtudiant >= etudiants.size()
                || indexCours < 0 || indexCours >= coursList.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Etudiant etudiant = etudiants.get(indexEtudiant);
        Cours cours = coursList.get(indexCours);

        if (!cours.getEtudiants().contains(etudiant)) {
            System.out.println("Erreur : cet étudiant n'est pas inscrit à ce cours.");
            return;
        }

        Note note = new Note(etudiant, cours, valeur);
        etudiant.ajouterNote(note);

        System.out.println("Note ajoutée avec succès.");
    }

    public static void afficherEtudiants() {
        System.out.println("\n===== LISTE DES ÉTUDIANTS =====");

        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant.");
            return;
        }

        for (int i = 0; i < etudiants.size(); i++) {
            System.out.println(i + ". " + etudiants.get(i));
        }
    }

    public static void afficherProfesseurs() {
        System.out.println("\n===== LISTE DES PROFESSEURS =====");

        if (professeurs.isEmpty()) {
            System.out.println("Aucun professeur.");
            return;
        }

        for (int i = 0; i < professeurs.size(); i++) {
            System.out.println(i + ". " + professeurs.get(i));
        }
    }

    public static void afficherCours() {
        System.out.println("\n===== LISTE DES COURS =====");

        if (coursList.isEmpty()) {
            System.out.println("Aucun cours.");
            return;
        }

        for (int i = 0; i < coursList.size(); i++) {
            System.out.println(i + ". " + coursList.get(i));
        }
    }

    public static void moyenneEtudiant() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant.");
            return;
        }

        afficherEtudiants();
        System.out.print("Choisir le numéro de l'étudiant : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= etudiants.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        double moyenne = etudiants.get(index).calculerMoyenne();
        System.out.println("Moyenne de l'étudiant : " + moyenne);
    }

    public static void moyenneCours() {
        if (coursList.isEmpty()) {
            System.out.println("Aucun cours.");
            return;
        }

        afficherCours();
        System.out.print("Choisir le numéro du cours : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= coursList.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        double moyenne = coursList.get(index).calculerMoyenneCours();
        System.out.println("Moyenne générale du cours : " + moyenne);
    }
}