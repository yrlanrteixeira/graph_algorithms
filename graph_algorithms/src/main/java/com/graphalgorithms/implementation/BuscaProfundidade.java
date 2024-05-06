package main.java.com.graphalgorithms.implementation;

import java.util.*;

public class BuscaProfundidade {
    public static void buscaProfundidade(GrafoListaAdjacencia grafo, int verticeInicial) {
        // Marcar todos os vértices como não visitados
        boolean[] visitado = new boolean[grafo.getNumeroDeVertices()];

        Map<Integer, Integer> pais = new HashMap<>();

        List<String> arestasRetorno = new ArrayList<>();

        buscaProfundidadeRecursiva(grafo, verticeInicial, visitado, pais, arestasRetorno, 0);

        System.out.println("Arestas de Retorno: ");
        for (String aresta : arestasRetorno) {
            System.out.println(aresta);
        }

    }

    public static void buscaProfundidadeRecursiva(GrafoListaAdjacencia grafo, int vertice, boolean[] visitado,
                                                  Map<Integer, Integer> pais, List<String> arestasDeRetorno, int nivel) {

        // Marca o vértice atual como visitado
        visitado[vertice] = true;

        System.out.println("Vértice: " + vertice + ", Nível: " + nivel);

        for (Integer vizinho : grafo.getVizinhos(vertice)) {
            if (!visitado[vizinho]) {
                pais.put(vizinho, vertice);
                buscaProfundidadeRecursiva(grafo, vizinho, visitado, pais, arestasDeRetorno, nivel + 1);
            } else if (!pais.get(vertice).equals(vizinho)) {
                arestasDeRetorno.add("(" + vertice + ", " + vizinho + ")");
            }
        }
    }
}
