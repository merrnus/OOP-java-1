package service;

import model.*;
import repository.*;
import java.util.Scanner;

public class PlatformaCLI {
    private final PlatformaService service;
    private final Scanner scanner;
    private final UtilizatorRepository utilizatorRepo;
    private final CursRepository cursRepo;
    private final InscrierRepository inscrierRepo;

    public PlatformaCLI(PlatformaService service) throws Exception {
        this.service = service;
        this.scanner = new Scanner(System.in);
        this.utilizatorRepo = new UtilizatorRepository();
        this.cursRepo = new CursRepository();
        this.inscrierRepo = new InscrierRepository();
    }

    public void run() {
        int optiune = -1;
        while (optiune != 0) {
            System.out.println("\n=== Platforma E-Learning ===");
            System.out.println("1  - Adauga instructor");
            System.out.println("2  - Adauga cursant");
            System.out.println("3  - Adauga curs");
            System.out.println("4  - Inscrie cursant la curs");
            System.out.println("5  - Finalizeaza curs");
            System.out.println("6  - Afiseaza toti utilizatorii");
            System.out.println("7  - Afiseaza toate cursurile");
            System.out.println("8  - Afiseaza cursurile unui cursant");
            System.out.println("0  - Iesire");
            System.out.print("Optiunea ta: ");
            optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1 -> adaugaInstructor();
                case 2 -> adaugaCursant();
                case 3 -> adaugaCurs();
                case 4 -> inscrieCursant();
                case 5 -> finalizeazaCurs();
                case 6 -> service.afiseazaUtilizatori();
                case 7 -> service.afiseazaCursuri();
                case 8 -> afiseazaCursuriCursant();
                case 0 -> System.out.println("La revedere!");
                default -> System.out.println("Optiune invalida!");
            }
        }
        scanner.close();
    }

    private void adaugaInstructor() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Nume: ");
            String nume = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            Instructor instructor = new Instructor(id, nume, email);
            service.adaugaUtilizator(instructor);
            utilizatorRepo.save(instructor);
            System.out.println("Instructor adaugat!");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void adaugaCursant() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Nume: ");
            String nume = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            Cursant cursant = new Cursant(id, nume, email);
            service.adaugaUtilizator(cursant);
            utilizatorRepo.save(cursant);
            System.out.println("Cursant adaugat!");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void adaugaCurs() {
        try {
            System.out.print("ID curs: ");
            int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Titlu: ");
            String titlu = scanner.nextLine();
            System.out.print("ID instructor: ");
            int instrId = scanner.nextInt(); scanner.nextLine();
            Instructor instructor = (Instructor) service.getUtilizatorById(instrId);
            if (instructor == null) {
                System.out.println("Instructorul nu a fost gasit!");
                return;
            }
            Curs curs = new Curs(id, titlu, instructor);
            service.adaugaCurs(curs);
            cursRepo.save(curs);
            System.out.println("Curs adaugat!");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void inscrieCursant() {
        try {
            System.out.print("ID cursant: ");
            int cursantId = scanner.nextInt(); scanner.nextLine();
            System.out.print("ID curs: ");
            int cursId = scanner.nextInt(); scanner.nextLine();
            Cursant cursant = (Cursant) service.getUtilizatorById(cursantId);
            Curs curs = service.getCursById(cursId);
            if (cursant == null || curs == null) {
                System.out.println("Cursant sau curs negasit!");
                return;
            }
            service.inscrieCursant(cursant, curs);
            inscrierRepo.save(service.getInscrieri().get(service.getInscrieri().size() - 1));
            System.out.println("Cursant inscris!");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void finalizeazaCurs() {
        try {
            System.out.print("ID cursant: ");
            int cursantId = scanner.nextInt(); scanner.nextLine();
            System.out.print("ID curs: ");
            int cursId = scanner.nextInt(); scanner.nextLine();
            Cursant cursant = (Cursant) service.getUtilizatorById(cursantId);
            Curs curs = service.getCursById(cursId);
            if (cursant == null || curs == null) {
                System.out.println("Cursant sau curs negasit!");
                return;
            }
            service.finalizeazaCurs(cursant, curs);
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void afiseazaCursuriCursant() {
        try {
            System.out.print("ID cursant: ");
            int id = scanner.nextInt(); scanner.nextLine();
            Cursant cursant = (Cursant) service.getUtilizatorById(id);
            if (cursant == null) {
                System.out.println("Cursant negasit!");
                return;
            }
            service.afiseazaCursuriCursant(cursant);
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}