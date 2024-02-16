package metier;

import java.util.ArrayList;
import java.util.List;

public class Classe {

 protected int idClasse;

 protected String sigle;

 protected int annee;

 protected String specialite;

 protected int nbreEleves;

 protected List<Salle> salleParDefault = new ArrayList<>();

 protected List<Cours> Cours = new ArrayList<>();

 public void nbreHeuresTot(){
 }

 public void listeEnseignantsEtHeures(){
 }

 public void listeSallesetHeures(){
 }

 public void listeCoursEtHeures(){
 }

 public boolean salleCapaciteOK(Salle salle){
  return salle==null;
 }

 public void addCours(Cours cours,int heure){

 }

public void modifCours(Cours cours,int heure){

}

 public void modifCours(Cours cours,Enseignant enseignant){

 }
 public void modifCours(Cours cours,Salle salle){

 }

 public int getIdClasse() {
  return idClasse;
 }

 public String getSigle() {
  return sigle;
 }

 public int getAnnee() {
  return annee;
 }

 public String getSpecialite() {
  return specialite;
 }

 public int getNbreEleves() {
  return nbreEleves;
 }

 public List<Salle> getSalleParDefault() {
  return salleParDefault;
 }

 public List<metier.Cours> getCours() {
  return Cours;
 }

 public void setIdClasse(int idClasse) {
  this.idClasse = idClasse;
 }

 public void setSigle(String sigle) {
  this.sigle = sigle;
 }

 public void setAnnee(int annee) {
  this.annee = annee;
 }

 public void setSpecialite(String specialite) {
  this.specialite = specialite;
 }

 public void setNbreEleves(int nbreEleves) {
  this.nbreEleves = nbreEleves;
 }

 public void setSalleParDefault(List<Salle> salleParDefault) {
  this.salleParDefault = salleParDefault;
 }

 public void setCours(List<metier.Cours> cours) {
  Cours = cours;
 }

 public Classe(int idClasse, String sigle, int annee, String specialite, int nbreEleves) {
  this.idClasse = idClasse;
  this.sigle = sigle;
  this.annee = annee;
  this.specialite = specialite;
  this.nbreEleves = nbreEleves;
 }

 public void suppCours(Cours cours){

 }
}
