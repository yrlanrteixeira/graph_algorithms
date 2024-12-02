package main.java.com.graphalgorithms.abstracts;

import main.java.com.graphalgorithms.interfaces.Grafo;

public abstract class GrafoAbstrato implements Grafo {
    protected int numeroDeVertices;
    protected int[] pesosVertices;
    protected String[] rotulosVertices;

    public GrafoAbstrato(int numeroDeVertices) {
        this.numeroDeVertices = numeroDeVertices;
        pesosVertices = new int[numeroDeVertices];
        rotulosVertices = new String[numeroDeVertices];
    }

    public void setPesoVertice(int vertice, int peso) {
        if (vertice >= 0 && vertice < numeroDeVertices) {
            pesosVertices[vertice] = peso;
        } else {
            throw new IllegalArgumentException("Vértice inválido!");
        }
    }

    public int getPesoVertice(int vertice) {
        if (vertice >= 0 && vertice < numeroDeVertices) {
            return pesosVertices[vertice];
        } else {
            throw new IllegalArgumentException("Vértice inválido!");
        }
    }

    public void setRotuloVertice(int vertice, String rotulo) {
        if (vertice >= 0 && vertice < numeroDeVertices) {
            rotulosVertices[vertice] = rotulo;
        } else {
            throw new IllegalArgumentException("Vértice inválido!");
        }
    }

    public String getRotuloVertice(int vertice) {
        if (vertice >= 0 && vertice < numeroDeVertices) {
            return rotulosVertices[vertice];
        } else {
            throw new IllegalArgumentException("Vértice inválido!");
        }
    }


    public abstract boolean removerAresta(int origem, int destino, int peso);

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public abstract boolean adicionarAresta(int origem, int destino, int peso);

    @Override
    public abstract boolean removerAresta(int origem, int destino);

    public abstract boolean saoAdjacentes(int origem, int destino);
}