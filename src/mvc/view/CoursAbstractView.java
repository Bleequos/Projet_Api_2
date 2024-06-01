package mvc.view;

import Ecole.metier.*;
import mvc.controller.CoursController;
import mvc.observer.Observer;

import java.util.List;

public abstract class CoursAbstractView implements Observer {
    protected CoursController coursController;

    protected ClasseAbstractView clv;

    protected EnseignantAbstractView ev;

    protected SalleAbstractView sv;
    protected List<Cours> lcs;
    public  void setController(CoursController coursController){
        this.coursController=coursController;
    };

    public void setClasseView(ClasseAbstractView clv){
        this.clv=clv;
    }

    public void setEnseignantView(EnseignantAbstractView ev){
        this.ev=ev;
    }

    public void setSalleView(SalleAbstractView sv){
        this.sv=sv;
    }

    public abstract void affMsg(String msg);

    public abstract void menu();

    public abstract void affList(List l);
    @Override
    public void update(List lcs) {
        this.lcs=lcs;
        affList(lcs);
    }

    public abstract Cours selectionner();

}
