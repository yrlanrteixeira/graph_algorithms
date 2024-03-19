package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.interfaces.Grafo;

import java.util.ArrayList;
import java.util.List;

public class GrafoMatrizAdjacencia implements Grafo {
    private boolean[][] matrizAdjacencia;
    private int numeroDeVertices;
    private boolean isDirecionado;

    public GrafoMatrizAdjacencia(int numeroDeVertices, boolean isDirecionado) {
        this.numeroDeVertices = numeroDeVertices;
        this.isDirecionado = isDirecionado;
        matrizAdjacencia = new boolean[numeroDeVertices][numeroDeVertices];
    }

    @Override
    public void adicionarVertice() {
        boolean[][] novaMatrizAdjacencia = new boolean[numeroDeVertices + 1][numeroDeVertices + 1];
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                novaMatrizAdjacencia[i][j] = matrizAdjacencia[i][j];
            }
        }
        matrizAdjacencia = novaMatrizAdjacencia;
        numeroDeVertices++;
    }

    @Override
    public boolean adicionarAresta(int origem, int destino) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino] = true;
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem] = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removerAresta(int origem, int destino) {
        if (origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino] = false;
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem] = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public boolean temAresta(int origem, int destino) {
        if (origem < numeroDeVertices && destino < numeroDeVertices) {
            return matrizAdjacencia[origem][destino];
        }
        return false;
    }

    private List<Integer> getAdjacentes(int vertice, boolean isPredecessor) {
        List<Integer> adjacentes = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if ((isPredecessor ? matrizAdjacencia[i][vertice] : matrizAdjacencia[vertice][i])) {
                adjacentes.add(i);
            }
        }
        return adjacentes;
    }
    public List<Integer> getVizinhos(int vertice) {
        return getAdjacentes(vertice, false);
    }

    public List<Integer> getSucessores(int vertice) {
        return getAdjacentes(vertice, false);
    }

    public List<Integer> getPredecessores(int vertice) {
        return getAdjacentes(vertice, true);
    }

    public int getGrau(int vertice) {
        int grau = 0;
        for (int i = 0; i < numeroDeVertices; i++) {
            if (matrizAdjacencia[vertice][i]) {
                grau++;
            }
        }
        return grau;
    }

}