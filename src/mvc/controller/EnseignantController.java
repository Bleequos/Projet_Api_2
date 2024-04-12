package mvc.controller;

import metier.Enseignant;
import mvc.model.DAOEnseignant;
import mvc.view.EnseignantAbstractView;
import java.util.List;
public class EnseignantController {
    private DAOEnseignant model;
    private EnseignantAbstractView view;

    public EnseignantController(DAOEnseignant model, EnseignantAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Enseignant> getAll(){
        return model.getEnseignants();
    }
    public Enseignant addEnseignant(Enseignant enseignant) {
        return  model.addEnseignant(enseignant);
    }

    public boolean removeEnseignant(Enseignant enseignant) {
        return model.removeEnseignant(enseignant);
    }

    public Enseignant update(Enseignant enseignant) {
        return model.updateEnseignant(enseignant);

    }
    public Enseignant search(int idEnseignant) {
        return  model.readEnseignant(idEnseignant);
    }

}