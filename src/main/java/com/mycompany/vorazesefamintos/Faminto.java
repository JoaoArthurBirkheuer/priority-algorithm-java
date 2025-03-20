/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vorazesefamintos;
import java.util.Random;
/**
 *
 * @author 20231PF.CC0022
 */
public class Faminto {
    // private Necessity necessity; // 0 - dance | 1 - comer | 2 - beber
    // bebedouro ou alimentador
    private int[] necessityLevels = new int[3];

    public Faminto() {
    }
    public Faminto(int da, int ea, int dr){
        this.necessityLevels[0] = da;
        this.necessityLevels[1] = ea;
        this.necessityLevels[2] = dr;
    }
    
    public void waiting(){
        for(int i=0;i<3;i++)
            this.necessityLevels[i] += new Random().nextInt(5);
    }

    public int[] getNecessityLevels() {
        return necessityLevels;
    }

    public void setNecessityLevels(int[] necessityLevels) {
        this.necessityLevels = necessityLevels;
    }
}
