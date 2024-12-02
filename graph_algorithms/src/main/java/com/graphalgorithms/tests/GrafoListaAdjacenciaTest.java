package com.graphalgorithms.tests;

import com.graphalgorithms.implementation.GrafoListaAdjacencia;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoListaAdjacenciaTest {

    @Test
    void testAdicionarAresta() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);
        assertTrue(grafo.adicionarAresta(0, 1, 2));
        assertTrue(grafo.adicionarAresta(1, 2, 3));
        assertFalse(grafo.adicionarAresta(3, 4, 1)); // Fora dos limites
    }

    @Test
    void testRemoverAresta() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        // Verifica se a aresta foi removida com sucesso
        assertTrue(grafo.removerAresta(0, 1, 2));
        // Tenta remover a mesma aresta novamente e espera que retorne falso
        assertFalse(grafo.removerAresta(0, 1, 2));
        // Tenta remover uma aresta inexistente e espera que retorne falso
        assertFalse(grafo.removerAresta(1, 0, 5));
    }

    @Test
    void testImprimeGrafo() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        grafo.imprimeGrafo();
    }

    @Test
    void testGetVizinhos() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);

        List<Integer> vizinhos = grafo.getVizinhos(0);
        assertEquals(List.of(1), vizinhos);

        vizinhos = grafo.getVizinhos(1);
        assertEquals(List.of(0), vizinhos);
    }

    @Test
    void testOrdenacaoTopologica() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, true);
        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        List<Integer> ordenacao = grafo.ordenacaoTopologica();
        assertEquals(List.of(0, 1, 2, 3), ordenacao);
    }

    @Test
    void testBuscaLargura() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        grafo.buscaLargura(0);
    }

    @Test
    void testBuscaProfundidade() {
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        grafo.buscaProfundidade(0);
    }
}
