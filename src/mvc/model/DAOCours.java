package mvc.model;

import Ecole.metier.*;

import mvc.observer.Subject;

import java.util.List;

public abstract class DAOCours extends Subject {

    public abstract boolean removeCours(Cours cours);

    public abstract Cours updateCours(Cours cours);

    public abstract Cours readCours(int idCours);

    public abstract List<Cours> getCours();
    public abstract boolean addEnseignant(Cours cs,int nbreheures,Enseignant ens,Classe cl,Salle sa);

    public abstract boolean modifEnseignant(Cours cs,int nbreheures,Enseignant ens,Classe cl,Salle sa);

    public abstract boolean supEnseignant(Cours cs,Enseignant ens,Classe cl,Salle sa);

    public abstract  List<Infos> getEnseignants(Cours cs);

    public abstract boolean addSalle(Cours cs, int nbreheures, Salle sa, Classe cl, Enseignant ens);

    public abstract boolean modifSalle(Cours cs, int nbreheures, Salle sa, Classe cl, Enseignant ens);

    public abstract boolean supSalle(Cours cs, Salle sa, Classe cl, Enseignant ens);

    public abstract List<Infos> getSalles(Cours cs);

    public abstract Cours addCours(Cours cours);

    public abstract List<Cours> getCoursesByClass(Classe cl);

}
