package com.mycompany.vorazesefamintos;
import java.util.Random;

public class Faminto implements Comparable<Faminto> {
    private static int contador = 0;
    public int id;
    public int nivelFome;
    public int nivelSede;
    public int ciclosSemComer;
    public int ciclosSemBeber;
    private Random random = new Random();

    public Faminto() {
        this.id = ++contador;
        this.nivelFome = random.nextInt(10) + 1;
        this.nivelSede = random.nextInt(10) + 1;
        this.ciclosSemComer = 0;
        this.ciclosSemBeber = 0;
    }

    public void incrementarFomeESede() {
        nivelFome += random.nextInt(3);
        nivelSede += random.nextInt(3);
        ciclosSemComer++;
        ciclosSemBeber++;
    }

    public String decidirAcao() {
        double chance = random.nextDouble();
        if (chance < 0.45) {
            return "comer";
        } else if (chance < 0.9) {
            return "beber";
        } else {
            return "dancar";
        }
    }

    @Override
    public int compareTo(Faminto outro) {
        return Integer.compare(outro.nivelFome + outro.nivelSede, this.nivelFome + this.nivelSede);
    }

    @Override
    public String toString() {
        return "Faminto " + id + " [Fome: " + nivelFome + ", Sede: " + nivelSede + "]";
    }
}
