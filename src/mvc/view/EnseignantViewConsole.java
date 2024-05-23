package mvc.view;

import Ecole.metier.Enseignant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EnseignantViewConsole extends EnseignantAbstractView {
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
        update(enseignantController.getAll());
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
        int nl = choixElt(le);
            Enseignant en = le.get(nl-1);
            String matricule= modifyIfNotBlank("numéro de matricule",en.getMatricule());
            String nom = modifyIfNotBlank("nom",en.getNom());
            String prenom = modifyIfNotBlank("prenom",en.getPrenom());
            String tel = modifyIfNotBlank("numéro de téléphone",en.getTel());
            int chargesem = Integer.parseInt(modifyIfNotBlank("Charge de la semaine",""+en.getChargeSem()));
            BigDecimal salairemensuel = new BigDecimal(modifyIfNotBlank("Salaire mensuel",""+en.getSalaireMensu()));
            LocalDate dateEngagement = LocalDate.parse(modifyIfNotBlank("date d'engagement",""+en.getDateEngag()));
            Enseignant enmaj =  enseignantController.update(new Enseignant(en.getIdEnseignant(),matricule,nom,prenom,tel,chargesem,salairemensuel,dateEngagement));
           if(enmaj==null) affMsg("mise à jour infructueuse");
           else affMsg("mise à jour effectuée : "+enmaj);
    }

    private void rechercher() {
        System.out.println("idEnseignant : ");
        int idEnseignant = sc.nextInt();
        enseignantController.search(idEnseignant);
    }

    private void retirer() {

    int nl = choixElt(le);
        Enseignant en = le.get(nl-1);
      boolean ok = enseignantController.removeEnseignant(en);
        if(ok) affMsg("produit effacé");
        else affMsg("produit non effacé");
    }

    private void ajouter() {
        System.out.print("matricule du professeur :");
        String matricule = sc.nextLine();
        System.out.print("nom du professeur :");
        String nom = sc.nextLine();
        System.out.print("prénom du professeur :");
        String prenom = sc.nextLine();
        System.out.print("télephone du professeur :");
        String tel = sc.nextLine();
        System.out.print("Charge sem :");
        int chargesem = sc.nextInt();
        System.out.print("Salaire mensuel :");
        BigDecimal salairemensuel = BigDecimal.valueOf(sc.nextInt());
        System.out.println("date d'engagemenet : ");
        System.out.println("annéee : ");
        int annee = sc.nextInt();
        System.out.println("mois : ");
        int mois = sc.nextInt();
        System.out.println("jour : ");
        int jour = sc.nextInt();
        LocalDate dateEngagement = LocalDate.of(annee, mois, jour);
        Enseignant en = enseignantController.addEnseignant(new Enseignant(0,matricule,nom,prenom,tel,chargesem,salairemensuel,dateEngagement)) ;
        if(en!=null) affMsg("création de :"+en);
        else affMsg("erreur de création");
     }

    @Override
    public Enseignant selectionner(){
         update(enseignantController.getAll());
         int nl =  choixListe(le);
         Enseignant en = le.get(nl-1);
            return en;
        }
}

