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
        System.out.println("Lectie adaugata: " + lectie.getTitlu() + " la cursul " + curs.getTitlu());
        audit.log("adaugaLectie");
    }

    // 9. ADAUGA QUIZ LA CURS
    public void adaugaQuiz(Curs curs, Quiz quiz) {
        curs.adaugaQuiz(quiz);
        System.out.println("Quiz adaugat: " + quiz.getTitlu() + " la cursul " + curs.getTitlu());
        audit.log("adaugaQuiz");
    }

    // 10. ARATA INTREBARI QUIZ
    public void afiseazaIntrebariQuiz(Quiz quiz) {
        System.out.println("=== Intrebari Quiz: " + quiz.getTitlu() + " ===");
        for (Intrebare i : quiz.getIntrebari()) {
            System.out.println(i.getText());
        }
        audit.log("afiseazaIntrebariQuiz");
    }

    public List<Inscriere> getInscrieri() {
        return inscrieri;
    }
}