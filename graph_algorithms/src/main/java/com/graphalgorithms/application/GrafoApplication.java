package com.graphalgorithms.application;

import com.graphalgorithms.implementation.GrafoMatrizAdjacencia;
import com.graphalgorithms.implementation.GrafoListaAdjacencia;

import java.util.Scanner;

public class GrafoApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("##------------------------- REPRESENTAÇÃO DE GRAFOS -------------------------##");
        System.out.println("|                                                                            |");
        System.out.println("| Representação de grafos utilizando Matriz de Adjacência e Lista de Adjacência. |");
        System.out.println("|                                                                            |");
        System.out.println("##---------------------------------------------------------------------------##\n");

        System.out.print("--> Digite o número de vértices do grafo: ");
        int numeroDeVertices = scanner.nextInt();
        System.out.print("--> O grafo é direcionado? (true/false): ");
        boolean isDirecionado = scanner.nextBoolean();
        System.out.print("--> O grafo é ponderado? (true/false): ");
        boolean isPonderado = scanner.nextBoolean();

        GrafoMatrizAdjacencia grafoMatriz = new GrafoMatrizAdjacencia(numeroDeVertices, isDirecionado);
        GrafoListaAdjacencia grafoLista = new GrafoListaAdjacencia(numeroDeVertices, isDirecionado);

        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            System.out.print("--> Digite o número de uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarAresta(scanner, grafoMatriz, grafoLista, isPonderado);
                case 2 -> removerAresta(scanner, grafoMatriz, grafoLista, isPonderado);
                case 3 -> imprimirGrafos(grafoMatriz, grafoLista);
                case 4 -> verificarTodasPropriedades(grafoMatriz, grafoLista);
                case 5 -> verificarSimples(grafoMatriz, grafoLista);
                case 6 -> verificarRegular(grafoMatriz, grafoLista);
                case 7 -> verificarCompleto(grafoMatriz, grafoLista);
                case 8 -> verificarBipartido(grafoMatriz, grafoLista);
                case 9 -> buscarVertices(scanner, grafoMatriz, grafoLista, isDirecionado);
                case 10 -> verificarConectividade(grafoMatriz, grafoLista);
                case 11 -> buscaLargura(scanner, grafoMatriz, grafoLista);
                case 12 -> buscaProfundidade(scanner, grafoMatriz, grafoLista);
                case 13 -> executarOrdenacaoTopologica(grafoMatriz, grafoLista, isDirecionado);
                case 14 -> encontrarCiclos(grafoMatriz, grafoLista);
                case 15 -> encontrarCaminhoDijkstra(scanner, grafoMatriz, grafoLista);
                case 16 -> encontrarArvoreGeradoraPrim(scanner, grafoMatriz, grafoLista);
                case 17 -> encontrarArvoreGeradoraKruskal(grafoMatriz, grafoLista);
                case 18 -> verificarEuleriano(grafoMatriz, grafoLista);
                case 0 -> {
                    System.out.println("Saindo...");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n##----------------------------- MENU -----------------------------##");
        System.out.println("| 1. Adicionar aresta                                              |");
        System.out.println("| 2. Remover aresta                                                |");
        System.out.println("| 3. Imprimir grafos                                               |");
        System.out.println("| 4. Verificar todas as propriedades                               |");
        System.out.println("| 5. Verificar se é simples                                        |");
        System.out.println("| 6. Verificar se é regular                                        |");
        System.out.println("| 7. Verificar se é completo                                       |");
        System.out.println("| 8. Verificar se é bipartido                                      |");
        System.out.println("| 9. Buscar vértices (Sucessores, Predecessores, Grau)             |");
        System.out.println("| 10. Verificar conectividade do grafo                             |");
        System.out.println("| 11. Executar busca em largura                                    |");
        System.out.println("| 12. Executar busca em profundidade                               |");
        System.out.println("| 13. Executar ordenação topológica                                |");
        System.out.println("| 14. Encontrar ciclos no grafo                                    |");
        System.out.println("| 15. Encontrar menor caminho (Dijkstra)                           |");
        System.out.println("| 16. Encontrar árvore geradora mínima (Prim)                      |");
        System.out.println("| 17. Encontrar árvore geradora mínima (Kruskal)                   |");
        System.out.println("| 18. Verificar se o grafo é Euleriano                             |");
        System.out.println("| 0. Sair                                                          |");
        System.out.println("##----------------------------------------------------------------##");
    }

    private static void adicionarAresta(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista, boolean isPonderado) {
        System.out.println("| 1. Adicionar aresta                                              |\n");
        System.out.print("--> Digite o vértice de origem da aresta: ");
        int origem = scanner.nextInt();
        System.out.print("--> Digite o vértice de destino da aresta: ");
        int destino = scanner.nextInt();

        if (!isPonderado) {
            if (grafoMatriz.adicionarAresta(origem, destino) && grafoLista.adicionarAresta(origem, destino)) {
                System.out.println("\nAresta adicionada com sucesso!\n");
            } else {
                System.out.println("\n[ERRO] Não foi possível adicionar a aresta. Verifique os vértices!\n");
            }
        } else {
            System.out.print("--> Digite o peso da aresta: ");
            int peso = scanner.nextInt();

            if (grafoMatriz.adicionarAresta(origem, destino, peso) && grafoLista.adicionarAresta(origem, destino, peso)) {
                System.out.println("\nAresta ponderada adicionada com sucesso!\n");
            } else {
                System.out.println("\n[ERRO] Não foi possível adicionar a aresta ponderada. Verifique os vértices e o peso!\n");
            }
        }
    }

    private static void removerAresta(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista, boolean isPonderado) {
        System.out.println("| 2. Remover aresta                                                |\n");
        System.out.print("--> Digite o vértice de origem da aresta: ");
        int origem = scanner.nextInt();
        System.out.print("--> Digite o vértice de destino da aresta: ");
        int destino = scanner.nextInt();

        if (!isPonderado) {
            if (grafoMatriz.removerAresta(origem, destino) && grafoLista.removerAresta(origem, destino)) {
                System.out.println("\nAresta removida com sucesso!\n");
            } else {
                System.out.println("\n[ERRO] Não foi possível remover a aresta. Verifique os vértices!\n");
            }
        } else {
            System.out.print("--> Digite o peso da aresta que deseja remover: ");
            int peso = scanner.nextInt();

            if (grafoMatriz.removerAresta(origem, destino, peso) && grafoLista.removerAresta(origem, destino, peso)) {
                System.out.println("\nAresta ponderada removida com sucesso!\n");
            } else {
                System.out.println("\n[ERRO] Não foi possível remover a aresta ponderada. Verifique os vértices e o peso!\n");
            }
        }
    }

    private static void imprimirGrafos(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("| 3. Imprimir grafos                                               |\n");
        System.out.println("(Matriz de Adjacência):");
        grafoMatriz.imprimeGrafo();

        System.out.println("(Lista de Adjacência):");
        grafoLista.imprimeGrafo();
    }

    private static void verificarTodasPropriedades(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("Simples? " + (grafoMatriz.isSimples() && grafoLista.isSimples()));
        System.out.println("Regular? " + (grafoMatriz.isRegular() && grafoLista.isRegular()));
        System.out.println("Completo? " + (grafoMatriz.isCompleto() && grafoLista.isCompleto()));
        System.out.println("Bipartido? " + (grafoMatriz.isBipartido() && grafoLista.isBipartido()));
    }

    private static void verificarSimples(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("Simples? " + (grafoMatriz.isSimples() && grafoLista.isSimples()));
    }

    private static void verificarRegular(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("Regular? " + (grafoMatriz.isRegular() && grafoLista.isRegular()));
    }

    private static void verificarCompleto(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("Completo? " + (grafoMatriz.isCompleto() && grafoLista.isCompleto()));
    }

    private static void verificarBipartido(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("Bipartido? " + (grafoMatriz.isBipartido() && grafoLista.isBipartido()));
    }

    private static void buscarVertices(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista, boolean isDirecionado) {
        System.out.print("--> Digite o vértice: ");
        int vertice = scanner.nextInt();
        System.out.println("Sucessores: " + grafoLista.getSucessores(vertice));
        if (isDirecionado) {
            System.out.println("Predecessores: " + grafoLista.getPredecessores(vertice));
        }
        System.out.println("Grau: " + grafoLista.getGrau(vertice)[0]);
    }

    private static void verificarConectividade(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("O grafo é conexo? " + (grafoMatriz.isConexo() && grafoLista.isConexo()));
    }

    private static void buscaLargura(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.print("--> Digite o vértice inicial: ");
        int verticeInicial = scanner.nextInt();
        System.out.println("(Matriz de Adjacência) Busca em Largura:");
        grafoMatriz.buscaLargura(verticeInicial);
        System.out.println("(Lista de Adjacência) Busca em Largura:");
        grafoLista.buscaLargura(verticeInicial);
    }

    private static void buscaProfundidade(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.print("--> Digite o vértice inicial: ");
        int verticeInicial = scanner.nextInt();
        System.out.println("(Matriz de Adjacência) Busca em Profundidade:");
        grafoMatriz.buscaProfundidade(verticeInicial);
        System.out.println("(Lista de Adjacência) Busca em Profundidade:");
        grafoLista.buscaProfundidade(verticeInicial);
    }

    private static void executarOrdenacaoTopologica(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista, boolean isDirecionado) {
        if (!isDirecionado) {
            System.out.println("Ordenação Topológica só é válida para grafos direcionados!");
            return;
        }
        System.out.println("(Matriz de Adjacência) Ordenação Topológica: " + grafoMatriz.ordenacaoTopologica());
        System.out.println("(Lista de Adjacência) Ordenação Topológica: " + grafoLista.ordenacaoTopologica());
    }

    private static void encontrarCiclos(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("(Matriz de Adjacência) Ciclos encontrados: " + grafoMatriz.getCiclos());
        System.out.println("(Lista de Adjacência) Ciclos encontrados: " + grafoLista.getCiclos());
    }

    private static void encontrarCaminhoDijkstra(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.print("--> Digite o vértice de origem: ");
        int origem = scanner.nextInt();
        System.out.print("--> Digite o vértice de destino: ");
        int destino = scanner.nextInt();
        System.out.println("(Matriz de Adjacência) Caminho mínimo:");
        grafoMatriz.dijkstra(origem, destino);
        System.out.println("(Lista de Adjacência) Caminho mínimo:");
        grafoLista.dijkstra(origem, destino);
    }

    private static void encontrarArvoreGeradoraPrim(Scanner scanner, GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.print("--> Digite o vértice inicial: ");
        int verticeInicial = scanner.nextInt();
        System.out.println("(Matriz de Adjacência) Árvore Geradora Mínima (Prim):");
        grafoMatriz.prim(verticeInicial);
        System.out.println("(Lista de Adjacência) Árvore Geradora Mínima (Prim):");
        grafoLista.prim(verticeInicial);
    }

    private static void encontrarArvoreGeradoraKruskal(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("(Matriz de Adjacência) Árvore Geradora Mínima (Kruskal):");
        grafoMatriz.kruskal();
        System.out.println("(Lista de Adjacência) Árvore Geradora Mínima (Kruskal):");
        grafoLista.kruskal();
    }

    private static void verificarEuleriano(GrafoMatrizAdjacencia grafoMatriz, GrafoListaAdjacencia grafoLista) {
        System.out.println("O grafo é Euleriano? (Matriz de Adjacência): " + grafoMatriz.isEuleriano());
        System.out.println("O grafo é Euleriano? (Lista de Adjacência): " + grafoLista.isEuleriano());
    }
}
