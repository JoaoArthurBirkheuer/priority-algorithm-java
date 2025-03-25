package com.mycompany.vorazesefamintos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bebedouro {
    private boolean ocupado = false;
    private boolean fluxoAtivo = true;
    private int currentFamintoId = -1;
    private int remainingTime = 0;
    private Map<Integer, Integer> registroInterrupcao = new HashMap<>();
    private Random random = new Random();
    private Reservatorio reservatorio = Reservatorio.getInstance();

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void beber(Faminto f) {
        if (ocupado) {
            System.out.println("Faminto " + f.id + " tentou beber, mas o bebedouro já está ocupado.");
            return;
        }

        fluxoAtivo = random.nextDouble() >= 0.2; // 80% de chance de ter fluxo

        if (!fluxoAtivo && reservatorio.isVazio()) {
            System.out.println("O faminto " + f.id + " tentou beber, mas não há água disponível!");
            return;
        }

        ocupado = true;
        currentFamintoId = f.id;

        if (registroInterrupcao.containsKey(f.id)) {
            remainingTime = registroInterrupcao.remove(f.id);
        } else {
            remainingTime = 2 + random.nextInt(2);
        }

        if (!fluxoAtivo) {
            if (reservatorio.retirarAgua(1)) {
                System.out.println("O faminto " + f.id + " está bebendo do reservatório.");
            } else {
                System.out.println("O faminto " + f.id + " não conseguiu água e terá que esperar.");
            }
        } else {
            System.out.println("O faminto " + f.id + " está bebendo do bebedouro.");
        }
    }

    public synchronized void update() {
        if (ocupado) {
            if (random.nextDouble() < 0.1) {
                registroInterrupcao.put(currentFamintoId, remainingTime);
                System.out.println("O faminto " + currentFamintoId + " foi interrompido enquanto bebia!");
                ocupado = false;
                currentFamintoId = -1;
                remainingTime = 0;
            } else {
                remainingTime--;
                if (remainingTime <= 0) {
                    System.out.println("O faminto " + currentFamintoId + " terminou de beber.");
                    ocupado = false;
                    currentFamintoId = -1;
                }
            }
        }
    }

    public synchronized String getStatus() {
        if (ocupado) {
            return "Bebedouro ocupado por Faminto " + currentFamintoId + " (restam " + remainingTime + " ciclos)";
        } else if (!fluxoAtivo && reservatorio.isVazio()) {
            return "Bebedouro INDISPONÍVEL! Sem fluxo e sem água no reservatório!";
        } else if (!fluxoAtivo) {
            return "Bebedouro sem fluxo! Utilizando reservatório.";
        } else {
            return "Bebedouro livre, com fluxo ativo.";
        }
    }
}
