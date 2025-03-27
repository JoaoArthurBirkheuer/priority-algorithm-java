package com.mycompany.vorazesefamintos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Alimentador {
    private boolean ocupado = false;
    private Faminto famintoAtual = null;
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
            System.out.println("Tentativa falha! O alimentador já está ocupado.");
            return;
        }
        ocupado = true;
        famintoAtual = f;
        f.estado = Faminto.Estado.COMENDO;

        if (registroInterrupcao.containsKey(f.id)) {
            remainingTime = registroInterrupcao.remove(f.id);
            System.out.println("Faminto " + f.id + " retomou sua refeição (restam " + remainingTime + " ciclos).");
        } else {
            remainingTime = 3 + random.nextInt(3);
            System.out.println("Faminto " + f.id + " começou a se alimentar (necessário " + remainingTime + " ciclos).");
        }
    }

    public synchronized void update() {
        if (ocupado) {
            if (random.nextDouble() < 0.1) { // chance de interrupção
                registroInterrupcao.put(famintoAtual.id, remainingTime);
                System.out.println("Interrupção! Faminto " + famintoAtual.id + " teve que parar de comer (restavam " + remainingTime + " ciclos) e voltou para a fila.");
                famintoAtual.estado = Faminto.Estado.NA_FILA;
                ocupado = false;
                famintoAtual = null;
                remainingTime = 0;
            } else {
                remainingTime--;
                if (remainingTime <= 0) {
                    System.out.println("Faminto " + famintoAtual.id + " terminou de comer e voltou para a fila.");
                    famintoAtual.estado = Faminto.Estado.NA_FILA;
                    ocupado = false;
                    famintoAtual = null;
                }
            }
        }
    }

    public synchronized String getStatus() {
        if (ocupado && famintoAtual != null) {
            return "Alimentador ocupado por Faminto " + famintoAtual.id + " (restam " + remainingTime + " ciclos)";
        } else {
            return "Alimentador livre";
        }
    }
}
