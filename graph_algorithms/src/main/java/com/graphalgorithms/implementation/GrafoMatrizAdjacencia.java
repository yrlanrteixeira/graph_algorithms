package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.interfaces.Grafo;

import java.util.*;

public class GrafoMatrizAdjacencia implements Grafo {
    private boolean[][] matrizAdjacencia;
    private int numeroDeVertices;
    private final boolean isDirecionado;

    public GrafoMatrizAdjacencia(int numeroDeVertices, boolean isDirecionado) {
        this.numeroDeVertices = numeroDeVertices;
        this.isDirecionado = isDirecionado;
        matrizAdjacencia = new boolean[numeroDeVertices][numeroDeVertices];
    }

    @Override
    public void adicionarVertice() {
        boolean[][] novaMatrizAdjacencia = new boolean[numeroDeVertices + 1][numeroDeVertices + 1];
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                novaMatrizAdjacencia[i][j] = matrizAdjacencia[i][j];
            }
        }
        matrizAdjacencia = novaMatrizAdjacencia;
        numeroDeVertices++;
    }

    @Override
    public boolean adicionarAresta(int origem, int destino) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino] = true;
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem] = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removerAresta(int origem, int destino) {
        if (origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino] = false;
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem] = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public boolean temAresta(int origem, int destino) {
        if (origem < numeroDeVertices && destino < numeroDeVertices) {
            return matrizAdjacencia[origem][destino];
        }
        return false;
    }

    private List<Integer> getAdjacentes(int vertice, boolean isPredecessor) {
        List<Integer> adjacentes = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if ((isPredecessor ? matrizAdjacencia[i][vertice] : matrizAdjacencia[vertice][i])) {
                adjacentes.add(i);
            }
        }
        return adjacentes;
    }

    public List<Integer> getVizinhos(int vertice) {
        return getAdjacentes(vertice, false);
    }

    public List<Integer> getSucessores(int vertice) {
        return getAdjacentes(vertice, false);
    }

    public List<Integer> getPredecessores(int vertice) {
        return getAdjacentes(vertice, true);
    }

    public int[] getGrau(int vertice) {
        int grauEntrada = 0;
        int grauSaida = 0;
        for (int i = 0; i < numeroDeVertices; i++) {
            if (matrizAdjacencia[i][vertice]) {
                grauEntrada++;
            }
            if (matrizAdjacencia[vertice][i]) {
                grauSaida++;
            }
        }
        if (isDirecionado) {
            return new int[] { grauEntrada, grauSaida };
        } else {
            return new int[] { grauEntrada + grauSaida };
        }
    }

    public boolean isSimples() {
        // Verifica se há laços ou múltiplas arestas
        for (int i = 0; i < numeroDeVertices; i++) {
            if (matrizAdjacencia[i][i]) {
                return false;
            }
        }

        return true;
    }

    public boolean isRegular() {
        int[] grau = getGrau(0);

        for (int i = 1; i < numeroDeVertices; i++) {
            if (getGrau(i) != grau) {
                return false;
            }
        }

        return true;
    }

    public boolean isCompleto() {
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (i != j && !matrizAdjacencia[i][j]) {
                    return false;
                }
                if (i == j && matrizAdjacencia[i][j]) {
                    return false;
                }
            }
        }

        return false;
    }

    private boolean isBipartiteUtil(int[] cores, int inicio) {
        Queue<Integer> fila = new LinkedList<>();
        fila.offer(inicio);
        cores[inicio] = 0;

        while (!fila.isEmpty()) {
            int vertice = fila.poll();

            for (int vizinho = 0; vizinho < matrizAdjacencia.length; vizinho++) {
                if (matrizAdjacencia[vertice][vizinho]) {
                    if (cores[vizinho] == -1) {
                        cores[vizinho] = 1 - cores[vertice];
                        fila.offer(vizinho);
                    } else if (cores[vizinho] == cores[vertice]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isBipartido() {
        int[] cores = new int[numeroDeVertices];
        Arrays.fill(cores, -1);

        for (int i = 0; i < numeroDeVertices; i++) {
            if (cores[i] == -1) {
                if (!isBipartiteUtil(cores, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void imprimeGrafo() {
        System.out.println("Matriz de Adjacência: \n");
        for (int colunas = -1; colunas < numeroDeVertices; colunas++) {
            if (colunas == -1) {
                System.out.print("   ");
            } else
                System.out.print("V" + colunas + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < numeroDeVertices; i++) {
            System.out.print("V" + i + "  ");
            for (int j = 0; j < numeroDeVertices; j++) {
                System.out.print(matrizAdjacencia[i][j] ? 1 + "  " : 0 + "  ");
            }
            System.out.println(" ");
        }

        System.out.println("\n");
    }
}