package com.mycompany.vorazesefamintos;

import java.util.PriorityQueue;
import java.util.Scanner;
import com.mycompany.vorazesefamintos.Bebedouro;

public class Simulador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de ciclos: ");
        int ciclos = scanner.nextInt();
        System.out.print("Digite o número de famintos: ");
        int numFamintos = scanner.nextInt();

        PriorityQueue<Faminto> filaFome = new PriorityQueue<>();
        PriorityQueue<Faminto> filaSede = new PriorityQueue<>();
        Alimentador alimentador = Alimentador.getInstance();
        Bebedouro bebedouro = new Bebedouro();

        for (int i = 0; i < numFamintos; i++) {
            filaFome.add(new Faminto());
            filaSede.add(new Faminto());
        }

        for (int i = 1; i <= ciclos; i++) {
            System.out.println("=== Ciclo " + i + " ===");

            if (!alimentador.isOcupado() && !filaFome.isEmpty()) {
                Faminto faminto = filaFome.poll();
                alimentador.alimentar(faminto);
            }

            if (!bebedouro.isOcupado() && !filaSede.isEmpty()) {
                Faminto faminto = filaSede.poll();
                bebedouro.beber(faminto);
            }

            alimentador.update();
            bebedouro.update();

            System.out.println(alimentador.getStatus());
            System.out.println(bebedouro.getStatus());

            for (Faminto f : filaFome) {
                f.incrementarFomeESede();
            }
            for (Faminto f : filaSede) {
                f.incrementarFomeESede();
            }
        }

        scanner.close();
    }
}

