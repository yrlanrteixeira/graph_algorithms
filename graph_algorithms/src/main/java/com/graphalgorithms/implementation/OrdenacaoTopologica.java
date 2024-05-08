package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.interfaces.Grafo;

import java.util.ArrayList;
import java.util.List;

public class OrdenacaoTopologica {
    private final Grafo grafo;

    public OrdenacaoTopologica(Grafo grafo) {
        this.grafo = grafo;
    }

    public List<Integer> ordenar() {
        List<Integer> ordenacao = new ArrayList<>();
        List<Integer> verticesSemPredecessores = new ArrayList<>();
        int[] grau = new int[grafo.getNumeroDeVertices()];

        for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
            grau[i] = grafo.getGrau(i)[0];
            if (grau[i] == 0) {
                verticesSemPredecessores.add(i);
            }
        }

        while (!verticesSemPredecessores.isEmpty()) {
            int vertice = verticesSemPredecessores.remove(0);
            ordenacao.add(vertice);

            for (int vizinho : grafo.getVizinhos(vertice)) {
                grau[vizinho]--;
                if (grau[vizinho] == 0) {
                    verticesSemPredecessores.add(vizinho);
                }
            }
        }

        if (ordenacao.size() != grafo.getNumeroDeVertices()) {
            return null;
        }
        return ordenacao;
    }

}