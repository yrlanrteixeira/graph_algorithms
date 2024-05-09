package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.utils.Aresta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prim {
    private GrafoMatrizAdjacencia grafo;

    public Prim(GrafoMatrizAdjacencia grafo) {
        this.grafo = grafo;
    }

    public List<Aresta> prim() {
        int numVertices = grafo.getNumeroDeVertices();
        boolean[] visitado = new boolean[numVertices];
        List<Aresta> mst = new ArrayList<>();
        int[] chave = new int[numVertices];
        int[] pai = new int[numVertices];
        Arrays.fill(chave, Integer.MAX_VALUE);
        Arrays.fill(pai, -1);
        chave[0] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = minKey(chave, visitado);
            visitado[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (grafo.temAresta(u, v) && !visitado[v] && grafo.getPesoAresta(u, v) < chave[v]) {
                    pai[v] = u;
                    chave[v] = grafo.getPesoAresta(u, v);
                }
            }
        }

        for (int j = 1; j < numVertices; j++) {
            if (pai[j] != -1) {
                mst.add(new Aresta(pai[j], j, grafo.getPesoAresta(pai[j], j)));
            }
        }

        return mst;
    }

    private int minKey(int[] chave, boolean[] visitado) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int i = 0; i < chave.length; i++) {
            if (!visitado[i] && chave[i] < min) {
                min = chave[i];
                min_index = i;
            }
        }
        return min_index;
    }
}
