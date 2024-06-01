package mvc.view;

import Ecole.metier.Classe;
import Ecole.metier.Cours;
import Ecole.metier.Enseignant;
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
            int ch = choixListe(Arrays.asList("nombre d'heure totale", "Capacité de salle ok ou pas", "Ajouter un cours", "Supprimer un cours", "Modifier un cours(heure)","Modifier un cours(enseignant)", "Modifier un cours(salle)", "Liste des cours et heures", "Liste des enseignants et heures", "Liste des salles et heures", "Liste des infos", "Menu principal"));
            if(ch == 12) return;
            int nbreHeuresTot = 0;
            boolean ok = false;
            List l = null;
            switch (ch) {
                case 1:
                    nbreHeuresTot = classeController.nbreHeuresTot(cl);
                    affMsg(nbreHeuresTot == 0 ? "aucun élément trouvée" : nbreHeuresTot + " heures");
                    break;
                case 2:
                    ok = classeController.salleCapaciteOK(cl, cl.getSalleParDefault());
                    affMsg(ok ? "la capacité de cette salle pour les eleves est ok" : "la capacité de cette salle pour les eleves n'est pas ok");
                    break;
                case 3:
                    affMsg("nouveau cours:");
                    Cours cours = cv.selectionner();
                    affMsg("nouvel enseignant:");
                    Enseignant enseignant = ev.selectionner();
                    affMsg("nombre d'heure:");
                    int heures= sc.nextInt();
                    ok = classeController.addCours(cl, cours, enseignant, heures);
                    affMsg(ok ? "Cours ajouté avec succès" : "Erreur lors de l'ajout du cours");
                    break;
                case 4:
                    ok = classeController.suppCours(cl, cv.selectionnerCours(cl));
                    affMsg(ok ? "Cours supprimé avec succès" : "Erreur lors de la suppression du cours");
                    break;
                case 5:
                    Cours coursv2=cv.selectionnerCours(cl);
                    affMsg("nouvelle heure:");
                    int heure = sc.nextInt();
                    ok = classeController.modifCours(cl,coursv2, heure);
                    affMsg(ok ? "Cours modifié avec succès" : "Erreur lors de la modification du cours");
                    break;
                case 6 :
                    Cours coursv3=cv.selectionnerCours(cl);
                    affMsg("nouvel enseignant:");
                    Enseignant enseignantv2=ev.selectionner();
                    ok = classeController.modifCours(cl,coursv3, enseignantv2);
                    affMsg(ok ? "Enseignant modifié avec succès" : "Erreur lors de la modification de l'enseignant");
                    break;
                case 7:
                    Cours coursv4=cv.selectionnerCours(cl);
                    affMsg("nouvelle salle:");
                    Salle salle = sv.selectionner();
                    ok = classeController.modifCours(cl,coursv4, salle);
                    affMsg(ok ? "Salle modifiée avec succès" : "Erreur lors de la modification de la salle");
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    l = switch (ch) {
                        case 8 -> classeController.listeCoursEtHeures(cl);
                        case 9 -> classeController.listeEnseignantsEtHeures(cl);
                        case 10 -> classeController.listeSallesetHeures(cl);
                        case 11 -> classeController.listeInfos(cl);
                        default -> null;
                    };
                    if(l==null || l.isEmpty()) affMsg("aucun élément trouvée");
                    else affListe(l);
                    break;
            }
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

