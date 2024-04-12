package mvc.view;

import metier.Enseignant;
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
        update(EnseignantController.getAll());
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
        int nl = choixElt(lp);

        Enseignant ens = lp.get(nl-1);
        String matricule= modifyIfNotBlank("matricule de enseignant",ens.getMatricule());
        String nom = modifyIfNotBlank("nom de enseignant",ens.getNom());
        String prenom = modifyIfNotBlank("Prenom de enseignant",""+ens.getPrenom());
        String tel = modifyIfNotBlank("telephone de enseignant",""+ens.getTel());
        int chargesem = Integer.parseInt(modifyIfNotBlank("charge semaine de enseignant",""+ens.getChargeSem()));
        BigDecimal salairemensuel= BigDecimal.valueOf(Long.parseLong(modifyIfNotBlank("stock min",""+ens.getSalaireMensu())));
        LocalDate dateEngagement= LocalDate.parse(modifyIfNotBlank("telephone de enseignant",""+ens.getDateEngag()));
        Enseignant ensmaj =  EnseignantController.update(new Enseignant(ens.getIdEnseignant(),matricule,nom,prenom,tel,chargesem,salairemensuel,dateEngagement));
        if(ensmaj==null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : "+ensmaj);
    }

    private void rechercher() {
        System.out.println("idProduit : ");
        int idProduit = sc.nextInt();
        EnseignantController.search(idProduit);
    }

    private void retirer() {

        int nl = choixElt(lp);
        Enseignant pr = lp.get(nl-1);
        boolean ok = EnseignantController.removeEnseignant(pr);
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
        Enseignant Ens = EnseignantController.addEnseignant(new Enseignant(0,matricule,nom,prenom,tel,chargesem,salairemensuel,dateEngagement)) ;
        if(Ens!=null) affMsg("création de :"+Ens);
        else affMsg("erreur de création");
    }

    @Override
    public Enseignant selectionner(){
        update(EnseignantController.getAll());
        int nl =  choixListe(lp);
        Enseignant ens = lp.get(nl-1);
        return ens;
    }
}


