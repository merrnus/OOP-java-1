
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {




        // JDBC test - sonra silinecek
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Conexiune reusita!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }



        // Etapa I kodu
        PlatformaService service = new PlatformaService();

        // Instructor
        Instructor instructor1 = new Instructor(1, "Ion Popescu", "ion@email.com");
        service.adaugaUtilizator(instructor1);

        // Cursant
        Cursant cursant1 = new Cursant(2, "Ana Maria", "ana@email.com");
        Cursant cursant2 = new Cursant(3, "Mihai Ion", "mihai@email.com");
        service.adaugaUtilizator(cursant1);
        service.adaugaUtilizator(cursant2);

        // Curs
        Curs curs1 = new Curs(1, "Java Basics", instructor1);
        Curs curs2 = new Curs(2, "Python Intro", instructor1);
        service.adaugaCurs(curs1);
        service.adaugaCurs(curs2);

        // Lectie
        Lectie lectie1 = new Lectie(1, "Variables", "Despre variabile in Java");
        Lectie lectie2 = new Lectie(2, "Loops", "Despre bucle in Java");
        service.adaugaLectie(curs1, lectie1);
        service.adaugaLectie(curs1, lectie2);

        // Quiz
        Quiz quiz1 = new Quiz(1, "Quiz Java Basics");
        quiz1.adaugaIntrebare(new Intrebare(1, "Ce este o variabila?", "O locatie de memorie"));
        quiz1.adaugaIntrebare(new Intrebare(2, "Ce este un loop?", "O structura repetitiva"));
        service.adaugaQuiz(curs1, quiz1);

        // Inscrie
        service.inscrieCursant(cursant1, curs1);
        service.inscrieCursant(cursant1, curs2);
        service.inscrieCursant(cursant2, curs1);

        // Termina Curs
        service.finalizeazaCurs(cursant1, curs1);

        // List
        service.afiseazaUtilizatori();
        service.afiseazaCursuri();
        service.afiseazaCursuriCursant(cursant1);
        service.afiseazaIntrebariQuiz(quiz1);

        // Repository test
        try {
            UtilizatorRepository repo = new UtilizatorRepository();

            // database'e yaz
            repo.save(instructor1);
            repo.save(cursant1);
            repo.save(cursant2);

            // database'den oku
            System.out.println("=== Din baza de date ===");
            for (Utilizator u : repo.findAll()) {
                System.out.println(u);
            }
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        // CursRepository test
        try {
            CursRepository cursRepo = new CursRepository();

            // database'e yaz
            cursRepo.save(curs1);
            cursRepo.save(curs2);

            // database'den oku
            System.out.println("=== Cursuri din baza de date ===");
            for (String c : cursRepo.findAll()) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        // InscrierRepository test
        try {
            InscrierRepository inscrierRepo = new InscrierRepository();

            inscrierRepo.save(service.getInscrieri().get(0));
            inscrierRepo.save(service.getInscrieri().get(1));
            inscrierRepo.save(service.getInscrieri().get(2));

            System.out.println("=== Inscrieri din baza de date ===");
            for (String i : inscrierRepo.findAll()) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        // LectieRepository test
        try {
            LectieRepository lectieRepo = new LectieRepository();

            lectieRepo.save(lectie1, curs1.getId());
            lectieRepo.save(lectie2, curs1.getId());

            System.out.println("=== Lectii din baza de date ===");
            for (String l : lectieRepo.findAll()) {
                System.out.println(l);
            }
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}