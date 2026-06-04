package model;

import java.util.ArrayList;
import java.util.List;

public class Cursant extends Utilizator {
    private List<Inscriere> inscrieri;

    public Cursant(int id, String nume, String email) {
        super(id, nume, email);
        this.inscrieri = new ArrayList<>();
    }

    public List<Inscriere> getInscrieri() { return inscrieri; }
    public void adaugaInscriere(Inscriere i) { inscrieri.add(i); }

    @Override
    public String toString() {
        return "Cursant{id=" + getId() + ", nume=" + getNume() + "}";
    }
}