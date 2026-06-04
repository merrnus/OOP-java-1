package model;

public class Lectie {
    private int id;
    private String titlu;
    private String continut;

    public Lectie(int id, String titlu, String continut) {
        this.id = id;
        this.titlu = titlu;
        this.continut = continut;
    }

    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public String getContinut() { return continut; }

    @Override
    public String toString() {
        return "model.Lectie{id=" + id + ", titlu=" + titlu + "}";
    }
}