package mvc.view;

import Ecole.metier.Classe;
import Ecole.metier.Salle;
import mvc.model.DAOSalle;
import mvc.model.SalleModelDB;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;


public class ClasseViewConsole extends ClasseAbstractView {
    private Scanner sc = new Scanner(System.in);



    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

   @Override
    public void menu() {
        update(classeController.getAll());
        do {

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    @Override
    public void affList(List l) {
        affListe(l);
    }

    private void special(Classe cl) {
        do {
            int ch = choixListe(Arrays.asList("nombre d'heure totale", "factures non payees", "factures en retard", "factures payees", "produits achetés", "menu principal"));
            if(ch==6) return;
            List l = null;
            if(ch == 1) {
                int nbreHeuresTot = classeController.nbreHeuresTot(cl);
                System.out.println(nbreHeuresTot);
            } else {
                l = switch (ch) {
                    case 2 -> classeController.listeSallesetHeures(cl);
                    case 3 -> classeController.listeCoursEtHeures(cl);
                    case 4 -> classeController.listeInfos(cl);
                    default -> null;
                };
            }
            if(l == null || l.isEmpty()) affMsg("aucun élément trouvée");
            else affList(l);
        } while (true);
    }



    private void modifier() {
        int nl = choixElt(lc) - 1;
        Classe classe = lc.get(nl);
        String sigle = modifyIfNotBlank("sigle", classe.getSigle());
        int annee = Integer.parseInt(modifyIfNotBlank("annee", "" + classe.getAnnee()));
        String specialite= modifyIfNotBlank("specialite", classe.getSpecialite());
        int nbreeleves = Integer.parseInt(modifyIfNotBlank("nombre d'eleve", "" + classe.getNbreEleves()));
        Classe cl =classeController.updateClasse(new Classe(classe.getIdClasse(), sigle, annee, specialite, nbreeleves, classe.getSalleParDefault()));
        if(cl==null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : "+cl);
    }

    private void rechercher() {
        System.out.println("idclasse : ");
        int idClasse = sc.nextInt();
        Classe cl = classeController.search(idClasse);
        if(cl==null) affMsg("recherche infructueuse");
        else {
            affMsg(cl.toString());
            special(cl);
        }
    }

    private void retirer() {
        int nl = choixElt(lc)-1;
        Classe classe = lc.get(nl);
        boolean ok = classeController.removeClasse(classe);
        if(ok) affMsg("client effacé");
        else affMsg("client non effacé");
    }

    private void ajouter() {
        System.out.print("sigle : ");
        String sigle = sc.nextLine();
        System.out.print("annee : ");
        int annee = Integer.parseInt(sc.nextLine());
        System.out.print("specialite : ");
        String specialite = sc.nextLine();
        System.out.print("nombre d'élèves : ");
        int nbreeleves = Integer.parseInt(sc.nextLine());
        System.out.print("salle par default : ");
        Salle salleParDefault = sv.selectionner();
        Classe cl =classeController.addClasse(new Classe(0, sigle, annee, specialite, nbreeleves, salleParDefault));
        if(cl!=null) affMsg("création de :"+cl);
        else affMsg("erreur de création");
    }

    @Override
    public Classe selectionner() {
        update(classeController.getAll());
        int nl = choixListe(lc);
        Classe classe = lc.get(nl - 1);
        return classe;
    }
}

