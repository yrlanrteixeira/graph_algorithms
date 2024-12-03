package com.graphalgorithms.tests;

import com.graphalgorithms.implementation.GrafoMatrizAdjacencia;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoMatrizAdjacenciaTest {

    private void printSection(String title) {
        System.out.println("\n====================== " + title + " ======================\n");
    }

    @Test
    void testCriacaoGrafo() {
        printSection("TESTE: Criação do Grafo");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(5, false);
        System.out.println("-> Grafo criado com 5 vértices (não direcionado).");
        assertEquals(5, grafo.getNumeroDeVertices());
        System.out.println("✔ Teste de criação do grafo concluído com sucesso!");
    }

    @Test
    void testAdicionarAresta() {
        printSection("TESTE: Adicionar Arestas");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        System.out.println("-> Adicionando aresta entre 0 e 1 com peso 2...");
        assertTrue(grafo.adicionarAresta(0, 1, 2));
        System.out.println("✔ Aresta adicionada com sucesso.");

        System.out.println("-> Adicionando aresta entre 1 e 2 com peso 3...");
        assertTrue(grafo.adicionarAresta(1, 2, 3));
        System.out.println("✔ Aresta adicionada com sucesso.");

        System.out.println("-> Tentando adicionar aresta fora dos limites...");
        assertFalse(grafo.adicionarAresta(3, 4, 1));
        System.out.println("✔ Aresta fora dos limites detectada corretamente.");
    }

    @Test
    void testRemoverAresta() {
        printSection("TESTE: Remover Arestas");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        System.out.println("-> Removendo aresta entre 0 e 1 com peso 2...");
        assertTrue(grafo.removerAresta(0, 1, 2));
        System.out.println("✔ Aresta removida com sucesso.");

        System.out.println("-> Tentando remover novamente a mesma aresta...");
        assertFalse(grafo.removerAresta(0, 1, 2));
        System.out.println("✔ Aresta não encontrada, operação inválida.");
    }

    @Test
    void testImprimeGrafo() {
        printSection("TESTE: Imprimir Grafo");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        System.out.println("-> Estrutura do grafo:");
        grafo.imprimeGrafo();
        System.out.println("✔ Teste de impressão do grafo concluído.");
    }

    @Test
    void testGetVizinhos() {
        printSection("TESTE: Obter Vizinhos");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);

        System.out.println("-> Obtendo vizinhos do vértice 0...");
        List<Integer> vizinhos = grafo.getVizinhos(0);
        System.out.println("Vizinhos encontrados: " + vizinhos);
        assertEquals(List.of(1), vizinhos);
    }

    @Test
    void testOrdenacaoTopologica() {
        printSection("TESTE: Ordenação Topológica");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(4, true);
        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        System.out.println("-> Calculando ordenação topológica...");
        List<Integer> ordenacao = grafo.ordenacaoTopologica();
        System.out.println("Ordenação topológica: " + ordenacao);
        assertEquals(List.of(0, 1, 2, 3), ordenacao);
    }

    @Test
    void testBuscaLargura() {
        printSection("TESTE: Busca em Largura");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        System.out.println("-> Executando busca em largura a partir do vértice 0...");
        grafo.buscaLargura(0);
        System.out.println("✔ Teste de busca em largura concluído.");
    }

    @Test
    void testBuscaProfundidade() {
        printSection("TESTE: Busca em Profundidade");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(1, 2, 3);

        System.out.println("-> Executando busca em profundidade a partir do vértice 0...");
        grafo.buscaProfundidade(0);
        System.out.println("✔ Teste de busca em profundidade concluído.");
    }

    @Test
    void testPonderacaoRotulacaoVertices() {
        printSection("TESTE: Ponderação e Rotulação de Vértices");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(3, false);
        grafo.setPesoVertice(0, 10);
        grafo.setRotuloVertice(0, "Início");

        System.out.println("-> Vértice 0 - Peso: " + grafo.getPesoVertice(0) + ", Rótulo: " + grafo.getRotuloVertice(0));
        assertEquals(10, grafo.getPesoVertice(0));
        assertEquals("Início", grafo.getRotuloVertice(0));

        grafo.setPesoVertice(2, 20);
        grafo.setRotuloVertice(2, "Fim");

        System.out.println("-> Vértice 2 - Peso: " + grafo.getPesoVertice(2) + ", Rótulo: " + grafo.getRotuloVertice(2));
        assertEquals(20, grafo.getPesoVertice(2));
        assertEquals("Fim", grafo.getRotuloVertice(2));
    }

    @Test
    void testFloydWarshall() {
        printSection("TESTE: Floyd-Warshall");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(4, true);
        grafo.adicionarAresta(0, 1, 3);
        grafo.adicionarAresta(0, 2, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 2);

        System.out.println("-> Calculando menores distâncias com Floyd-Warshall...");
        int[][] distancias = grafo.floydWarshall();
        for (int i = 0; i < distancias.length; i++) {
            System.out.println("Distâncias a partir do vértice " + i + ": " + java.util.Arrays.toString(distancias[i]));
        }

        assertEquals(0, distancias[0][0]);
        assertEquals(3, distancias[0][1]);
        assertEquals(1, distancias[0][2]);
        assertEquals(3, distancias[0][3]);
    }


    @Test
    void testDijkstraTodos() {
        printSection("TESTE: Dijkstra Todos");
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(5, true);
        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(0, 2, 4);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(1, 3, 7);
        grafo.adicionarAresta(2, 4, 3);

        int[] distancias = grafo.dijkstraTodos(0);

        System.out.println("-> Calculando menores distâncias com Dijkstra...");
        assertArrayEquals(new int[]{0, 2, 3, 9, 6}, distancias);
        System.out.println("Distâncias a partir do vértice 0: " + java.util.Arrays.toString(distancias));
        System.out.println("✔ Teste de Dijkstra Todos concluído.");
    }

}
