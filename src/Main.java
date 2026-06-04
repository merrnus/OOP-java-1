import config.DatabaseConnection;
import service.PlatformaService;
import service.PlatformaCLI;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Verificare conexiune la baza de date
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            System.out.println("Conexiune reusita!");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
            return;
        }

        // Initializare serviciu si pornire CLI
        try {
            PlatformaService service = new PlatformaService();
            new PlatformaCLI(service).run();
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}