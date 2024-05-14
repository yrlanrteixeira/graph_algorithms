package main.java.com.graphalgorithms.abstracts;

import main.java.com.graphalgorithms.interfaces.Grafo;
import java.util.List;

public abstract class GrafoAbstrato implements Grafo {
    protected int numeroDeVertices;
    protected boolean isDirecionado;

    @Override
    public void adicionarVertice() {
        numeroDeVertices++;
    }

    public abstract boolean removerAresta(int origem, int destino, int peso);

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public boolean temAresta(int origem, int destino) {
        return getVizinhos(origem).contains(destino);
    }

    @Override
    public abstract boolean adicionarAresta(int origem, int destino, int peso);

    @Override
    public abstract boolean removerAresta(int origem, int destino);
}