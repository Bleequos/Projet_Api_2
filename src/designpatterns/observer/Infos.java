/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.observer;



import Ecole.metier.Cours;
import Ecole.metier.Enseignant;
import Ecole.metier.Salle;

import java.util.Objects;

/**
 * Classe métier de gestion d'Info
 * @author Ahmed Al robaie
 * @version 1.0
 * @see Cours
 * @see Salle
 */





public class Infos {




    /**
     * Nombre d'heures de cours dans l'info
     */

    protected int nbreHeures;

    /**
     * Le cours dans l'info
     */

    protected Cours cours;

    /**
     * Salle de cours dans l'info
     */

    protected Salle salle;

    /**
     * Constructeur paramétré
     * @param nbreHeures nombres d'heures de cours de l'info
     * @param cours cours de l'info
     */

    public Infos(int nbreHeures, Cours cours) {
        this.nbreHeures = nbreHeures;
        this.cours = cours;
        this.salle = null;
        this.enseignant = null;
    }

    /**
     * Enseignant du cours dans l'info
     */

    protected Enseignant enseignant;


    /**
     * Constructeur paramétré
     * @param nbreHeures nombres d'heures de cours de l'info
     * @param cours cours de l'info
     * @param salle Salle de cours de l'info
     * @param enseignant enseignant de cours de l'info
     */

    public Infos(int nbreHeures, Cours cours, Salle salle, Enseignant enseignant) {
        this.nbreHeures = nbreHeures;
        this.cours = cours;
        this.salle = salle;
        this.enseignant = enseignant;
    }




    /**
     * Setter Salle de cours de l'info
     *
     * @param salle Salle de cours de l'info
     */

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    /**
     * Setter Enseignant de cours de l'info
     *
     * @param enseignant Enseignant de cours de l'info
     */

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    /**
     * getter Salle de l'info
     *
     * @return salle Salle de l'info
     */

    public Salle getSalle() {
        return salle;
    }

    /**
     * Getter Enseignant de cours de l'info
     *
     * @return enseignant Enseignant de cours de l'info
     */

    public Enseignant getEnseignant() {
        return enseignant;
    }

    /**
     * Setter Nombres d'heures de cours de l'info
     *
     * @param nbreHeures Nombres d'heures de cours de l'info
     */

    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }

    /**
     * Setter Cours de l'info
     *
     * @param cours Cours de l'info
     */

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * Getter Nombre d'heures de cours de l'info
     *
     * @return nbreHeures Nombre d'heures de cours de l'info
     */

    public int getNbreHeures() {
        return nbreHeures;
    }

    /**
     * Getter Cours de l'info
     *
     * @return cours Cours de l'info
     */

    public Cours getCours() {
        return cours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return nbreHeures == infos.nbreHeures && Objects.equals(cours, infos.cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nbreHeures, cours);
    }

    /**
     * méthode toString
     * @return informations complètes
     */

    @Override
    public String toString() {
        return "Infos{" +
                "nbreHeures=" + nbreHeures +
                ", cours=" + cours +
                ", salle=" + salle +
                ", enseignant=" + enseignant +
                '}';
    }
}

