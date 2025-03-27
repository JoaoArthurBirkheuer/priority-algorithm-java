package com.mycompany.vorazesefamintos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bebedouro {
    private static Bebedouro instance; // Singleton instance
    private boolean ocupado = false;
    private boolean fluxoAtivo = true;
    private Faminto famintoAtual = null;
    private int remainingTime = 0;
    private Map<Integer, Integer> registroInterrupcao = new HashMap<>();
    private Random random = new Random();
    private Reservatorio reservatorio = Reservatorio.getInstance(); // Usando o Singleton do Reservatório

    private Bebedouro() {}

    public static synchronized Bebedouro getInstance() {
        if (instance == null) {
            instance = new Bebedouro();
        }
        return instance;
    }

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void beber(Faminto f) {
        if (ocupado) {
            System.out.println("Faminto " + f.id + " tentou beber, mas o bebedouro já está ocupado.");
            return;
        }
        fluxoAtivo = random.nextDouble() >= 0.2; // 80% de chance de fluxo ativo

        if (!fluxoAtivo && reservatorio.isVazio()) {
            System.out.println("O faminto " + f.id + " tentou beber, mas não há água disponível!");
            return;
        }
        ocupado = true;
        famintoAtual = f;
        f.estado = Faminto.Estado.BEBENDO;

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
                registroInterrupcao.put(famintoAtual.id, remainingTime);
                System.out.println("Interrupção! Faminto " + famintoAtual.id + " foi interrompido enquanto bebia e voltou para a fila.");
                famintoAtual.estado = Faminto.Estado.NA_FILA;
                ocupado = false;
                famintoAtual = null;
                remainingTime = 0;
            } else {
                remainingTime--;
                if (remainingTime <= 0) {
                    System.out.println("Faminto " + famintoAtual.id + " terminou de beber e voltou para a fila.");
                    famintoAtual.estado = Faminto.Estado.NA_FILA;
                    ocupado = false;
                    famintoAtual = null;
                }
            }
        }
    }

    public synchronized String getStatus() {
        if (ocupado && famintoAtual != null) {
            return "Bebedouro ocupado por Faminto " + famintoAtual.id + " (restam " + remainingTime + " ciclos)";
        } else if (!fluxoAtivo && reservatorio.isVazio()) {
            return "Bebedouro INDISPONÍVEL! Sem fluxo e sem água no reservatório!";
        } else if (!fluxoAtivo) {
            return "Bebedouro sem fluxo! Utilizando reservatório.";
        } else {
            return "Bebedouro livre, com fluxo ativo.";
        }
    }
}
