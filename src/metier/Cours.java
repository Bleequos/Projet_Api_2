package metier;

import java.util.Objects;

/**
 * classe métier de gestion d'un cours
 * @author Ahmed Al robaie
 * @version 1.0
 */

public class Cours {

    /**
     * Identifiant de cours
     */
    protected int idCours ;

    /**
     * Code de cours
     */

    protected String code ;

    /**
     * Intitule de cours
     */

    protected String intitule;

    /**
     * Setter Code du cours
     *
     * @param code code du cours
     */

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Setter Code de l'Intitule du cours
     *
     * @param intitule intitule du cours
     */

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * getter Code
     *
     * @return code
     */

    public String getCode() {
        return code;
    }

    /**
     * getter Intitule
     *
     * @return intitule
     */

    public String getIntitule() {
        return intitule;
    }

    /**
     * constructeur paramétré
     * @param idCours identifiant unique du cours, affecté par la base de
     * données
     * @param code code du cours
     * @param intitule intitule du cours
     */

    public Cours(int idCours, String code, String intitule) {
        this.idCours = idCours;
        this.code = code;
        this.intitule = intitule;
    }

    /**
     * Setter Id du cours
     *
     * @param idCours ID du cours
     */

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }



    /**
     * méthode de vérification d'égalité de deux cours
     * @param o  autre cours
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return idCours == cours.idCours && Objects.equals(code, cours.code);
    }


    /**
     * Calcul du hashcode du cours
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(idCours, code);
    }

    /**
     * getter IdCours
     *
     * @return IdCours
     */

    public int getIdCours() {
        return idCours;
    }
}
