package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.abstracts.GrafoAbstrato;
import main.java.com.graphalgorithms.utils.Aresta;

import java.util.*;

public class GrafoMatrizAdjacencia extends GrafoAbstrato {
    private final int numeroDeVertices;
    private final boolean isDirecionado;

    private final Aresta[][] matrizAdjacencia;

    public GrafoMatrizAdjacencia(int numeroDeVertices, boolean isDirecionado) {
        this.numeroDeVertices = numeroDeVertices;
        this.isDirecionado = isDirecionado;
        matrizAdjacencia = new Aresta[numeroDeVertices][numeroDeVertices];
    }

    public boolean adicionarAresta(int origem, int destino) {
        return adicionarAresta(origem, destino, 1);
    }

    @Override
    public boolean adicionarAresta(int origem, int destino, int peso) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino] = new Aresta(origem, destino, peso);
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem] = new Aresta(destino, origem, peso);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removerAresta(int origem, int destino) {
        if (origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino] = null;
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem] = null;
            }
            return true;
        }
        return false;
    }

    public int getPesoAresta(int origem, int destino) {
        if (matrizAdjacencia[origem][destino] != null) {
            return matrizAdjacencia[origem][destino].peso;
        }

        return Integer.MAX_VALUE;
    }

    public List<Aresta> getArestas() {
        List<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                Aresta aresta = matrizAdjacencia[i][j];
                if (aresta != null) {
                    arestas.add(aresta);
                }
            }
        }

        return arestas;
    }

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @Override
    public boolean temAresta(int origem, int destino) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            if (matrizAdjacencia[origem][destino] != null) {
                return true; // Aresta encontrada
            }
        }
        return false; // Não há aresta entre os vértices
    }

    private List<Integer> getAdjacentes(int vertice, boolean isPredecessor) {
        List<Integer> adjacentes = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if ((isPredecessor ? matrizAdjacencia[i][vertice] : matrizAdjacencia[vertice][i]) != null) {
                adjacentes.add(i);
            }
        }
        return adjacentes;
    }

    @Override
    public List<Integer> getVizinhos(int vertice) {
        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if (matrizAdjacencia[vertice][i] != null) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    @Override
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
            if (matrizAdjacencia[i][vertice] != null) {
                grauEntrada++;
            }
            if (matrizAdjacencia[vertice][i] != null) {
                grauSaida++;
            }
        }
        if (isDirecionado) {
            return new int[] { grauEntrada, grauSaida };
        } else {
            return new int[] { grauEntrada + grauSaida };
        }
    }

    @Override
    public boolean isSimples() {
        for (int i = 0; i < numeroDeVertices; i++) {
            if (matrizAdjacencia[i][i] != null) {
                return false; // Grafo possui laço, portanto não é simples
            }
        }

        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (i != j && matrizAdjacencia[i][j] != null && matrizAdjacencia[j][i] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isRegular() {
        int[] grau = getGrau(0);
        for (int i = 1; i < numeroDeVertices; i++) {
            if (!Arrays.equals(getGrau(i), grau)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCompleto() {
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (i != j && matrizAdjacencia[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isBipartido() {
        int[] cores = new int[numeroDeVertices];
        Arrays.fill(cores, -1);

        for (int i = 0; i < numeroDeVertices; i++) {
            if (cores[i] == -1) {
                if (!colorirBipartido(i, cores)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean colorirBipartido(int vertice, int[] cores) {
        Queue<Integer> fila = new LinkedList<>();
        fila.offer(vertice);
        cores[vertice] = 0;

        while (!fila.isEmpty()) {
            int v = fila.poll();

            for (int vizinho = 0; vizinho < numeroDeVertices; vizinho++) {
                if (matrizAdjacencia[v][vizinho] != null) {
                    if (cores[vizinho] == -1) {
                        cores[vizinho] = 1 - cores[v];
                        fila.offer(vizinho);
                    } else if (cores[vizinho] == cores[v]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
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
                if (matrizAdjacencia[i][j] != null) {
                    System.out.print(matrizAdjacencia[i][j].peso + " ");
                } else {
                    System.out.print("0 ");
                }

            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    @Override
    public List<Integer> ordenacaoTopologica() {
        OrdenacaoTopologica ordenacao = new OrdenacaoTopologica(this);
        return ordenacao.ordenar();
    }

}