
package designpatterns.composite;

import Ecole.metier.Infos;
import Ecole.metier.Salle;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe métier de gestion de classe
 * @author Ahmed Al robaie
 * @version 1.0
 * @see Ecole.metier.Infos
 * @see Ecole.metier.Salle
 */


public class Classe extends Element{

    /**
     * Compteur de l'Id des Classes
     */

    private static int CompteurIdClasses = 0;

    /**
     * identifiant de la classe
     */

    protected int idClasse;

    /**
     * sigle de la classe
     */

    protected String sigle;

    /**
     * Annee de la classe
     */

    protected int annee;

    /**
     * specialite de la classe
     */

    protected String specialite;

    /**
     * Nombre d'élèves de la classe
     */

    protected int nbreEleves;

    /**
     * Salle par default de la classe
     */

    protected Salle salleParDefault;

    /**
     * Ensemble des infos concernant la classe
     */

    protected List<Infos> infos = new ArrayList<>();

    public Classe(int idClasse, String sigle, int annee, String specialite, int nbreEleves, Salle salleParDefault) {
        super(idClasse);
        this.idClasse = idClasse;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleves = nbreEleves;
        this.salleParDefault = salleParDefault;
    }

    public int getIdClasse() {
        return idClasse;
    }

    /**
     * Getter Sigle de la classe
     *
     * @return sigle
     */

    public String getSigle() {
        return sigle;
    }


    /**
     * Getter Année de la classe
     *
     * @return annee
     */
    public int getAnnee() {
        return annee;
    }


    /**
     * Getter specialite de la classe
     *
     * @return specialite
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * Getter Nombres d'élèves de la classe
     *
     * @return nbreEleves
     */
    public int getNbreEleves() {
        return nbreEleves;
    }

    /**
     * Getter de la salle par default de la classe
     *
     * @return salleParDefault
     */

    public Salle getSalleParDefault() {
        return salleParDefault;
    }

    /**
     * méthode toString
     *
     * @return informations complètes
     */

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", sigle='" + sigle + '\'' +
                ", annee=" + annee +
                ", specialite='" + specialite + '\'' +
                ", nbreEleves=" + nbreEleves +
                ", salleParDefault=" + salleParDefault +
                '}';
    }

    /**
     * setter Id de la classe
     *
     * @param idClasse id de classe
     */
    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    /**
     * setter sigle de la classe
     *
     * @param sigle sigle de la classe
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Setter Année de la classe
     *
     * @param annee Année de la classe
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Setter specialite de la classe
     *
     * @param specialite specialite de la classe
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    /**
     * Setter Nombre d'élèves de la classe
     *
     * @param nbreEleves Nombre d'élèves de la classe
     */
    public void setNbreEleves(int nbreEleves) {
        this.nbreEleves = nbreEleves;
    }

    /**
     * Setter de la salle par default de la classe
     *
     * @param salleParDefault une salle par default de la classe
     */

    public void setSalleParDefault(Salle salleParDefault) {
        this.salleParDefault = salleParDefault;
    }

//pas sur de la documentation pour la salle par default

    /**
     * Getter liste d'info de la classe
     *
     * @return infos
     */
    public List<Infos> getInfos() {
        return infos;
    }


    /**
     * Setter une info dans la liste d'info
     *
     * @param infos une info dans la liste d'info
     */
    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }

    /**
     * Constructeur paramétré
     * idClasse identifiant unique de la classe, affecté par la base de
     * données, increment de 1 à chaque fois grace CompteurIdClasses
     *
     * @param sigle      sigle de la classe
     * @param annee      année de la classe
     * @param specialite specialité de la classe
     * @param nbreEleves nombre d'eleves de la classe
     */
    public Classe(String sigle, int annee, String specialite, int nbreEleves) {
        super(CompteurIdClasses);
        this.idClasse = CompteurIdClasses++;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleves = nbreEleves;
    }

    @Override
    public int TotNbrEleves() {
        return nbreEleves;
    }
}
