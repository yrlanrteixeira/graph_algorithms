package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.abstracts.GrafoAbstrato;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GrafoListaAdjacencia extends GrafoAbstrato {
    private final int numeroDeVertices;
    private final boolean isDirecionado;
    private final LinkedList<Integer>[] listaAdjacencia;

    /**
     * Construtor do grafo de adjacência
     * 
     * @param numeroDeVertices Número de vértices do grafo (tipo int)
     * @param isDirecionado    Grafo direcionado (true), grafo NÃO direcionado
     *                         (false)
     */
    public GrafoListaAdjacencia(int numeroDeVertices, boolean isDirecionado) {
        this.numeroDeVertices = numeroDeVertices;
        this.isDirecionado = isDirecionado;
        this.listaAdjacencia = new LinkedList[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            listaAdjacencia[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean adicionarAresta(int origem, int destino) {
        // Verifica se a aresta é válida
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            listaAdjacencia[origem].add(destino);

            // Se for um grafo não direcionado, adiciona nas duas direções
            if (!isDirecionado)
                listaAdjacencia[destino].add(origem);

            return true;
        }
        return false;
    }

    @Override
    public boolean removerAresta(int origem, int destino) {
        // Verifica se a aresta é válida
        if (origem < numeroDeVertices && destino < numeroDeVertices) {
            listaAdjacencia[origem].remove(Integer.valueOf(destino));

            // Se for um grafo não direcionado, adiciona nas duas direções
            if (!isDirecionado)
                listaAdjacencia[destino].remove(Integer.valueOf(origem));

            return true;
        }

        return false;
    }

    @Override
    public List<Integer> getVizinhos(int vertice) {
        return listaAdjacencia[vertice];
    }

    @Override
    public List<Integer> getSucessores(int vertice) {
        return listaAdjacencia[vertice];
    }

    @Override
    public List<Integer> getPredecessores(int vertice) {
        List<Integer> predecessores = new LinkedList<>();

        for (int i = 0; i < vertice; i++) {
            if (listaAdjacencia[i].contains(vertice)) {
                predecessores.add(i);
            }
        }

        return predecessores;
    }

    @Override
    public int[] getGrau(int vertice) {
        if (listaAdjacencia[vertice].isEmpty())
            return new int[] { 0 };

        if (isDirecionado) {
            int grauEntrada = 0;
            for (int i = 0; i < numeroDeVertices; i++) {
                if (listaAdjacencia[i].contains(vertice))
                    grauEntrada++;
            }

            int grauSaida = listaAdjacencia[vertice].size();
            return new int[] { grauEntrada + grauSaida };
        }

        return new int[] { listaAdjacencia[vertice].size() };
    }

    public boolean isSimples() {
        for (int i = 0; i < numeroDeVertices; i++) {
            for (Integer vizinho : listaAdjacencia[i]) {
                if (i == vizinho)
                    return false; // Grafo possui laço, portanto não é simples
            }
        }

        return true;
    }

    public boolean isRegular() {
        int[] grau = getGrau(0);

        for (int i = 1; i < numeroDeVertices; i++) {
            if (getGrau(i) != grau)
                return false;
        }

        return true;
    }

    public boolean isCompleto() {
        for (int i = 0; i < numeroDeVertices; i++) {
            if (listaAdjacencia[i].size() != numeroDeVertices - 1)
                return false;
        }

        return true;
    }

    private boolean isBipartiteUtil(int src, int[] cor) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        cor[src] = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Integer v : listaAdjacencia[u]) {
                if (cor[v] == -1) {
                    cor[v] = 1 - cor[u];
                    queue.add(v);
                } else if (cor[v] == cor[u]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartido() {
        int[] cor = new int[numeroDeVertices];
        Arrays.fill(cor, -1);

        for (int i = 0; i < numeroDeVertices; i++) {
            if (cor[i] == -1) {
                if (!isBipartiteUtil(i, cor))
                    return false;
            }
        }

        return true;
    }

    @Override
    public void adicionarVertice() {
    }

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public boolean temAresta(int origem, int destino) {
        return false;
    }

    public void imprimeGrafo() {
        System.out.println("Lista de Adjacência: \n");
        for (int i = 0; i < numeroDeVertices; i++) {
            System.out.print(i + " --> ");
            for (Integer n : listaAdjacencia[i])
                System.out.print("|" + n);
            System.out.println();
        }
        System.out.println("\n");
    }

}
