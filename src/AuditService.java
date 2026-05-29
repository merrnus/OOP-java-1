import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String FILE_PATH = "audit.csv";
    private static AuditService instance;

    private AuditService() {}

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void log(String actiune) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(actiune + ", " + timestamp + "\n");
        } catch (IOException e) {
            System.out.println("Eroare audit: " + e.getMessage());
        }
    }
}