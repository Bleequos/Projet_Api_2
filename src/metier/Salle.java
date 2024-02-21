package metier;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe métier de gestion d'une salle
 * @author Ahmed Al robaie
 * @version 1.0
 * @see Classe
 */

public class Salle {

    /**
     * identifiant de la salle
     */
    protected int idSalle;

    /**
     * sigle de la salle
     */

    protected String sigle;

    /**
     * Capacité de la salle
     */

    protected int capacite;

    /**
     * Ensemble des classes de la salle
     */

    protected List<Classe> listeClasse = new ArrayList<>();

    /**
     * setter ID de la salle
     *
     * @param idSalle id de la salle
     */

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    /**
     * setter sigle de la salle
     *
     * @param sigle sigle de la salle
     */

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * setter capacité de la salle
     *
     * @param capacite capacite de la salle
     */

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * getter IdSalle
     *
     * @return idSalle
     */

    public int getIdSalle() {
        return idSalle;
    }

    /**
     * getter Sigle
     *
     * @return sigle
     */

    public String getSigle() {
        return sigle;
    }

    /**
     * getter
     *
     * @return capacite
     */

    public int getCapacite() {
        return capacite;
    }

    /**
     * constructeur paramétré
     * @param idSalle identifiant unique de la salle, affecté par la base de
     * données
     * @param sigle sigle de la salle
     * @param capacite capacite de la salle
     */

    public Salle(int idSalle, String sigle, int capacite) {
        this.idSalle = idSalle;
        this.sigle = sigle;
        this.capacite = capacite;
    }
}
