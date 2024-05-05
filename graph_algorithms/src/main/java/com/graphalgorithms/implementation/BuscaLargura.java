package main.java.com.graphalgorithms.implementation;

import java.util.*;
import main.java.com.graphalgorithms.implementation.GrafoListaAdjacencia;

public class BuscaLargura {
    private GrafoListaAdjacencia grafoLista;

    public BuscaLargura(GrafoListaAdjacencia grafoLista) {
        this.grafoLista = grafoLista;
    }

    public void buscaLargura(int verticeInicial) {
        System.out.println("Busca em Largura: ");
        boolean visitado[] = new boolean[grafoLista.getNumeroDeVertices()];
        LinkedList<Integer> fila = new LinkedList<Integer>();
        visitado[verticeInicial] = true;
        fila.add(verticeInicial);

        Map<Integer, Integer> nivel = new HashMap<>();
        nivel.put(verticeInicial, 0);

        while (fila.size() != 0) {
            verticeInicial = fila.poll();
            System.out.println("Vértice: " + verticeInicial + " - Nível: " + nivel.get(verticeInicial));

            for (Integer vizinho : grafoLista.getVizinhos(verticeInicial)) {
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;
                    nivel.put(vizinho, nivel.get(verticeInicial) + 1);
                    fila.add(vizinho);
                }
            }
        }
    }
}
