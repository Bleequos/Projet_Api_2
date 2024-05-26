/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.composite;

/**
 *
 * @author Michel
 */
public class Ecole {

    public static void main(String[] args) {
        Classe cl1 = new Classe("A4", 2012, "Informatique", 20);
        Classe cl2 = new Classe("A5", 2013, "Informatique", 25);
        Classe cl3 = new Classe("A6", 2014, "Informatique", 30);
        Classe cl4 = new Classe("A7", 2015, "Informatique", 35);
        Section s1 = new Section(1, "Bachelier");
        Section s2 = new Section(2, "1eme année");
        Section s3 = new Section(3, "2eme année");
        s1.getElts().add(cl1);
        s1.getElts().add(s2);
        s1.getElts().add(s3);
        s2.getElts().add(cl4);
        s2.getElts().add(cl3);
        s3.getElts().add(cl2);
        System.out.println(s1);

    }
}
