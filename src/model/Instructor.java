package model;

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
        return "model.Instructor{id=" + getId() + ", nume=" + getNume() + "}";
    }

    public static class Intrebare {
        private int id;
        private String text;
        private String raspunsCorect;

        public Intrebare(int id, String text, String raspunsCorect) {
            this.id = id;
            this.text = text;
            this.raspunsCorect = raspunsCorect;
        }

        public int getId() { return id; }
        public String getText() { return text; }
        public String getRaspunsCorect() { return raspunsCorect; }

        @Override
        public String toString() {
            return "Instructor.Intrebare{id=" + id + ", text=" + text + "}";
        }
    }

    public static class Quiz {
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
            return "model.Instructor.Quiz{id=" + id + ", titlu=" + titlu + ", intrebari=" + intrebari.size() + "}";
        }
    }
}