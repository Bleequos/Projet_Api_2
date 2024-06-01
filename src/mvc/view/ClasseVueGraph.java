/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;


import Ecole.metier.*;
import mvc.model.DAOSalle;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static utilitaires.Utilitaire.choixListe;


public class ClasseVueGraph extends ClasseAbstractView {



    public Classe create() {
        JTextField tfsigle = new JTextField();
        JTextField tfannee = new JTextField();
        JTextField tfspecialite = new JTextField();
        JTextField tfnbreeleves = new JTextField();


        Salle tfsalle = sv.selectionner();



        Object[] message = {
                "sigle: ", tfsigle,
                "annee:", tfannee,
                "specialite:", tfspecialite,
                "nbreeleves:", tfnbreeleves,
                "salle par defaut:", tfsalle
        };

        int option = JOptionPane.showConfirmDialog(null, message, "nouveau classe", JOptionPane.DEFAULT_OPTION);
        String sigle = tfsigle.getText().toString();
        int annee = Integer.parseInt(tfannee.getText().toString());
        String specialite = tfspecialite.getText().toString();
        int nbreeleves = Integer.parseInt(tfnbreeleves.getText().toString());


        Salle salleParDefault = tfsalle;

        Classe newcl = new Classe(0, sigle, annee, specialite, nbreeleves, salleParDefault);
        return newcl;
    }
  

    public void display(Classe cl) {
        displayMsg(cl.toString());
        if (!cl.getInfos().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher ses infos (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                int i=0;
                StringBuffer sb= new StringBuffer(200);
                for (Infos in : cl.getInfos()) sb.append((++i)+"."+in+"\n");
                displayMsg(sb.toString());
            }
        }
    }

    private String getMsg(String msg) {
        int option = JOptionPane.showConfirmDialog(null, msg, "question", JOptionPane.DEFAULT_OPTION);
        return ""+option;
    }

    private void displayMsg(String msg) {
        JOptionPane.showConfirmDialog(null, msg, "info", JOptionPane.DEFAULT_OPTION);
    }


    public Classe update(Classe cl) {
       
        do {
            String ch = getMsg("1.changement de sigle\n2.fin");
            switch (ch) {
                case "1":
                    String sigle = getMsg("nouveau sigle :");
                    cl.setSigle(sigle);//on devrait tester que cela ne crée pas de doublons sigle
                    break;
                case "2":
                    return cl;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }



    public Integer read() {
        String ns = getMsg("sigle : ");
        int n = Integer.parseInt(ns);
        return n;
    }


    public void affAll(List<Classe> lc) {
        int i =0;
        StringBuffer sb= new StringBuffer(200);

        for(Classe cl:lc) sb.append((++i)+"."+cl+"\n");
        displayMsg(sb.toString());
    }


    public void affLobj(List lobj) {
        int i =0;
        for(Object o:lobj){
            displayMsg((++i)+"."+o.toString());
        }
    }

    @Override
    public void affMsg(String msg) {

    }



    public Classe selectionner() {
        return null;
    }

    @Override

        public void menu() {
            update(classeController.getAll());
            do {

                int ch = choixListe(Arrays.asList("ajout", "fin"));
                switch (ch) {
                    case 1:
                        ajouter();
                        break;
                    case 2 :
                        return;
                }
            } while (true);
        }



    private void ajouter() {
        Classe cl = create();
        classeController.addClasse(cl);
    }

    @Override
    public void affList(List l) {
          displayMsg(l.toString());
    }
}
