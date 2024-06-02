package mvc.model;

import Ecole.metier.*;

import mvc.observer.Subject;

import java.util.List;

public abstract class DAOCours extends Subject {

    public abstract boolean removeCours(Cours cours);

    public abstract Cours updateCours(Cours cours);

    public abstract Cours readCours(int idCours);

    public abstract List<Cours> getCours();

    public abstract Cours addCours(Cours cours);

    public abstract List<Cours> getCoursesByClass(Classe cl);

    public abstract List<Infos> getListInfos();

    public abstract Boolean  addCoursInfos(Classe classe, Cours cours, Enseignant enseignant, int heure);

    public abstract Boolean suppCoursInfos(Cours cours    );

    public abstract Boolean ModifierCoursInfos(Classe classe,Cours cours, Enseignant enseignant, int heure);



}
