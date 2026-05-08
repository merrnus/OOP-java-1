import java.util.ArrayList;
import java.util.List;

public class Instructor extends Utilizator {
    private List<Curs> cursuri;

    public Instructor(int id, String nume, String email) {
        super(id, nume, email);
        this.cursuri = new ArrayList<>();
    }

    public List<Curs> getCursuri() { return cursuri; }
    public void adaugaCurs(Curs c) { cursuri.add(c); }

    @Override
    public String toString() {
        return "Instructor{id=" + getId() + ", nume=" + getNume() + "}";
    }
}