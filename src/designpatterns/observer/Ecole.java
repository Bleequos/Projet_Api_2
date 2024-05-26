/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.observer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Ahmed
 */
public class Ecole {
    public static void main(String[] args) {
         Classe cl1 = new Classe("1B",2015,"Info",10);
         Classe cl2 = new Classe("1A",2015,"Info",10);
         Enseignant ens1 = new Enseignant("12az", "Durand", "Jean", "0483132547", 12, new BigDecimal(5000), LocalDate.now());
         Enseignant ens2 = new Enseignant("12az", "Dupond", "Annie", "0483132547", 12, new BigDecimal(5000), LocalDate.now());
         cl1.addObserver(ens1);
         cl1.addObserver(ens2);
         cl2.addObserver(ens1);

         cl1.setNbreEleves(15);
         cl2.setNbreEleves(17);
    }
}
