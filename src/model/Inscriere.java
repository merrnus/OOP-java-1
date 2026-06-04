package model;

public class Inscriere {
    private int id;
    private Cursant cursant;
    private Curs curs;
    private boolean finalizat;

    public Inscriere(int id, Cursant cursant, Curs curs) {
        this.id = id;
        this.cursant = cursant;
        this.curs = curs;
        this.finalizat = false;
    }

    public int getId() { return id; }
    public Cursant getCursant() { return cursant; }
    public Curs getCurs() { return curs; }
    public boolean isFinalizat() { return finalizat; }
    public void finalizeaza() { this.finalizat = true; }

    @Override
    public String toString() {
        return "Inscriere{cursant=" + cursant.getNume() + ", curs=" + curs.getTitlu() + ", finalizat=" + finalizat + "}";
    }
}