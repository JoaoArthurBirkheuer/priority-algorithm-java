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

    @Override
    public int compareTo(Faminto outro) {
        if (this.nivelFome != outro.nivelFome) return Integer.compare(outro.nivelFome, this.nivelFome);
        if (this.nivelSede != outro.nivelSede) return Integer.compare(outro.nivelSede, this.nivelSede);
        if (this.ciclosSemComer != outro.ciclosSemComer) return Integer.compare(outro.ciclosSemComer, this.ciclosSemComer);
        if (this.ciclosSemBeber != outro.ciclosSemBeber) return Integer.compare(outro.ciclosSemBeber, this.ciclosSemBeber);
        return Integer.compare(this.id, outro.id);
    }

    @Override
    public String toString() {
        return "Faminto " + id + " [Fome: " + nivelFome + ", Sede: " + nivelSede + "]";
    }
}

