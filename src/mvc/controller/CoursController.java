package mvc.controller;

import Ecole.metier.*;

import mvc.model.DAOCours;
import mvc.view.CoursAbstractView;

import java.util.List;

public class CoursController {
    private DAOCours model;
    private CoursAbstractView view;


    public CoursController(DAOCours model, CoursAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Cours> getCoursesByClass(Classe cl) {
        return model.getCoursesByClass(cl);
    }

    public List<Cours> getAll(){
        return model.getCours();
    }

    public boolean removeCours(Cours cours) {
        return  model.removeCours(cours);
    }

    public Cours update(Cours cours) {
         return model.updateCours(cours);
    }

    public Cours search(int idCours) {
       return  model.readCours(idCours);
    }
    public Cours addCours(Cours cs) {
        return model.addCours(cs);
    }

    public List<Infos> getListInfos() {
        return model.getListInfos();
    }

    public Boolean addCoursInfos(Classe classe, Cours cours, Enseignant enseignant, int heure){
        return model.addCoursInfos(classe, cours, enseignant, heure);
    }

    public Boolean suppCoursInfos(Cours cours){
        return model.suppCoursInfos(cours);
    }

    public Boolean ModifierCoursInfos(Classe classe,Cours cours, Enseignant enseignant, int heure){
        return model.ModifierCoursInfos(classe,cours, enseignant, heure);
    }

}

