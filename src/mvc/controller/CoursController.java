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

    public boolean addEnseignant(Cours cs,int nbreheures,Enseignant ens,Classe cl,Salle sa){
       return  model.addEnseignant(cs, nbreheures, ens,cl,sa);
    }

    public boolean modifEnseignant(Cours cs,int nbreheures,Enseignant ens,Classe cl,Salle sa){
        return model.modifEnseignant(cs, nbreheures, ens,cl,sa);
    }

    public boolean supEnseignant(Cours cs,Enseignant ens,Classe cl,Salle sa){

        return  model.supEnseignant(cs, ens,cl,sa);
    }

    public List<Infos> getEnseignants(Cours cs){
        return model.getEnseignants(cs);
    }

    public boolean addSalle(Cours cs, int nbreheures, Salle sa, Classe cl, Enseignant ens){
        return model.addSalle(cs, nbreheures, sa, cl, ens);
    }

    public boolean modifSalle(Cours cs, int nbreheures, Salle sa, Classe cl, Enseignant ens) {
        return model.modifSalle(cs, nbreheures, sa, cl, ens);
    }

    public boolean supSalle(Cours cs, Salle sa, Classe cl, Enseignant ens){
        return model.supSalle(cs, sa, cl, ens);
    }

    public List<Infos> getSalles(Cours cs){
        return model.getSalles(cs);
    }

    public Cours addCours(Cours cs) {
        return model.addCours(cs);
    }
}

