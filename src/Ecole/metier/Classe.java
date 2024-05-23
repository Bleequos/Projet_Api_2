package Ecole.metier;
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
  * Calcul le nombre d'heures total d'un cours
  * @return HeuresTot
  */

 public int nbreHeuresTot(){
  int HeuresTot=0;
  for(Infos info : infos){
   HeuresTot=HeuresTot+info.getNbreHeures();
  }
  return HeuresTot;
 }

 /**
  * Récupérer liste des enseignants et heures
  * @return liste des enseignants et heures
  */
//pas sur de la documentation ici
 public ArrayList<EnseignantsEtHeures> listeEnseignantsEtHeures(){
  ArrayList<EnseignantsEtHeures> liste = new ArrayList<>();
  for(Infos info : infos){
   liste.add(new EnseignantsEtHeures(info.getEnseignant(),info.getNbreHeures()) );
  }
  return liste;
 }
 /**
  * Récupérer liste des Salles et Heures
  * @return liste des Salles et Heures
  */
 public ArrayList<SallesetHeures> listeSallesetHeures(){
  ArrayList<SallesetHeures> liste = new ArrayList<>();
  for(Infos info : infos){
   liste.add(new SallesetHeures(info.getNbreHeures(),info.getSalle()));
  }
  return liste;
 }


 /**
  * Récupérer liste des enseignants et heures
  * @return liste des cours et heures
  */
 public ArrayList<CoursEtHeures>  listeCoursEtHeures(){
  ArrayList<CoursEtHeures> liste = new ArrayList<>();
  for(Infos info : infos){
   liste.add(new CoursEtHeures(info.getNbreHeures(),info.getCours()));
  }
  return liste;
 }


 /**
  * Vérification de la capacité d'une classe
  * @return true ou false s'il y a de place ou pas
  */
 public boolean salleCapaciteOK(Salle salle){
  return nbreEleves <= salle.capacite;
 }//utilisation de chatpgt car voulait reduire l'if en condionnel mais au final c'etait pas nécessaire


 /**
  * Ajout d'un cours
  * @param cours cours à ajouter
  * @param heure nombre d'heures
  * @return ajout effectué ou pas
  */
 public boolean addCours(Cours cours,int heure){
  if (infos.indexOf(new Infos(heure, cours)) != -1) {    // j'aurais pu utiliser contains aussi, mais on ne l'a pas appris
   System.out.println("Le cours est déjà présent dans la liste.");
   return false;
  }
  else{
   infos.add(new Infos(heure, cours));
   return true;
 }
 } // j'ai utilisé l'equals sur le code et l'id, mais ne suis pas sûr
 //je me suis aidé de chatgpt ici, je savais plus faire indexof
 //fallait-il rajouter comme vous true or false



 /**
  * Modification d'un cours via l'heure
  * @param cours cours à modifier
  * @param heure cours modifié via l'heure
  */

public boolean modifCours(Cours cours,int heure){
 boolean ok = false;
 for (Infos info : infos){
  if (info.getCours().equals(cours)){
   info.setNbreHeures(heure);
   ok=true;
   break;
  }
 }
 System.out.println(ok ? "Modification réussie" : "Pas de cours trouvé");
 return ok;
}
//cours labo 5 ou 6 selon la classe en api 1

 /**
  * Modification d'un cours via l'enseignant
  * @param cours cours à modifier
  * @param enseignant cours modifié via l'enseignant
  */
 public boolean modifCours(Cours cours,Enseignant enseignant){
  boolean ok = false;
  for (Infos info : infos){
   if (info.getCours().equals(cours)){
    info.setEnseignant(enseignant);
    ok=true;
    break;
   }
  }
  System.out.println(ok ? "Modification réussie" : "Pas de cours trouvé");
  return ok;
 }

 /**
  * Modification d'un cours via la salle
  * @param cours cours à modifier
  * @param salle cours modifié via la salle
  */
 public boolean modifCours(Cours cours,Salle salle){
  boolean ok = false;
  for (Infos info : infos){
   if (info.getCours().equals(cours)){
    info.setSalle(salle);
    ok=true;
    break;
   }
  }
  System.out.println(ok ? "Modification réussie" : "Pas de cours trouvé");
  return ok;
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
  *  idClasse identifiant unique de la classe, affecté par la base de
  * données, increment de 1 à chaque fois grace CompteurIdClasses
  * @param sigle sigle de la classe
  * @param annee année de la classe
  * @param specialite specialité de la classe
  * @param nbreEleves nombre d'eleves de la classe
  */
 public Classe( String sigle, int annee, String specialite, int nbreEleves) {
  this.idClasse = CompteurIdClasses++;
  this.sigle = sigle;
  this.annee = annee;
  this.specialite = specialite;
  this.nbreEleves = nbreEleves;
 }


 /**
  * Suppression d'un cours
  * @param cours cours à supprimer
  */
 public boolean suppCours(Cours cours){
  boolean ok = false;
  for (Infos info : infos) {
   if (info.getCours().equals(cours)) {
    infos.remove(info);
    ok = true;
    break;
   }
  }
  System.out.println(ok ? "Suppression réussie" : "Pas de cours trouvé");
  return ok;
 }
}
// fallait-il ajouter un boolean pour confirmer la suppression ou pas comme pour ajout, car dans l'exemple du cours, il n'y est pas ?
//meme question pour les modifications





