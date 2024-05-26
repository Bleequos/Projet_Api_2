package designpatterns.builder;
import Ecole.metier.Infos;
import Ecole.metier.Salle;

import java.util.ArrayList;
import java.util.List;



/**
 * Classe métier de gestion de classe
 * @author Ahmed Al robaie
 * @version 1.0
 * @see Infos
 * @see Salle
 */


public class Classe {

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




    /**
 * constructeur paramétré
 * @param cb objet de la classe ClasseBuilder contenant les informations d'initialisation
 */

   private Classe(ClasseBuilder cb) {
        this.idClasse = cb.idClasse;
        this.sigle = cb.sigle;
        this.annee = cb.annee;
        this.specialite = cb.specialite;
        this.nbreEleves = cb.nbreEleves;
        this.salleParDefault = cb.salleParDefault;
    }


    /**
     * Getter IdClasse
     *
     * @return idClasse
     */
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
     * setter Id de la classe
     *
     * @param idClasse id de classe
     */
   

 /**
 * méthode toString
 * @return informations complètes
 */
    @Override public String toString() {
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
     * Getter liste d'info de la classe
     *
     * @return infos
     */
    public List<Infos> getInfos() {
        return infos;
    }


  
    
    public static class ClasseBuilder{
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

       // protected List<Infos> infos = new ArrayList<>();

        public ClasseBuilder setIdClasse(int idClasse) {
            this.idClasse = idClasse;
            return this;
        }

        public ClasseBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public ClasseBuilder setAnnee(int annee) {
            this.annee = annee;
            return this;
        }

        public ClasseBuilder setSpecialite(String specialite) {
            this.specialite = specialite;
            return this;
        }

        public ClasseBuilder setNbreEleves(int nbreEleves) {
            this.nbreEleves = nbreEleves;
            return this;
        }

        public ClasseBuilder setSalleParDefault(Salle salleParDefault) {
            this.salleParDefault = salleParDefault;
            return this;
        }

        public Classe build() throws Exception{
            if(idClasse<=0 || sigle==null || annee==0) throw new Exception("informations de construction incomplètes");
            return new Classe(this);
        }


}
    
}
