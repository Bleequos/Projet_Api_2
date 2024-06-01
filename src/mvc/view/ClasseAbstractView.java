package mvc.view;

import Ecole.metier.Classe;
import Ecole.metier.Salle;
import mvc.controller.ClasseController;
import mvc.controller.SalleController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ClasseAbstractView implements  Observer {

    protected ClasseController classeController;

    protected SalleController salleController;

    protected SalleAbstractView sv;
    protected List<Classe> lc;

    protected List<Salle> ls;

    public void  setController(ClasseController classeController){
        this.classeController=classeController;
    }

    public void setSalleView(SalleAbstractView sv){
        this.sv=sv;
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
