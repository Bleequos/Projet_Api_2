package mvc.view;

import Ecole.metier.Salle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class SalleViewConsole extends SalleAbstractView {
    private Scanner sc = new Scanner(System.in);


    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu(){
        update(salleController.getAll());
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



    private void modifier() {
        int nl = choixElt(ls);
            Salle sa = ls.get(nl-1);
            String sigle= modifyIfNotBlank("sigle de la salle",sa.getSigle());
            int capacite = Integer.parseInt(modifyIfNotBlank("capacite de la salle",""+sa.getCapacite()));
            Salle samaj =  salleController.update(new Salle(sa.getIdSalle(),sigle,capacite));
           if(samaj==null) affMsg("mise à jour infructueuse");
           else affMsg("mise à jour effectuée : "+samaj);
    }

    private void rechercher() {
        System.out.println("idSalle : ");
        int idSalle = sc.nextInt();
        salleController.search(idSalle);
    }

    private void retirer() {

    int nl = choixElt(ls);
        Salle sa = ls.get(nl-1);
      boolean ok = salleController.removeSalle(sa);
        if(ok) affMsg("produit effacé");
        else affMsg("produit non effacé");
    }

    private void ajouter() {
        System.out.print("sigle de la salle :");
        String sigle = sc.nextLine();
        System.out.print("capacite de la salle :");
        int capacite = sc.nextInt();
        Salle sa = salleController.addSalle(new Salle(0,sigle,capacite)) ;
        if(sa!=null) affMsg("création de :"+sa);
        else affMsg("erreur de création");
     }

    @Override
    public Salle selectionner(){
         update(salleController.getAll());
         int nl =  choixListe(ls);
        Salle sa = ls.get(nl-1);
            return sa;
        }
}

