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
            int ch = choixListe(Arrays.asList("ajouter enseignant", "modifier enseignant", "supprimer enseignant", "lister enseignant", "ajouter salle", "modifier salle", "supprimer salle", "lister salles", "menu principal"));

            switch (ch) {
                case 1:
                    ajouterEnseignant(cs);
                    break;
                case 2:
                    modifierEnseignant(cs);
                    break;
                case 3:
                    supprimerEnseignant(cs);
                    break;
                case 4:
                    listerEnseignants(cs);
                    break;
                case 5:
                    ajouterSalle(cs);
                    break;
                case 6:
                    modifierSalle(cs);
                    break;
                case 7:
                    supprimerSalle(cs);
                    break;
                case 8:
                    listerSalles(cs);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }

    public void ajouterEnseignant(Cours cs){
        System.out.println("ajout d'une infos");
        Enseignant ens = ev.selectionner();
        Salle sa = sv.selectionner();
        Classe cl = clv.selectionner();
        System.out.print("nombre d'heure :");
        int nbreheures= sc.nextInt();
        boolean ok = coursController.addEnseignant(cs, nbreheures,ens,cl,sa);
        if(ok) affMsg("Enseignant ajouté");
        else affMsg("erreur lors de l'ajout de l'enseignant");
    }

    private void listerEnseignants(Cours cs) {
        System.out.println("Enseignants du cours");
        List<Infos> li = coursController.getEnseignants(cs);
        if(li.isEmpty()) affMsg("aucune info pour ce cours");
        else affList(li);
    }

    private void supprimerEnseignant(Cours cs) {
        System.out.println("suppression d'une infos");
        Enseignant ens = ev.selectionner();
        Salle sa = sv.selectionner();
        Classe cl = clv.selectionner();
        boolean ok = coursController.supEnseignant(cs,ens,cl,sa);
        if(ok) affMsg("info d'enseignant supprimée");
        else affMsg("info d'enseignant non supprimée");
    }



    private void modifierEnseignant(Cours cs) {
        System.out.println("modification d'une infos");
        Enseignant ens = ev.selectionner();
        Salle sa = sv.selectionner();
        Classe cl = clv.selectionner();
        System.out.print("nombre d'heure :");
        int nbreheures= sc.nextInt();
        boolean ok = coursController.modifEnseignant(cs, nbreheures,ens,cl,sa);
        if(ok) affMsg("mise à jour effectuée");
        else  affMsg("mise à jour infructueuse");
    }

    public void ajouterSalle(Cours cs){
        System.out.println("ajout d'une salle");
        Salle sa = sv.selectionner();
        Enseignant ens = ev.selectionner();
        Classe cl = clv.selectionner();
        System.out.print("nombre d'heure :");
        int nbreheures= sc.nextInt();
        boolean ok = coursController.addSalle(cs, nbreheures, sa, cl, ens);
        if(ok) affMsg("Salle ajoutée");
        else affMsg("erreur lors de l'ajout de la salle");
    }

    private void listerSalles(Cours cs) {
        System.out.println("Salles du cours");
        List<Infos> li = coursController.getSalles(cs);
        if(li.isEmpty()) affMsg("aucune info pour ce cours");
        else affList(li);
    }

    private void supprimerSalle(Cours cs) {
        System.out.println("suppression d'une salle");
        Salle sa = sv.selectionner();
        Enseignant ens = ev.selectionner();
        Classe cl = clv.selectionner();
        boolean ok = coursController.supSalle(cs, sa, cl, ens);
        if(ok) affMsg("info de salle supprimée");
        else affMsg("info de salle non supprimée");
    }

    private void modifierSalle(Cours cs) {
        System.out.println("modification d'une salle");
        Salle sa = sv.selectionner();
        Enseignant ens = ev.selectionner();
        Classe cl = clv.selectionner();
        System.out.print("nombre d'heure :");
        int nbreheures= sc.nextInt();
        boolean ok = coursController.modifSalle(cs, nbreheures, sa, cl, ens);
        if(ok) affMsg("mise à jour effectuée");
        else  affMsg("mise à jour infructueuse");
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

 }



