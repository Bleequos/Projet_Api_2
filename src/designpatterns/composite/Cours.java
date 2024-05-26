package designpatterns.composite;



import java.util.Objects;

/**
 * Classe métier de gestion d'un cours
 * @author Ahmed Al robaie
 * @version 1.0
 */

public class Cours {

    /**
     * Compteur de l'Id des cours
     */

    private static int CompteurIdCours = 0;

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
     * Constructeur paramétré
     *  idCours identifiant unique du cours, affecté par la base de
     * données, increment de 1 à chaque fois grace CompteurIdCours
     * @param code code du cours
     * @param intitule intitule du cours
     */

    public Cours(String code, String intitule) {
        this.idCours = CompteurIdCours++;
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
     * Méthode de vérification d'égalité de deux cours
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

    public Cours(int idCours, String code, String intitule) {
        this.idCours = idCours;
        this.code = code;
        this.intitule = intitule;
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
     * méthode toString
     * @return informations complètes
     */

    @Override
    public String toString() {
        return "Cours{" +
                "idCours=" + idCours +
                ", code='" + code + '\'' +
                ", intitule='" + intitule + '\'' +
                '}';
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
