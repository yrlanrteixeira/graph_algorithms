package main.java.com.graphalgorithms.implementation;

import java.util.*;

public class Dijkstra {
    // private static final int INFINITO = Integer.MAX_VALUE;

    // public int[] dist;
    // private Set<Integer> verticesVisitados;
    // private PriorityQueue<No> filaPrioridade;
    // private int V; // Número de vértices
    // List<List<No>> adj;

    // public Dijkstra(int V) {
    // this.V = V;
    // dist = new int[V];
    // verticesVisitados = new HashSet<>();
    // filaPrioridade = new PriorityQueue<>(V, new No());
    // }

    // public void dijkstra(List<List<No>> adj, int src) {
    // this.adj = adj;

    // for (int i = 0; i < V; i++)
    // dist[i] = INFINITO;

    // // Adiciona o nó de origem à fila de prioridade
    // filaPrioridade.add(new No(src, 0));

    // // A distância para a origem é 0
    // dist[src] = 0;
    // while (verticesVisitados.size() != V) {

    // // remove o nó de distância mínima
    // // da fila de prioridade
    // int u = filaPrioridade.remove().no;

    // // adiciona o nó cuja distância é
    // // finalizada
    // verticesVisitados.add(u);

    // e_Vizinhos(u);
    // }
    // }

    // private void e_Vizinhos(int u) {
    // int distanciaBorda = -1;
    // int novaDistancia = -1;

    // // Todos os vizinhos de v
    // for (int i = 0; i < adj.get(u).size(); i++) {
    // No v = adj.get(u).get(i);

    // // Se o nó atual ainda não foi processado
    // if (!verticesVisitados.contains(v.no)) {
    // distanciaBorda = v.custo;
    // novaDistancia = dist[u] + distanciaBorda;

    // // Se a nova distância for mais barata em custo
    // if (novaDistancia < dist[v.no])
    // dist[v.no] = novaDistancia;

    // // Adiciona o nó atual à fila
    // filaPrioridade.add(new No(v.no, dist[v.no]));
    // }
    // }
    // }

    // public static class No implements Comparator<No> {
    // public int no;
    // public int custo;

    // public No() {
    // }

    // public No(int no, int custo) {
    // this.no = no;
    // this.custo = custo;
    // }

    // @Override
    // public int compare(No no1, No no2) {
    // return Integer.compare(no1.custo, no2.custo);
    // }
    // }

    private final GrafoMatrizAdjacencia grafo;

    public Dijkstra(GrafoMatrizAdjacencia grafo) {
        this.grafo = grafo;
    }

    public int dijkstra(int origem, int destino) {
        int numVertices = grafo.getNumeroDeVertices();
        int[] distancia = new int[numVertices];
        boolean[] visitado = new boolean[numVertices];

        // Inicializa as distâncias com infinito e marca todos os vértices como não
        // visitados
        for (int i = 0; i < numVertices; i++) {
            distancia[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }

        // A distância da origem para ela mesma é zero
        distancia[origem] = 0;

        // Encontra o caminho mais curto para o vértice de destino
        for (int i = 0; i < numVertices - 1; i++) {
            // Encontra o vértice com a menor distância ainda não visitado
            int verticeMenorDistancia = encontrarVerticeMenorDistancia(distancia, visitado);
            visitado[verticeMenorDistancia] = true;

            // Se o vértice de destino for alcançado, retorna a distância
            if (verticeMenorDistancia == destino) {
                return distancia[destino];
            }

            // Atualiza as distâncias para os vértices adjacentes ao vértice atual
            for (int v = 0; v < numVertices; v++) {
                if (!visitado[v] && grafo.temAresta(verticeMenorDistancia, v)
                        && distancia[verticeMenorDistancia] != Integer.MAX_VALUE
                        && distancia[verticeMenorDistancia]
                                + grafo.getPesoAresta(verticeMenorDistancia, v) < distancia[v]) {
                    distancia[v] = distancia[verticeMenorDistancia] + grafo.getPesoAresta(verticeMenorDistancia, v);
                }
            }
        }

        // Se não for possível alcançar o destino, retorna -1
        return -1;
    }

    private int encontrarVerticeMenorDistancia(int[] distancia, boolean[] visitado) {
        int minDistancia = Integer.MAX_VALUE;
        int verticeMenorDistancia = -1;

        for (int i = 0; i < distancia.length; i++) {
            if (!visitado[i] && distancia[i] <= minDistancia) {
                minDistancia = distancia[i];
                verticeMenorDistancia = i;
            }
        }

        return verticeMenorDistancia;
    }
}