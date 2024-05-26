/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.builder;
import Ecole.metier.Salle;
/**
 *
 * @author Ahmed Al Robaie
 */
public class Ecole {
    public static void main(String[] args)  {
        try {
            Salle defaultSalle = new Salle(1, "Salle 1", 12);
            Classe cl1 = new Classe.ClasseBuilder()
                    .setIdClasse(1)
                    .setSigle("4578")
                    .setAnnee(1928)
                    .setSpecialite("math")
                    .setNbreEleves(12)
                    .setSalleParDefault(defaultSalle)
                    .build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
            try {
                Salle defaultSalle = new Salle(2, "Salle 2", 13);
                Classe cl2 = new Classe.ClasseBuilder()
                        .setIdClasse(2)
                        .setAnnee(1968)
                        .setSpecialite("fr")
                        .setNbreEleves(12)
                        .setSalleParDefault(defaultSalle)
                        .build();
                System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
    }
}
