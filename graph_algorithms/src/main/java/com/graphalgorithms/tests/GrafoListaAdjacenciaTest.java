package com.graphalgorithms.tests;

import com.graphalgorithms.implementation.GrafoListaAdjacencia;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoListaAdjacenciaTest {

    private void printSection(String title) {
        System.out.println("\n====================== " + title + " ======================\n");
    }

    @Test
    void testCriacaoGrafo() {
        printSection("TESTE: Criação do Grafo");
        assertThrows(IllegalArgumentException.class, () -> new GrafoListaAdjacencia(0, false),
                "Deveria lançar exceção para número de vértices inválido!");

        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(5, false);
        System.out.println("-> Grafo criado com 5 vértices (não direcionado).");
        assertEquals(5, grafo.getNumeroDeVertices());
        System.out.println("✔ Teste de criação do grafo concluído com sucesso!");
    }

    @Test
    void testAdicionarRemoverArestas() {
        printSection("TESTE: Adicionar e Remover Arestas");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);

        System.out.println("-> Adicionando aresta entre 0 e 1 com peso 2...");
        assertTrue(grafo.adicionarAresta(0, 1, 2));
        System.out.println("✔ Aresta adicionada com sucesso.");

        System.out.println("-> Adicionando aresta entre 1 e 2 com peso 3...");
        assertTrue(grafo.adicionarAresta(1, 2, 3));
        System.out.println("✔ Aresta adicionada com sucesso.");

        System.out.println("-> Tentando adicionar aresta fora dos limites...");
        assertFalse(grafo.adicionarAresta(3, 4, 1));
        System.out.println("✔ Operação inválida detectada corretamente.");

        System.out.println("-> Removendo aresta entre 0 e 1...");
        assertTrue(grafo.removerAresta(0, 1, 2));
        System.out.println("✔ Aresta removida com sucesso.");

        System.out.println("-> Tentando remover uma aresta inexistente...");
        assertFalse(grafo.removerAresta(0, 1, 2));
        System.out.println("✔ Operação inválida detectada corretamente.");
    }

    @Test
    void testBuscaLarguraProfundidade() {
        printSection("TESTE: Busca em Largura e Profundidade");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, false);

        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        System.out.println("-> Executando busca em largura...");
        grafo.buscaLargura(0);

        System.out.println("-> Executando busca em profundidade...");
        grafo.buscaProfundidade(0);
        System.out.println("✔ Testes de busca concluídos.");
    }

    @Test
    void testPonderacaoRotulacaoVertices() {
        printSection("TESTE: Ponderação e Rotulação de Vértices");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(3, false);

        System.out.println("-> Configurando peso e rótulo para vértice 0...");
        grafo.setPesoVertice(0, 10);
        grafo.setRotuloVertice(0, "Origem");
        assertEquals(10, grafo.getPesoVertice(0));
        assertEquals("Origem", grafo.getRotuloVertice(0));

        System.out.println("-> Configurando peso e rótulo para vértice 2...");
        grafo.setPesoVertice(2, 20);
        grafo.setRotuloVertice(2, "Destino");
        assertEquals(20, grafo.getPesoVertice(2));
        assertEquals("Destino", grafo.getRotuloVertice(2));

        System.out.println("✔ Teste de ponderação e rotulação de vértices concluído.");
    }

    @Test
    void testPropriedadesGrafo() {
        printSection("TESTE: Propriedades do Grafo");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, false);

        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        System.out.println("-> Testando se o grafo é conexo...");
        assertTrue(grafo.isConexo());
        System.out.println("✔ Grafo identificado como conexo.");

        System.out.println("-> Testando se o grafo contém ciclos...");
        assertFalse(grafo.temCiclo());
        System.out.println("✔ Grafo identificado como acíclico.");

        System.out.println("-> Testando se o grafo é regular...");
        assertFalse(grafo.isRegular());
        System.out.println("✔ Regularidade verificada corretamente.");
    }

    @Test
    void testFloydWarshall() {
        printSection("TESTE: Floyd-Warshall");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, true);

        grafo.adicionarAresta(0, 1, 3);
        grafo.adicionarAresta(0, 2, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 2);

        int[][] distancias = grafo.floydWarshall();

        System.out.println("-> Distâncias calculadas:");
        for (int[] linha : distancias) {
            System.out.println(java.util.Arrays.toString(linha));
        }

        assertEquals(0, distancias[0][0]);
        assertEquals(3, distancias[0][1]);
        assertEquals(1, distancias[0][2]);
        assertEquals(3, distancias[0][3]);
    }

    @Test
    void testDijkstraTodos() {
        printSection("TESTE: Dijkstra Todos");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(5, true);

        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(0, 2, 4);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(1, 3, 7);
        grafo.adicionarAresta(2, 4, 3);

        int[] distancias = grafo.dijkstraTodos(0);

        System.out.println("-> Distâncias calculadas: " + java.util.Arrays.toString(distancias));
        assertArrayEquals(new int[]{0, 2, 3, 9, 6}, distancias);
    }

    @Test
    void testOrdenacaoTopologica() {
        printSection("TESTE: Ordenação Topológica");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, true);

        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        List<Integer> ordenacao = grafo.ordenacaoTopologica();

        System.out.println("-> Ordenação topológica: " + ordenacao);
        assertEquals(List.of(0, 1, 2, 3), ordenacao);

        System.out.println("-> Adicionando um ciclo...");
        grafo.adicionarAresta(3, 1, 1);

        System.out.println("-> Tentando novamente...");
        assertNull(grafo.ordenacaoTopologica());
    }

    @Test
    void testEuleriano() {
        printSection("TESTE: Grafo Euleriano");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, false);

        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);
        grafo.adicionarAresta(3, 0, 1);

        System.out.println("-> Testando se o grafo é Euleriano...");
        assertTrue(grafo.isEuleriano());
        System.out.println("✔ Grafo identificado como Euleriano.");

        System.out.println("-> Removendo uma aresta...");
        grafo.removerAresta(0, 1);

        System.out.println("-> Testando novamente...");
        assertFalse(grafo.isEuleriano());
    }

    @Test
    void testBipartido() {
        printSection("TESTE: Grafo Bipartido");
        GrafoListaAdjacencia grafo = new GrafoListaAdjacencia(4, false);

        grafo.adicionarAresta(0, 1, 1);
        grafo.adicionarAresta(0, 3, 1);
        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 3, 1);

        assertTrue(grafo.isBipartido());

        System.out.println("✔ Grafo identificado como bipartido.");
    }
}
