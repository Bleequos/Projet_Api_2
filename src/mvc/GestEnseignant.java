package mvc;

import mvc.model.*;
import mvc.controller.EnseignantController;
import mvc.view.*;
import utilitaires.Utilitaire;
import java.util.Arrays;
import java.util.List;

public class GestEnseignant {
    private DAOEnseignant em;
    private EnseignantController ec;
    private EnseignantAbstractView ev;

    public void gestion(){
        em = new EnseignantModelDB();
        ev = new EnseignantViewConsole();
        ec = new EnseignantController(em,ev);//création et injection de dépendance
     //   ev.setEnseignantView();
        em.addObserver(ev);
        List<String> loptions = Arrays.asList("clients","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: ev.menu();
                    break;
                case 2: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        demodb.GestEnseignant gm = new demodb.GestEnseignant();
        gm.gestion();
    }
}

