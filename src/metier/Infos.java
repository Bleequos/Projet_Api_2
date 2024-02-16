package metier;

public class Infos {

    protected int nbreHeures;

    protected Cours cours;

    protected Salle salle;

    protected Enseignant enseignant;


    public Infos(int nbreHeures, Cours cours, Salle salle, Enseignant enseignant) {
        this.nbreHeures = nbreHeures;
        this.cours = cours;
        this.salle = salle;
        this.enseignant = enseignant;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Salle getSalle() {
        return salle;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public int getNbreHeures() {
        return nbreHeures;
    }

    public Cours getCours() {
        return cours;
    }
}
