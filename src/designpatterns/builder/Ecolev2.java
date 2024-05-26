/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.builder;

/**
 *
 * @author Michel
 */
public class Ecolev2 {
    public static void main(String[] args)  {
        
        try {
            ClasseV2 cl1 = new ClasseV2.ClasseBuilder(12,"45",1928).
                    setNbreEleves(4).
                    setSpecialite("Informatique").
                    build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
            try {
                ClasseV2 cl2 = new ClasseV2.ClasseBuilder(1,"45",0).
                        setNbreEleves(4).
                        setSpecialite("Informatique").
                        build();
                System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
    }
}
//je n'ai mis  la salle par default car le but est le prouver que le sigle et l'année est obligatoire