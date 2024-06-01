package Ecole.metier;

public class CoursEtHeures {
    protected int nbreHeures;

    protected Cours cours;

    public CoursEtHeures(int nbreHeures, Cours cours) {
        this.nbreHeures = nbreHeures;
        this.cours = cours;
    }

    @Override
    public String toString() {
        return "CoursEtHeures{" +
                "nbreHeures=" + nbreHeures +
                ", cours=" + cours +
                '}';
    }
}
