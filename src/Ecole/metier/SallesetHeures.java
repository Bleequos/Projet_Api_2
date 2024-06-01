package Ecole.metier;

public class SallesetHeures {

    protected int nbreHeures;

    protected Salle salle;

    public SallesetHeures(int nbreHeures, Salle salle) {
        this.nbreHeures = nbreHeures;
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "SallesetHeures{" +
                "nbreHeures=" + nbreHeures +
                ", salle=" + salle +
                '}';
    }
}
