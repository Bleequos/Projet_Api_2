package mvc.view;

import metier.Enseignant;
import mvc.controller.EnseignantController;
import mvc.observer.Observer;

import java.util.List;

public abstract class EnseignantAbstractView implements Observer {

    protected EnseignantController EnseignantController;
    protected List<Enseignant> lp;

    public void setController(EnseignantController EnseignantController){
        this.EnseignantController=EnseignantController;
    }
    public abstract void affMsg(String msg);

    public abstract Enseignant selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
        affList(lp);
    }

}

