/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vorazesefamintos;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 20231PF.CC0024
 */
public class VorazesEFamintos {

    public static void main(String[] args) {
        PriorityQueue<Faminto> dance = new PriorityQueue<>(sortDance);
        PriorityQueue<Faminto> drink = new PriorityQueue<>(sortDrink);
        PriorityQueue<Faminto> eat = new PriorityQueue<>(sortEat);
        // uso de recurso: rand(<=nivel de necessidade da atividade)
        
        Faminto f1 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        Faminto f2 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        Faminto f3 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        Faminto f4 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        Faminto f5 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        Faminto f6 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        Faminto f7 = new Faminto(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5));
        
        int ciclos = 0;
        Scanner sc = new Scanner(System.in);
        ciclos = sc.nextInt();
        
        while(ciclos != 0){
            // 
            ciclos--;
            // waiting
        }
        
    }
    public static Comparator<Faminto> sortDance = new Comparator<Faminto>() {
        public int compare(Faminto o1, Faminto o2) {
            return Integer.compare(o1.getNecessityLevels()[0], o2.getNecessityLevels()[0]);
        }
    };

    public static Comparator<Faminto> sortEat = new Comparator<Faminto>() {
    public int compare(Faminto o1, Faminto o2) {
            return Integer.compare(o1.getNecessityLevels()[1], o2.getNecessityLevels()[1]);
        }
    };
    public static Comparator<Faminto> sortDrink = new Comparator<Faminto>() {
        public int compare(Faminto o1, Faminto o2) {
            return Integer.compare(o1.getNecessityLevels()[2], o2.getNecessityLevels()[2]);
        }
    };
}