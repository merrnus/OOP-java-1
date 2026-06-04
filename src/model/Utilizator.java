package model;

public class Utilizator {
    private int id;
    private String nume;
    private String email;

    public Utilizator(int id, String nume, String email) {
        this.id = id;
        this.nume = nume;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNume() { return nume; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Utilizator{id=" + id + ", nume=" + nume + ", email=" + email + "}";
    }
}