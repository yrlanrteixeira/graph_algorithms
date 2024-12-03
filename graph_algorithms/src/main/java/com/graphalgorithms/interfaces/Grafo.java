package com.graphalgorithms.interfaces;

import java.util.List;

public interface Grafo {

    /**
     * Método para adicionar aresta ao grafo
     * 
     * @param origem  Vértice de origem da aresta
     * @param destino Vértice de destino da aresta
     * @param peso    Peso da aresta
     * @return Retorna true se adicionou com sucesso e false se falhou
     */
    boolean adicionarAresta(int origem, int destino, int peso);

    /**
     * Método para remover aresta do grafo
     * 
     * @param origem  Vértice de origem da aresta
     * @param destino Vértice de destino da aresta
     * @return Retorna true se conseguiu remover e false se falhou
     */
    boolean removerAresta(int origem, int destino);

    /**
     * Método para pegar o número de vértices do grafo
     * 
     * @return Retorna o número de vértices do grafo
     */
    int getNumeroDeVertices();

    /**
     * Método para obter todos os vizinhos de um vértice
     * 
     * @param vertice Vertice que deseja obter os vizinhos
     * @return Retorna uma lista com os vizinhos do vértice
     */
    List<Integer> getVizinhos(int vertice);

    /**
     * Método para obter todos os sucessores de um vértice
     * 
     * @param vertice Vértice que deseja obter os sucessores
     * @return Retorna uma lista com os sucessores do vértice
     */
    List<Integer> getSucessores(int vertice);

    /**
     * Método para obter todos os Predecessores de um vértice
     * 
     * @param vertice Vértice que deseja obter os predecessores
     * @return Retorna uma lista com os predecessores do vértice
     */
    List<Integer> getPredecessores(int vertice);

    /**
     * Método para obter o grau de um vértice
     * 
     * @param vertice Vértice que deseja obter o grau
     * @return Retorna o grau do vértice
     */
    int[] getGrau(int vertice);

    /**
     * Método para verificar se o grafo é simples
     * 
     * @return Retorna true se for simples e false se não for.
     */
    boolean isSimples();

    /**
     * Método para verificar se o grafo é regular
     * 
     * @return Retorna true se for regular e false se não for
     */
    boolean isRegular();

    /**
     * Método para verificar se o grafo é completo
     * 
     * @return retorna true se for completo e false se não for
     */
    boolean isCompleto();

    /**
     * Método para verificar se o grafo é bipartido
     * 
     * @return retorna true se for bipartido e false se não for
     */
    boolean isBipartido();

    /**
     * Método para imprimir o grafo
     */
    void imprimeGrafo();

    /**
     * Método para realizar ordenação topológica de um grafo
     * 
     * @return Retorna uma lista ordenada
     */
    List<Integer> ordenacaoTopologica();

}