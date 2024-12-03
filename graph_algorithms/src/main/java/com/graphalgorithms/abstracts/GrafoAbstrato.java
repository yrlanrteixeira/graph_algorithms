package com.graphalgorithms.abstracts;

import com.graphalgorithms.interfaces.Grafo;

import java.util.List;

public abstract class GrafoAbstrato implements Grafo {

    protected final int numeroDeVertices;
    protected final int[] pesosVertices;
    protected final String[] rotulosVertices;
    public abstract int[][] floydWarshall();

    /**
     * Construtor para inicializar os atributos comuns do grafo abstrato.
     *
     * @param numeroDeVertices Número total de vértices no grafo.
     */
    public GrafoAbstrato(int numeroDeVertices) {
        if (numeroDeVertices <= 0) {
            throw new IllegalArgumentException("O número de vértices deve ser maior que zero!");
        }

        this.numeroDeVertices = numeroDeVertices;
        this.pesosVertices = new int[numeroDeVertices];
        this.rotulosVertices = new String[numeroDeVertices];
    }

    /**
     * Define o peso de um vértice.
     *
     * @param vertice Índice do vértice.
     * @param peso    Peso a ser atribuído ao vértice.
     */
    public void setPesoVertice(int vertice, int peso) {
        validarVertice(vertice);
        pesosVertices[vertice] = peso;
    }

    /**
     * Obtém o peso de um vértice.
     *
     * @param vertice Índice do vértice.
     * @return Peso do vértice.
     */
    public int getPesoVertice(int vertice) {
        validarVertice(vertice);
        return pesosVertices[vertice];
    }

    /**
     * Define o rótulo de um vértice.
     *
     * @param vertice Índice do vértice.
     * @param rotulo  Rótulo a ser atribuído ao vértice.
     */
    public void setRotuloVertice(int vertice, String rotulo) {
        validarVertice(vertice);
        rotulosVertices[vertice] = rotulo;
    }

    /**
     * Obtém o rótulo de um vértice.
     *
     * @param vertice Índice do vértice.
     * @return Rótulo do vértice.
     */
    public String getRotuloVertice(int vertice) {
        validarVertice(vertice);
        return rotulosVertices[vertice];
    }

    /**
     * Valida se um índice de vértice é válido no grafo.
     *
     * @param vertice Índice do vértice.
     */
    private void validarVertice(int vertice) {
        if (vertice < 0 || vertice >= numeroDeVertices) {
            throw new IllegalArgumentException("Vértice inválido! Deve estar entre 0 e " + (numeroDeVertices - 1));
        }
    }

    /**
     * Retorna o número total de vértices no grafo.
     *
     * @return Número de vértices.
     */
    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    /**
     * Adiciona uma aresta entre dois vértices com um peso.
     *
     * @param origem  Vértice de origem.
     * @param destino Vértice de destino.
     * @param peso    Peso da aresta.
     * @return true se a aresta foi adicionada com sucesso, false caso contrário.
     */
    @Override
    public abstract boolean adicionarAresta(int origem, int destino, int peso);

    /**
     * Remove uma aresta entre dois vértices.
     *
     * @param origem  Vértice de origem.
     * @param destino Vértice de destino.
     * @param peso    Peso da aresta a ser removida.
     * @return true se a aresta foi removida com sucesso, false caso contrário.
     */
    public abstract boolean removerAresta(int origem, int destino, int peso);

    /**
     * Remove uma aresta entre dois vértices sem especificar peso.
     *
     * @param origem  Vértice de origem.
     * @param destino Vértice de destino.
     * @return true se a aresta foi removida com sucesso, false caso contrário.
     */
    @Override
    public abstract boolean removerAresta(int origem, int destino);

    /**
     * Retorna uma lista de vértices vizinhos de um vértice dado.
     *
     * @param vertice Vértice de referência.
     * @return Lista de vértices vizinhos.
     */
    public abstract List<Integer> getVizinhos(int vertice);

    /**
     * Retorna uma lista de sucessores de um vértice dado.
     *
     * @param vertice Vértice de referência.
     * @return Lista de sucessores.
     */
    public abstract List<Integer> getSucessores(int vertice);

    /**
     * Verifica se dois vértices são adjacentes.
     *
     * @param origem  Vértice de origem.
     * @param destino Vértice de destino.
     * @return true se os vértices são adjacentes, false caso contrário.
     */
    public abstract boolean saoAdjacentes(int origem, int destino);
}
