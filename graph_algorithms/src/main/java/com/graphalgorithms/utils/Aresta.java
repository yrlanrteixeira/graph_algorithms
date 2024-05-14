package main.java.com.graphalgorithms.utils;

public class Aresta {
    public int origem;
    public int destino;
    public int peso;

    public Aresta(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public int getDestino(){
        return this.destino;
    }

    public int getOrigem(){
        return this.origem;
    }

    public int getPeso(){
        return this.peso;
    }
}