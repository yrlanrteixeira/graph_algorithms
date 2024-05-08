package main.java.com.graphalgorithms.implementation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BuscaLargura {
    private final GrafoListaAdjacencia grafoLista;

    public BuscaLargura(GrafoListaAdjacencia grafoLista) {
        this.grafoLista = grafoLista;
    }

    public void buscaLargura(int verticeInicial) {
        System.out.println("Busca em Largura: ");
        boolean[] visitado = new boolean[grafoLista.getNumeroDeVertices()];
        LinkedList<Integer> fila = new LinkedList<>();
        visitado[verticeInicial] = true;
        fila.add(verticeInicial);

        Map<Integer, Integer> nivel = new HashMap<>();
        nivel.put(verticeInicial, 0);

        while (!fila.isEmpty()) {
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
