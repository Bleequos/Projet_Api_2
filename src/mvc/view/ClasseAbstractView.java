package mvc.view;

import Ecole.metier.Classe;
import Ecole.metier.Enseignant;
import Ecole.metier.Salle;
import mvc.controller.ClasseController;
import mvc.controller.CoursController;
import mvc.controller.EnseignantController;
import mvc.controller.SalleController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ClasseAbstractView implements  Observer {

    protected ClasseController classeController;

    protected SalleController salleController;

    protected CoursController coursController;

    protected EnseignantController enseignantController;

    protected CoursAbstractView cv;

    protected EnseignantAbstractView ev;

    protected SalleAbstractView sv;
    protected List<Classe> lc;

    protected List<Salle> ls;

    public void  setController(ClasseController classeController){
        this.classeController=classeController;
    }

    public void setSalleView(SalleAbstractView sv){
        this.sv=sv;
    }

    public void setCoursView(CoursAbstractView cv){
        this.cv=cv;
    }

    public void setEnseignantView(EnseignantAbstractView ev){
        this.ev=ev;
    }

    public abstract void affMsg(String msg);


    public abstract Classe selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lc) {
        this.lc = lc;
        affList(lc);
    }
}
