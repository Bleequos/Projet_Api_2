package mvc;

import Ecole.metier.Enseignant;
import mvc.model.*;
import mvc.controller.*;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestEcole {
    private DAOClasse cm;
    private DAOCours csm;
    private DAOEnseignant em;
    private DAOSalle sm;
    private ClasseController cc;
    private CoursController csc;
    private EnseignantController ec;

    private SalleController sc;
    private ClasseAbstractView cv;
    private CoursAbstractView csv;
    private EnseignantAbstractView ev;

    private SalleAbstractView sv;


    public void gestion(){
        cm = new ClasseModelDB();
        //cm = new ClasseModelHyb();
        csm = new CoursModelDB();
        em=new EnseignantModelDB();
        sm=new SalleModelDB();

        cv = new ClasseViewConsole();
        //cv= new ClasseVueGraph();
        csv = new CoursViewConsole();
        ev =  new EnseignantViewConsole();
        sv = new SalleViewConsole();

        cc = new ClasseController(cm,cv);//création et injection de dépendance
        csc = new CoursController(csm,csv);
        ec= new EnseignantController(em,ev);
        sc = new SalleController(sm,sv);

        csv.setClasseView(cv);
        csv.setEnseignantView(ev);
        csv.setSalleView(sv);

        cm.addObserver(cv);
        em.addObserver(ev);
        csm.addObserver(csv);
        sm.addObserver(sv);

        List<String> loptions = Arrays.asList("classes","cours","enseignants","salles","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cv.menu();
                    break;
                case 2 : csv.menu();
                    break;
                case 3: ev.menu();
                    break;
                case 4: sv.menu();
                    break;
                case 5: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestEcole ge = new GestEcole();
        ge.gestion();
    }
}
