package mvc.controller;

import Ecole.metier.*;

import mvc.model.DAOClasse;

import mvc.view.ClasseAbstractView;

import java.util.List;

public class ClasseController {
    private DAOClasse model;
    private ClasseAbstractView view;

    public ClasseController(DAOClasse model, ClasseAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Classe> getAll(){
        return model.getClasses();
    }

    public Classe addClasse(Classe classe) {
       return  model.addClasse(classe);
    }

    public boolean removeClasse(Classe classe) {
      return model.removeClasse(classe);
    }

    public Classe updateClasse(Classe classe) {
    return model.updateClasse(classe);
    }

    public Classe search(int idClasse) {
          return model.readClasse(idClasse);
    }

    public List<EnseignantsEtHeures> listeEnseignantsEtHeures(Classe classe) {
        return  model.listeEnseignantsEtHeures(classe);
    }

    public List<SallesetHeures> listeSallesetHeures(Classe classe) {
        return model.listeSallesetHeures(classe);
    }

    public List<CoursEtHeures> listeCoursEtHeures(Classe classe) {
        return model.listeCoursEtHeures(classe);
    }

    public List<Infos> listeInfos(Classe classe) {
        return model.listeInfos(classe);
    }

}

