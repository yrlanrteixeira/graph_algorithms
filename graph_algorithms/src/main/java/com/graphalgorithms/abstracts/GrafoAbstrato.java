package main.java.com.graphalgorithms.abstracts;

import main.java.com.graphalgorithms.interfaces.Grafo;

public abstract class GrafoAbstrato implements Grafo {
    protected int numeroDeVertices;

    public abstract boolean removerAresta(int origem, int destino, int peso);

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public abstract boolean adicionarAresta(int origem, int destino, int peso);

    @Override
    public abstract boolean removerAresta(int origem, int destino);
}