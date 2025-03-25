package com.mycompany.vorazesefamintos;

public class Reservatorio {
    private int quantidadeAgua = 0;
    private static Reservatorio instance;

    private Reservatorio() {}

    public static synchronized Reservatorio getInstance() {
        if(instance == null) {
            instance = new Reservatorio();
        }
        return instance;
    }

    public synchronized void armazenarAgua(int qtd) {
        quantidadeAgua += qtd;
    }

    public synchronized boolean retirarAgua(int qtd) {
        if(quantidadeAgua >= qtd) {
            quantidadeAgua -= qtd;
            return true;
        }
        return false;
    }

    public synchronized String getStatus() {
        return "Reservatório: " + quantidadeAgua + " unidades de água.";
    }
}
