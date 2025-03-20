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
public class Alimentador {
    private boolean occupied;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
 
    public void diminuirNecessidade(Faminto f){
        int[] v = new int[3];
        v[0] = f.getNecessityLevels()[0];
        v[1] = f.getNecessityLevels()[1] - new Random().nextInt(f.getNecessityLevels()[1]);
        v[2] = f.getNecessityLevels()[2];
        f.setNecessityLevels(v);
    }
    
}
