package model;

import java.util.ArrayList;
import java.util.List;

public class Curs implements Comparable<Curs> {
    private int id;
    private String titlu;
    private Instructor instructor;
    private List<Lectie> lectii;
    private List<Quiz> quizuri;

    public Curs(int id, String titlu, Instructor instructor) {
        this.id = id;
        this.titlu = titlu;
        this.instructor = instructor;
        this.lectii = new ArrayList<>();
        this.quizuri = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public Instructor getInstructor() { return instructor; }
    public List<Lectie> getLectii() { return lectii; }
    public List<Quiz> getQuizuri() { return quizuri; }
    public void adaugaLectie(Lectie l) { lectii.add(l); }
    public void adaugaQuiz(Quiz q) { quizuri.add(q); }

    @Override
    public int compareTo(Curs other) {
        return Integer.compare(this.id, other.id); // id'ye göre sıralı
    }

    @Override
    public String toString() {
        return "Curs{id=" + id + ", titlu=" + titlu + ", instructor=" + instructor.getNume() + "}";
    }
}