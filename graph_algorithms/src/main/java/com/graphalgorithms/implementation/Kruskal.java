package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.interfaces.Grafo;
import main.java.com.graphalgorithms.utils.Aresta;

import java.util.*;
public class Kruskal {
    public static List<Aresta> kruskal(GrafoListaAdjacencia grafo) {
        // Lista para armazenar a árvore geradora mínima
        List<Aresta> arvoreGeradoraMinima = new ArrayList<>();

        // Ordena todas as arestas do grafo pelo peso
        List<Aresta> arestas = new ArrayList<>(grafo.getArestas());
        Collections.sort(arestas, Comparator.comparingInt(a -> a.peso));

        // Inicializa uma estrutura de dados para verificar ciclos (usando Union-Find)
        UnionFind unionFind = new UnionFind(grafo.getNumeroDeVertices());

        // Itera sobre todas as arestas ordenadas pelo peso
        for (Aresta aresta : arestas) {
            int origem = aresta.origem;
            int destino = aresta.destino;

            // Verifica se a adição da aresta cria um ciclo
            if (!unionFind.saoConectados(origem, destino)) {
                arvoreGeradoraMinima.add(aresta);
                unionFind.unir(origem, destino);
            }
        }

        return arvoreGeradoraMinima;
    }

    static class UnionFind {
        private int[] parent;

        public UnionFind(int tamanho) {
            parent = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                parent[i] = i;
            }
        }

        public int encontrar(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = encontrar(parent[x]);
        }

        public void unir(int x, int y) {
            parent[encontrar(x)] = encontrar(y);
        }

        public boolean saoConectados(int x, int y) {
            return encontrar(x) == encontrar(y);
        }
    }
}
