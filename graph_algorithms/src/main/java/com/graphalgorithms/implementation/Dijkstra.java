package main.java.com.graphalgorithms.implementation;

import java.util.*;

import java.util.PriorityQueue;
import java.util.Comparator;

public class Dijkstra {
    private final GrafoMatrizAdjacencia grafo;

    public Dijkstra(GrafoMatrizAdjacencia grafo) {
        this.grafo = grafo;
    }

    public int dijkstra(int origem, int destino) {
        int numVertices = grafo.getNumeroDeVertices();
        int[] distancia = new int[numVertices];
        boolean[] visitado = new boolean[numVertices];
        PriorityQueue<Integer> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(v -> distancia[v]));

        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;
        filaPrioridade.add(origem);

        while (!filaPrioridade.isEmpty()) {
            int u = filaPrioridade.poll();
            if (u == destino) return distancia[destino];
            visitado[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visitado[v] && grafo.temAresta(u, v)) {
                    int pesoAresta = grafo.getPesoAresta(u, v);
                    if (distancia[u] + pesoAresta < distancia[v]) {
                        distancia[v] = distancia[u] + pesoAresta;
                        filaPrioridade.add(v);
                    }
                }
            }
        }

        return distancia[destino] != Integer.MAX_VALUE ? distancia[destino] : -1;
    }
}
