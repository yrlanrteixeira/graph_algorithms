package com.graphalgorithms.tests;

import com.graphalgorithms.implementation.GrafoMatrizAdjacencia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GrafoMatrizAdjacenciaTest {

    @Test
    void testAdicionarAresta() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        assertTrue(grafo.adicionarAresta(0, 1, 2));
        assertTrue(grafo.adicionarAresta(1, 2, 3));
        assertFalse(grafo.adicionarAresta(3, 4, 1)); // Fora dos limites
    }

    @Test
    void testRemoverAresta() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        assertTrue(grafo.removerAresta(0, 1, 2));
        assertFalse(grafo.removerAresta(0, 1, 2)); // Já foi removida
    }

    @Test
    void testImprimeGrafo() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        grafo.imprimeGrafo();
    }

    @Test
    void testGetVizinhos() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);

        List<Integer> vizinhos = grafo.getVizinhos(0);
        assertEquals(List.of(1), vizinhos);

        vizinhos = grafo.getVizinhos(1);
        assertEquals(List.of(0), vizinhos);
    }

    @Test
    void testOrdenacaoTopologica() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(4, true);
        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        List<Integer> ordenacao = grafo.ordenacaoTopologica();
        assertEquals(List.of(0, 1, 2, 3), ordenacao);
    }

    @Test
    void testBuscaLargura() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        grafo.buscaLargura(0);
    }

    @Test
    void testBuscaProfundidade() {
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        grafo.buscaProfundidade(0);
    }
}
