package metier;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Enseignant {

    protected int idEnseigant;

    protected String matricule;

    protected String nom;

    protected String prenom;

    protected String tel;

    protected int chargeSem;

    protected BigDecimal salaireMensu;

    protected LocalDate dateEngag;

    public void setIdEnseigant(int idEnseigant) {
        this.idEnseigant = idEnseigant;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setChargeSem(int chargeSem) {
        this.chargeSem = chargeSem;
    }

    public void setSalaireMensu(BigDecimal salaireMensu) {
        this.salaireMensu = salaireMensu;
    }

    public void setDateEngag(LocalDate dateEngag) {
        this.dateEngag = dateEngag;
    }

    public int getIdEnseigant() {
        return idEnseigant;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public int getChargeSem() {
        return chargeSem;
    }

    public BigDecimal getSalaireMensu() {
        return salaireMensu;
    }

    public LocalDate getDateEngag() {
        return dateEngag;
    }

    public Enseignant(int idEnseigant, String matricule, String nom, String prenom, String tel, int chargeSem, BigDecimal salaireMensu, LocalDate dateEngag) {
        this.idEnseigant = idEnseigant;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSem = chargeSem;
        this.salaireMensu = salaireMensu;
        this.dateEngag = dateEngag;
    }
}
