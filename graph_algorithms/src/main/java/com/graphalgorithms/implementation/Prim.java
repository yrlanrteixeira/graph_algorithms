package main.java.com.graphalgorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import main.java.com.graphalgorithms.utils.Aresta;

// public class Prim {
//     private static final int INF = Integer.MAX_VALUE;
//     private final int V;
//     private final int[][] grafo;

//     public Prim(int V, int[][] grafo) {
//         this.V = V;
//         this.grafo = grafo;
//     }

//     // Encontrar o vértice com a mínima chave
//     int minKey(int[] key, Boolean[] mstSet) {
//         int min = INF, min_index = -1;

//         for (int v = 0; v < V; v++)
//             if (!mstSet[v] && key[v] < min) {
//                 min = key[v];
//                 min_index = v;
//             }

//         return min_index;
//     }

//     // Construir e imprimir a MST para um gráfico representado usando matriz de adjacência
//     public Aresta[] primMST() {
//         int[] parent = new int[V];
//         Aresta[] mst = new Aresta[V];
//         int[] key = new int[V];

//         Boolean[] mstSet = new Boolean[V];

//         for (int i = 0; i < V; i++) {
//             key[i] = INF;
//             mstSet[i] = false;
//         }

//         key[0] = 0;
//         parent[0] = -1;

//         for (int count = 0; count < V - 1; count++) {
//             int u = minKey(key, mstSet);

//             mstSet[u] = true;

//             for (int v = 0; v < V; v++)

//                 // graph[u][v] é diferente de zero apenas para vértices adjacentes de m
//                 // mstSet[v] é falso para vértices ainda não incluídos na MST
//                 // Atualize a chave somente se graph[u][v] for menor que a chave[v]
//                 if (grafo[u][v] != 0 && !mstSet[v] && grafo[u][v] < key[v]) {
//                     parent[v] = u;
//                     key[v] = grafo[u][v];
//                 }
//         }

//         // Constrói a matriz de arestas
//         for (int i = 1; i < V; i++) {
//             Aresta aresta = new Aresta();
//             aresta.origem = parent[i];
//             aresta.destino = i;
//             aresta.peso = grafo[i][parent[i]];
//             mst[i] = aresta;
//         }

//         return mst;
//     }
// }

public class Prim {
    private final GrafoMatrizAdjacencia grafo;

    public Prim(GrafoMatrizAdjacencia grafo) {
        this.grafo = grafo;
    }

    public List<Aresta> prim() {
        int numVertices = grafo.getNumeroDeVertices();

        boolean[] visitado = new boolean[numVertices];
        List<Aresta> mst = new ArrayList<>();

        visitado[0] = true;

        for (int i = 0; i < numVertices - 1; i++) {
            int menorPeso = Integer.MAX_VALUE;
            int verticeOrigem = -1;
            int verticeDestino = -1;

            for (int j = 0; j < numVertices; j++) {
                if (visitado[j]) {
                    for (int k = 0; k < numVertices; k++) {
                        if (!visitado[k] && grafo.temAresta(j, k)) {
                            int peso = grafo.getPesoAresta(j, k);
                            if (peso < menorPeso) {
                                menorPeso = peso;
                                verticeOrigem = j;
                                verticeDestino = k;
                            }
                        }
                    }
                }
            }

            if (verticeOrigem != -1 && verticeDestino != -1) {
                mst.add(new Aresta(verticeOrigem, verticeDestino, menorPeso));
                visitado[verticeDestino] = true;
            }
        }

        return mst;
    }

}