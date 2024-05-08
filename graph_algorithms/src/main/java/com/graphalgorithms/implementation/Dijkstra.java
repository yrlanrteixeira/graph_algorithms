package main.java.com.graphalgorithms.implementation;

import java.util.*;

public class Dijkstra {
    private static final int INFINITO = Integer.MAX_VALUE;

    public int[] dist;
    private Set<Integer> verticesVisitados;
    private PriorityQueue<No> filaPrioridade;
    private int V; // Número de vértices
    List<List<No>> adj;

    public Dijkstra(int V) {
        this.V = V;
        dist = new int[V];
        verticesVisitados = new HashSet<>();
        filaPrioridade = new PriorityQueue<>(V, new No());
    }

    public void dijkstra(List<List<No>> adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = INFINITO;

        // Adiciona o nó de origem à fila de prioridade
        filaPrioridade.add(new No(src, 0));

        // A distância para a origem é 0
        dist[src] = 0;
        while (verticesVisitados.size() != V) {

            // remove o nó de distância mínima
            // da fila de prioridade
            int u = filaPrioridade.remove().no;

            // adiciona o nó cuja distância é
            // finalizada
            verticesVisitados.add(u);

            e_Vizinhos(u);
        }
    }

    private void e_Vizinhos(int u) {
        int distanciaBorda = -1;
        int novaDistancia = -1;

        // Todos os vizinhos de v
        for (int i = 0; i < adj.get(u).size(); i++) {
            No v = adj.get(u).get(i);

            // Se o nó atual ainda não foi processado
            if (!verticesVisitados.contains(v.no)) {
                distanciaBorda = v.custo;
                novaDistancia = dist[u] + distanciaBorda;

                // Se a nova distância for mais barata em custo
                if (novaDistancia < dist[v.no])
                    dist[v.no] = novaDistancia;

                // Adiciona o nó atual à fila
                filaPrioridade.add(new No(v.no, dist[v.no]));
            }
        }
    }

    public static class No implements Comparator<No> {
        public int no;
        public int custo;

        public No() {
        }

        public No(int no, int custo) {
            this.no = no;
            this.custo = custo;
        }

        @Override
        public int compare(No no1, No no2) {
            return Integer.compare(no1.custo, no2.custo);
        }
    }
}