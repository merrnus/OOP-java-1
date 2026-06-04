package service;

import model.*;
import repository.UtilizatorRepository;
import repository.CursRepository;
import repository.InscrierRepository;
import repository.LectieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PlatformaService {
    private List<Utilizator> utilizatori;
    private TreeSet<Curs> cursuri;
    private List<Inscriere> inscrieri;
    private AuditService audit = AuditService.getInstance();

    public PlatformaService() {
        this.utilizatori = new ArrayList<>();
        this.cursuri = new TreeSet<>();
        this.inscrieri = new ArrayList<>();
        loadFromDatabase(); // Incarca datele din baza de date la initializare
    }

    // Incarca toate datele din baza de date in memorie
    private void loadFromDatabase() {
        try {
            UtilizatorRepository utilizatorRepo = new UtilizatorRepository();
            CursRepository cursRepo = new CursRepository();
            InscrierRepository inscrierRepo = new InscrierRepository();

            // Incarca utilizatori, cursuri si inscrieri
            utilizatori.addAll(utilizatorRepo.findAll());
            cursuri.addAll(cursRepo.findAll());
            inscrieri.addAll(inscrierRepo.findAll());

            System.out.println("Date incarcate din baza de date.");
        } catch (Exception e) {
            System.out.println("Eroare la incarcare: " + e.getMessage());
        }
    }

    // 1. ADAUGA UTILIZATOR
    public void adaugaUtilizator(Utilizator u) {
        utilizatori.add(u);
        audit.log("adaugaUtilizator");
    }

    // 2. ADAUGA CURS
    public void adaugaCurs(Curs c) {
        cursuri.add(c);
        c.getInstructor().adaugaCurs(c);
        audit.log("adaugaCurs");
    }

    // 3. INSCRIE LA CURS
    public void inscrieCursant(Cursant cursant, Curs curs) {
        Inscriere inscriere = new Inscriere(inscrieri.size() + 1, cursant, curs);
        inscrieri.add(inscriere);
        cursant.adaugaInscriere(inscriere);
        audit.log("inscrieCursant");
    }

    // 4. TERMINA CURS
    public void finalizeazaCurs(Cursant cursant, Curs curs) {
        for (Inscriere i : inscrieri) {
            if (i.getCursant().getId() == cursant.getId() &&
                    i.getCurs().getId() == curs.getId()) {
                i.finalizeaza();
                System.out.println(cursant.getNume() + " a finalizat cursul " + curs.getTitlu());
                audit.log("finalizeazaCurs");
                return;
            }
        }
    }

    // 5. LIST TOATE CURSURI
    public void afiseazaCursuri() {
        System.out.println("=== Cursuri (sortate dupa id) ===");
        for (Curs c : cursuri) {
            System.out.println(c);
        }
        audit.log("afiseazaCursuri");
    }

    // 6. LIST CURSURI LUI CURSANT
    public void afiseazaCursuriCursant(Cursant cursant) {
        System.out.println("=== Cursurile lui " + cursant.getNume() + " ===");
        for (Inscriere i : cursant.getInscrieri()) {
            System.out.println(i.getCurs().getTitlu() + " - finalizat: " + i.isFinalizat());
        }
        audit.log("afiseazaCursuriCursant");
    }

    // 7. LIST TOATE UTILIZATORI
    public void afiseazaUtilizatori() {
        System.out.println("=== Utilizatori ===");
        for (Utilizator u : utilizatori) {
            System.out.println(u);
        }
        audit.log("afiseazaUtilizatori");
    }

    // 8. ADAUGA LECTIE LA CURS
    public void adaugaLectie(Curs curs, Lectie lectie) {
        curs.adaugaLectie(lectie);
        System.out.println("model.Lectie adaugata: " + lectie.getTitlu() + " la cursul " + curs.getTitlu());
        audit.log("adaugaLectie");
    }

    // 9. ADAUGA QUIZ LA CURS
    public void adaugaQuiz(Curs curs, Quiz quiz) {
        curs.adaugaQuiz(quiz);
        System.out.println("model.Instructor.Quiz adaugat: " + quiz.getTitlu() + " la cursul " + curs.getTitlu());
        audit.log("adaugaQuiz");
    }

    // 10. ARATA INTREBARI QUIZ
    public void afiseazaIntrebariQuiz(Quiz quiz) {
        System.out.println("=== Intrebari model.Instructor.Quiz: " + quiz.getTitlu() + " ===");
        for (Intrebare i : quiz.getIntrebari()) {
            System.out.println(i.getText());
        }
        audit.log("afiseazaIntrebariQuiz");
    }

    public List<Inscriere> getInscrieri() {
        return inscrieri;
    }

    public Utilizator getUtilizatorById(int id) {
        for (Utilizator u : utilizatori) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public Curs getCursById(int id) {
        for (Curs c : cursuri) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}