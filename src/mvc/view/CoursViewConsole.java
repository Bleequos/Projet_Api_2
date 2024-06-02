package mvc.view;


import Ecole.metier.*;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CoursViewConsole extends CoursAbstractView {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    @Override
    public void menu(){
        update(coursController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
            switch(ch){
                case 1: ajouter();
                        break;
                case 2 : retirer();
                        break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }

    private void special(Cours cs) {
        do {
            boolean ok = true;
            List l = null;
            int ch = choixListe(Arrays.asList("Liste de cours totale", "Ajout du cours(infos)", "Modifier le cours(infos)", "supprimer le cours(infos)", "menu principal"));
            switch (ch) {
                case 1:
                    l = coursController.getListInfos();
                    break;
                case 2:
                    affMsg("nouvel enseignant:");
                    Enseignant enseignant = ev.selectionner();
                    affMsg("nombre d'heure:");
                    int heures= sc.nextInt();
                    affMsg("classe:");
                    Classe classe = clv.selectionner();
                    ok = coursController.addCoursInfos(classe, cs, enseignant, heures);
                    break;
                case 3:
                    affMsg("nouvel enseignant:");
                    Enseignant newEnseignant = ev.selectionner();
                    affMsg("nombre d'heure:");
                    int newHeures= sc.nextInt();
                    affMsg("classe:");
                    Classe newClasse = clv.selectionner();
                    ok = coursController.ModifierCoursInfos(newClasse, cs, newEnseignant, newHeures);
                    break;
                case 4:
                    ok = coursController.suppCoursInfos(cs);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
            if(l == null || l.isEmpty()) {
                affMsg("aucun élément trouvée");
            } else {
                affListe(l);
            }
            if(!ok) {
                affMsg("Operation failed");
            }
        } while (true);
    }






    private void modifier() {
        int nl = choixElt(lcs);
        Cours cs = lcs.get(nl-1);
        String code = modifyIfNotBlank("code du cours ", cs.getCode());
        String intitule = modifyIfNotBlank("intitule du cours ", cs.getIntitule());
        Cours ncs = new Cours(cs.getIdCours(), code, intitule);
        cs = coursController.update(ncs);
        if(cs == null) {
            affMsg("mise à jour infructueuse");
        } else {
            affMsg("mise à jour effectuée : " + cs);
        }
    }

    private void rechercher() {
        System.out.println("idcours: ");
        int idCours = sc.nextInt();
        Cours cs =coursController.search(idCours);
        if(cs==null) affMsg("recherche infructueuse");
        else {
            affMsg(cs.toString());
            special(cs);
        }
    }

    private void retirer() {
            int nl = choixElt(lcs);
            Cours cs = lcs.get(nl-1);
           boolean ok = coursController.removeCours(cs);
        if(ok) affMsg("commande effacée");
        else affMsg("commande non effacée");
    }

    private void ajouter() {
        System.out.print("code du cours :");
        String code = sc.nextLine();
        System.out.print("intitule du cours :");
        String intitule = sc.nextLine();
        Cours cs = coursController.addCours(new Cours(0, code, intitule));
        if(cs != null) {
            affMsg("Création de : " + cs);
        } else {
            affMsg("Erreur de création");
        }
    }

    @Override
    public Cours selectionner() {
        update(coursController.getAll());
        int nl = choixListe(lcs);
        Cours cours = lcs.get(nl - 1);
        return cours;
    }

    public Cours selectionnerCours(Classe cl){
        List<Cours> lc = coursController.getCoursesByClass(cl);
        if (lc.isEmpty()) {
            affMsg("Pas de cours trouver.");
            return null;
        }
        affList(lc);
        int nl = choixListe(lc);
        return lc.get(nl - 1);
    }

 }



