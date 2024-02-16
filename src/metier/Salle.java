package metier;

import java.util.ArrayList;
import java.util.List;

public class Salle {

    protected int idSalle;

    protected String sigle;

    protected int capacite;

    protected List<Classe> listeClasse = new ArrayList<>();

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public String getSigle() {
        return sigle;
    }

    public int getCapacite() {
        return capacite;
    }

    public Salle(int idSalle, String sigle, int capacite) {
        this.idSalle = idSalle;
        this.sigle = sigle;
        this.capacite = capacite;
    }
}
