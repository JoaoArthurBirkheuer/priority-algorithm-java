package com.mycompany.vorazesefamintos;

import java.util.Random;

public class Reservatorio {
    private static Reservatorio instance; // Singleton instance
    private int quantidadeAgua = 10;
    private Random random = new Random();

    private Reservatorio() {}

    public static synchronized Reservatorio getInstance() {
        if (instance == null) {
            instance = new Reservatorio();
        }
        return instance;
    }

    public synchronized void armazenarAgua() {
        int quantidadeAdicionada = 1 + random.nextInt(3);
        quantidadeAgua += quantidadeAdicionada;
        System.out.println("O reservatório recebeu " + quantidadeAdicionada + " unidades de água.");
    }

    public synchronized boolean retirarAgua(int qtd) {
        if (quantidadeAgua >= qtd) {
            quantidadeAgua -= qtd;
            return true;
        }
        return false;
    }

    public synchronized boolean isVazio() {
        return quantidadeAgua <= 0;
    }

    public synchronized String getStatus() {
        return "Reservatório: " + quantidadeAgua + " unidades de água.";
    }
}
