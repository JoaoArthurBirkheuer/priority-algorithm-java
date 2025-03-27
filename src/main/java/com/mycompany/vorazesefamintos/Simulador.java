package com.mycompany.vorazesefamintos;

import java.util.*;

public class Simulador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de ciclos: ");
        int ciclos = scanner.nextInt();
        System.out.print("Digite o número de famintos: ");
        int numFamintos = scanner.nextInt();

        Alimentador alimentador = Alimentador.getInstance();
        Bebedouro bebedouro = Bebedouro.getInstance(); // Correção aqui!
        Reservatorio reservatorio = Reservatorio.getInstance(); // Correção aqui!

        List<Faminto> todosFamintos = new ArrayList<>();
        for (int i = 0; i < numFamintos; i++) {
            todosFamintos.add(new Faminto());
        }

        for (int ciclo = 1; ciclo <= ciclos; ciclo++) {
            System.out.println("\n=== Ciclo " + ciclo + " ===");

            System.out.println("\nStatus dos Famintos:");
            for (Faminto f : todosFamintos) {
                System.out.println(" - " + f);
            }

            List<Faminto> filaNeutral = new ArrayList<>();
            for (Faminto f : todosFamintos) {
                if (f.estado == Faminto.Estado.NA_FILA) {
                    filaNeutral.add(f);
                }
            }

            Collections.sort(filaNeutral);

            for (Faminto faminto : filaNeutral) {
                String decisao = faminto.decidirAcao();
                if (decisao.equals("comer") && !alimentador.isOcupado()) {
                    alimentador.alimentar(faminto);
                } else if (decisao.equals("beber") && !bebedouro.isOcupado()) {
                    bebedouro.beber(faminto);
                } else if (decisao.equals("dancar")) {
                    System.out.println("Faminto " + faminto.id + " decidiu dançar.");
                    faminto.estado = Faminto.Estado.DANCA;
                } else {
                    System.out.println("Faminto " + faminto.id + " tentou " + decisao + ", mas o recurso estava ocupado.");
                }
                faminto.incrementarFomeESede();
            }

            alimentador.update();
            bebedouro.update();
            reservatorio.armazenarAgua();

            for (Faminto f : todosFamintos) {
                if (f.estado == Faminto.Estado.DANCA) {
                    f.estado = Faminto.Estado.NA_FILA;
                }
            }

            System.out.println("\n" + alimentador.getStatus());
            System.out.println(bebedouro.getStatus());
            System.out.println(reservatorio.getStatus());
        }

        System.out.println("\n=== Status Final dos Famintos ===");
        for (Faminto f : todosFamintos) {
            System.out.println(" - " + f);
        }

        scanner.close();
    }
}
