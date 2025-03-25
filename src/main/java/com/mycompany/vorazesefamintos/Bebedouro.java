package com.mycompany.vorazesefamintos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bebedouro {
    private boolean ocupado = false;
    private int currentFamintoId = -1;
    private int remainingTime = 0;
    private Map<Integer, Integer> registroInterrupcao = new HashMap<>();
    private Random random = new Random();
    private int reservatorio = 10;

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void beber(Faminto f) {
        if (ocupado) return;

        ocupado = true;
        currentFamintoId = f.id;

        if (registroInterrupcao.containsKey(f.id)) {
            remainingTime = registroInterrupcao.remove(f.id);
        } else {
            remainingTime = 2 + random.nextInt(2);
        }
    }

    public synchronized void update() {
        if (ocupado) {
            if (random.nextDouble() < 0.1) {
                registroInterrupcao.put(currentFamintoId, remainingTime);
                ocupado = false;
                currentFamintoId = -1;
                remainingTime = 0;
            } else {
                remainingTime--;
                if (remainingTime <= 0) {
                    ocupado = false;
                    currentFamintoId = -1;
                }
            }
        }
    }

    public synchronized String getStatus() {
        return ocupado ? "Bebedouro ocupado por Faminto " + currentFamintoId + " (restam " + remainingTime + " ciclos)" : "Bebedouro livre";
    }
}

