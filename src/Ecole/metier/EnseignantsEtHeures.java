package Ecole.metier;

public class EnseignantsEtHeures {
    protected Enseignant enseignant;

    protected int nbreHeures;

    public EnseignantsEtHeures(Enseignant enseignant, int nbreHeures) {
        this.enseignant = enseignant;
        this.nbreHeures = nbreHeures;
    }

    @Override
    public String toString() {
        return "EnseignantsEtHeures{" +
                "enseignant=" + enseignant +
                ", heures=" + nbreHeures +
                '}';
    }
}
