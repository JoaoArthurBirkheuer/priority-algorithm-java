package com.mycompany.vorazesefamintos;

import java.util.*;

public class Simulador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de ciclos: ");
        int ciclos = scanner.nextInt();
        System.out.print("Digite o número de famintos: ");
        int numFamintos = scanner.nextInt();

        // Fila de famintos com as prioridades para comer e beber
        PriorityQueue<Faminto> filaFome = new PriorityQueue<>();
        PriorityQueue<Faminto> filaSede = new PriorityQueue<>();
        Alimentador alimentador = Alimentador.getInstance();
        Bebedouro bebedouro = new Bebedouro();
        Reservatorio reservatorio = Reservatorio.getInstance();

        // Preenche as filas com famintos
        for (int i = 0; i < numFamintos; i++) {
            Faminto faminto = new Faminto();
            filaFome.add(faminto);
            filaSede.add(faminto);
        }

        // Processamento dos ciclos
        for (int ciclo = 1; ciclo <= ciclos; ciclo++) {
            System.out.println("\n=== Ciclo " + ciclo + " ===");

            // Mostra as filas
            System.out.println("\nFila de Fome:");
            for (Faminto f : filaFome) {
                System.out.println(" - " + f);
            }

            System.out.println("\nFila de Sede:");
            for (Faminto f : filaSede) {
                System.out.println(" - " + f);
            }

            // Cria uma lista para intercalar as decisões
            List<Faminto> famintos = new ArrayList<>();
            famintos.addAll(filaFome);
            famintos.addAll(filaSede);

            // Itera pelos famintos alternando entre as decisões
            for (Faminto faminto : famintos) {
                String decisao = faminto.decidirAcao();
                
                if (decisao.equals("comer")) {
                    if (!alimentador.isOcupado()) {
                        alimentador.alimentar(faminto);
                        System.out.println("Faminto " + faminto.id + " começou a se alimentar.");
                    } else {
                        System.out.println("Faminto " + faminto.id + " tentou se alimentar, mas o alimentador estava ocupado.");
                    }
                } else if (decisao.equals("beber")) {
                    if (!bebedouro.isOcupado()) {
                        bebedouro.beber(faminto);
                    } else {
                        System.out.println("Faminto " + faminto.id + " tentou beber, mas o bebedouro estava ocupado.");
                    }
                } else {
                    System.out.println("Faminto " + faminto.id + " decidiu dançar.");
                }
            }

            // Atualiza o estado dos recursos
            alimentador.update();
            bebedouro.update();
            reservatorio.armazenarAgua();

            // Mostra o status final do ciclo
            System.out.println("\n" + alimentador.getStatus());
            System.out.println(bebedouro.getStatus());
            System.out.println(reservatorio.getStatus());
        }

        scanner.close();
    }
}
