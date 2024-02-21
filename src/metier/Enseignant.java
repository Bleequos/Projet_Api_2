package metier;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe métier de gestion d'un enseignant
 * @author Ahmed Al robaie
 * @version 1.0
 */

public class Enseignant {



    /**
     * Compteur de l'Id des enseignants
     */

    private static int CompteurIdEnseignant = 0;


    /**
     * identifiant de l'Enseignant
     */
    protected int idEnseignant;


    /**
     * Matricule de l'Enseignant
     */
    protected String matricule;

    /**
     * Nom de l'Enseignant
     */

    protected String nom;

    /**
     * Prenom de l'Enseignant
     */

    protected String prenom;

    /**
     * Téléphone de l'Enseignant
     */

    protected String tel;

    /**
     * chargeSem de l'Enseignant
     */
// je ne sais pas ce que cela veut dire
    protected int chargeSem;


    /**
     * Salaire mensuel de l'Enseignant
     */

    protected BigDecimal salaireMensu;

    /**
     * date engage de l'Enseignant
     */

    protected LocalDate dateEngag;

    /**
     * setter id de l'enseignant
     *
     * @param idEnseignant id de l'enseignant
     */

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    /**
     * Setter matricule de l'enseignant
     *
     * @param matricule matricule de l'enseignant
     */

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Setter nom de l'enseignant
     *
     * @param nom nom de l'enseignant
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * setter Prenom de l'enseignant
     *
     * @param prenom prenom de l'enseignant
     */


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Setter Téléphone de l'enseignant
     *
     * @param tel Téléphone de l'enseignant
     */

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * setter ChargeSem de l'enseignant
     *
     * @param chargeSem ChargeSem de l'enseignant
     */

    public void setChargeSem(int chargeSem) {
        this.chargeSem = chargeSem;
    }

    /**
     * Setter Salaire mensuel de l'enseignant
     *
     * @param salaireMensu Salaire mensuel de l'enseignant
     */

    public void setSalaireMensu(BigDecimal salaireMensu) {
        this.salaireMensu = salaireMensu;
    }

    /**
     * setter Date d'engagement de l'enseignant
     *
     * @param dateEngag Date d'engagement de l'enseignant
     */

    public void setDateEngag(LocalDate dateEngag) {
        this.dateEngag = dateEngag;
    }

    /**
     * getter IdEnseignant
     *
     * @return idEnseignant
     */
    public int getIdEnseignant() {
        return idEnseignant;
    }

    /**
     * getter Matricule
     *
     * @return matricule
     */

    public String getMatricule() {
        return matricule;
    }

    /**
     * getter Nom
     *
     * @return nom
     */

    public String getNom() {
        return nom;
    }

    /**
     * getter Nom
     *
     * @return nom
     */

    public String getPrenom() {
        return prenom;
    }

    /**
     * Getter Téléphone
     *
     * @return tel
     */

    public String getTel() {
        return tel;
    }

    /**
     * getter ChargeSem
     *
     * @return chargeSem
     */

    public int getChargeSem() {
        return chargeSem;
    }

    /**
     * getter Salaire mensuel
     *
     * @return salaireMensu
     */

    public BigDecimal getSalaireMensu() {
        return salaireMensu;
    }

    /**
     * getter Date d'engagement
     *
     * @return dateEngag
     */

    public LocalDate getDateEngag() {
        return dateEngag;
    }

    /**
     * Constructeur paramétré
     * idEnseignant identifiant unique de l'enseignant, affecté par la base de
     * données, increment de 1 à chaque fois grace CompteurIdEnseignant
     * @param matricule matricule de l'enseignant
     * @param nom nom de l'enseignant
     * @param prenom prenom de l'enseignant
     * @param tel téléphone de l'enseignant
     * @param chargeSem chargeSem de l'enseignant
     * @param salaireMensu salaireMensu de l'enseignant
     * @param dateEngag date d'engagement de l'enseignant
     */

    public Enseignant( String matricule, String nom, String prenom, String tel, int chargeSem, BigDecimal salaireMensu, LocalDate dateEngag) {
        this.idEnseignant = CompteurIdEnseignant++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSem = chargeSem;
        this.salaireMensu = salaireMensu;
        this.dateEngag = dateEngag;
    }
}
