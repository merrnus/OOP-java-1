public class Main {
    public static void main(String[] args) {
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
    }
}