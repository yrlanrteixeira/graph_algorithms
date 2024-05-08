package main.java.com.graphalgorithms.utils;

public class Aresta implements Comparable<Aresta> {
    public int origem;
    public int destino;
    public int peso;

    public int compareTo(Aresta compareAresta) {
        return this.peso - compareAresta.peso;
    }
}