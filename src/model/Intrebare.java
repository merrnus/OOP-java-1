package model;

public class Intrebare {
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
        return "Intrebare{id=" + id + ", text=" + text + "}";
    }
}