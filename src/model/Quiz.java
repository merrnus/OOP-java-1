package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String titlu;
    private List<Intrebare> intrebari;

    public Quiz(int id, String titlu) {
        this.id = id;
        this.titlu = titlu;
        this.intrebari = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public List<Intrebare> getIntrebari() { return intrebari; }
    public void adaugaIntrebare(Intrebare i) { intrebari.add(i); }

    @Override
    public String toString() {
        return "Quiz{id=" + id + ", titlu=" + titlu + ", intrebari=" + intrebari.size() + "}";
    }
}