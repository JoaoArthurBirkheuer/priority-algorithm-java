package com.mycompany.vorazesefamintos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Alimentador {
    private boolean ocupado = false;
    private int currentFamintoId = -1;
    private int remainingTime = 0;
    private Map<Integer, Integer> registroInterrupcao = new HashMap<>();
    private Random random = new Random();
    private static Alimentador instance;

    private Alimentador() {}

    public static synchronized Alimentador getInstance() {
        if(instance == null) {
            instance = new Alimentador();
        }
        return instance;
    }

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void alimentar(Faminto f) {
        if (ocupado) {
            System.out.println("[ALIMENTADOR] Tentativa falha! O alimentador já está ocupado.");
            return;
        }

        ocupado = true;
        currentFamintoId = f.id;

        if (registroInterrupcao.containsKey(f.id)) {
            remainingTime = registroInterrupcao.remove(f.id);
            System.out.println("[ALIMENTADOR] Faminto " + f.id + " retomou sua refeição (restam " + remainingTime + " ciclos).");
        } else {
            remainingTime = 3 + random.nextInt(3);
            System.out.println("[ALIMENTADOR] Faminto " + f.id + " começou a se alimentar (necessário " + remainingTime + " ciclos).");
        }
    }

    public synchronized void update() {
        if (ocupado) {
            if (random.nextDouble() < 0.1) { // 10% de chance de interrupção
                registroInterrupcao.put(currentFamintoId, remainingTime);
                System.out.println("[ALIMENTADOR] Interrupção! Faminto " + currentFamintoId + " teve que parar de comer (restavam " + remainingTime + " ciclos).");
                ocupado = false;
                currentFamintoId = -1;
                remainingTime = 0;
            } else {
                remainingTime--;
                if (remainingTime <= 0) {
                    System.out.println("[ALIMENTADOR] Faminto " + currentFamintoId + " terminou de comer.");
                    ocupado = false;
                    currentFamintoId = -1;
                }
            }
        }
    }

    public synchronized String getStatus() {
        return ocupado ? "Alimentador ocupado por Faminto " + currentFamintoId + " (restam " + remainingTime + " ciclos)" : "Alimentador livre";
    }
}
