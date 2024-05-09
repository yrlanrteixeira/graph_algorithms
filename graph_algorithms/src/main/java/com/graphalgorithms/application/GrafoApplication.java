package main.java.com.graphalgorithms.application;

import main.java.com.graphalgorithms.implementation.*;
import main.java.com.graphalgorithms.utils.Aresta;

import java.util.List;
import java.util.Scanner;

public class GrafoApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("##------------------------- ATIVIDADE #1 -------------------------##");
        System.out.println("|                                                                  |");
        System.out.println("| Essa atividade é uma representação de grafos utilizando matriz   |");
        System.out.println("| de Adjacência e Lista de Adjacência.                             |");
        System.out.println("|                                                                  |");
        System.out.println("##----------------------------------------------------------------##\n");

        System.out.print("--> Digite o número de vértices do grafo: ");
        int numeroDeVertices = scanner.nextInt();
        System.out.print("--> O grafo é direcionado? (true/false): ");
        boolean isDirecionado = scanner.nextBoolean();
        System.out.print("--> O grafo é ponderado? (true/false): ");
        boolean isPonderado = scanner.nextBoolean();

        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(numeroDeVertices, isDirecionado);
        GrafoListaAdjacencia grafoLista = new GrafoListaAdjacencia(numeroDeVertices, isDirecionado);
        int origem, destino;
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n##----------------------------- MENU -----------------------------##");
            System.out.println("|                                                                  |");
            System.out.println("| 1. Adicionar aresta                                              |");
            System.out.println("| 2. Remover aresta                                                |");
            System.out.println("| 3. Vizinhança de um vértice (Grafo NÃO Direcionado)              |");
            System.out.println("| 4. Sucessores e predecessores de um vértice (Grafo Direcionado)  |");
            System.out.println("| 5. Grau de um vértice                                            |");
            System.out.println("| 6. Informações do grafo (Simples, Regular, Completo e Bipartido) |");
            System.out.println("| 7. Imprimir grafo (Matriz e Lista de Adjacência)                 |");
            System.out.println("| 8. Busca por largura                                             |");
            System.out.println("| 9. Busca por Profundidade                                        |");
            System.out.println("| 10. Teste grafo conexo                                           |");
            System.out.println("| 11. Ordenação Topológica                                         |");
            System.out.println("| 12. Árvore Geradora Mínima (Prim)                                |");
            System.out.println("| 13. Árvore Geradora Mínima (Kruskal)                             |");
            System.out.println("| 14. Caminho mínimo entre dois vértices (Dijkstra)                |");
            System.out.println("| 15. Sair                                                         |");
            System.out.println("|                                                                  |");
            System.out.println("##----------------------------------------------------------------##\n");
            System.out.print("--> Digite o número de uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                // ADICIONAR ARESTA
                case 1:
                    System.out.println("| 1. Adicionar aresta                                              |\n");
                    System.out.print("--> Digite o vértice de origem da aresta: ");
                    origem = scanner.nextInt();
                    System.out.print("--> Digite o vértice de destino da aresta: ");
                    destino = scanner.nextInt();

                    if (!isPonderado) {
                        if (grafo.adicionarAresta(origem, destino) && grafoLista.adicionarAresta(origem, destino)) {
                            System.out.println("\nAresta adicionada!\n");
                        } else if (!grafo.adicionarAresta(origem, destino)
                                && !grafoLista.adicionarAresta(origem, destino)) {
                            System.out.println(
                                    "NÃO foi possível adicionar a aresta! Verifique os vértices de origem e destino!\n");
                        }
                    } else {
                        System.out.print("--> Digite o peso da aresta: ");
                        int peso = scanner.nextInt();

                        if (grafo.adicionarAresta(origem, destino, peso)
                                && grafoLista.adicionarAresta(origem, destino, peso)) {
                            System.out.println("\nAresta adicionada!\n");
                        } else if (!grafo.adicionarAresta(origem, destino, peso)
                                && !grafoLista.adicionarAresta(origem, destino, peso)) {
                            System.out.println(
                                    "NÃO foi possível adicionar a aresta! Verifique os vértices de origem e destino!\n");
                        }
                    }

                    break;

                // REMOVER ARESTA
                case 2:
                    System.out.println("| 2. Remover aresta                                                |\n");
                    System.out.print("--> Digite o vértice de origem da aresta: ");
                    origem = scanner.nextInt();
                    System.out.print("--> Digite o vértice de destino da aresta: ");
                    destino = scanner.nextInt();

                    if (grafo.removerAresta(origem, destino) && grafoLista.removerAresta(origem, destino)) {
                        System.out.println("\nAresta removida!\n");
                    } else if (!grafo.removerAresta(origem, destino) && !grafoLista.removerAresta(origem, destino)) {
                        System.out.println(
                                "NÃO foi possível remover a aresta, verifique os vértices de origem e destino!");
                    }

                    break;

                // VIZINHANÇA DO VÉRTICE
                case 3:
                    System.out.println("| 3. Vizinhança de um vértice (Grafo NÃO Direcionado)              |\n");
                    if (!isDirecionado) {
                        System.out.print("--> Digite o número do vértice para obter sua vizinhança: ");
                        int vertice = scanner.nextInt();
                        System.out.println("(Matriz de Adjacência) Vizinhos do vértice " + vertice + ": "
                                + grafo.getVizinhos(vertice));
                        System.out.println("(Lista de Adjacência) Vizinhos do vértice " + vertice + ": "
                                + grafoLista.getVizinhos(vertice));
                    } else {
                        System.out.println("O Grafo é direcionado!");
                    }
                    break;

                // SUCESSORES E PREDECESSORES
                case 4:
                    System.out.println("| 4. Sucessores e predecessores de um vértice (Grafo Direcionado)  |\n");
                    if (isDirecionado) {
                        System.out.print("--> Digite o número do vértice para obter os sucessores e predecessores: ");
                        int vertice = scanner.nextInt();
                        System.out.println("(Matriz de Adjacência) Sucessores do vértice " + vertice + ": "
                                + grafo.getSucessores(vertice));
                        System.out.println("(Lista Adjacência) Sucessores do vértice " + vertice + ": "
                                + grafoLista.getSucessores(vertice));
                        System.out.println("\n");
                        System.out.println("(Matriz de Adjacência) Predecessores do vértice " + vertice + ": "
                                + grafo.getPredecessores(vertice));
                        System.out.println("(Lista Adjacência) Predecessores do vértice " + vertice + ": "
                                + grafoLista.getPredecessores(vertice));
                    } else {
                        System.out.println("O Grafo é NÃO direcionado!");
                    }
                    break;

                // GRAU DE UM VÉRTICE
                case 5:
                    System.out.println("| 5. Grau de um vértice                                            |\n");
                    System.out.print("--> Digite o vértice para obter o grau: ");
                    int vertice = scanner.nextInt();
                    int[] graus = grafo.getGrau(vertice);
                    int[] grausLista = grafoLista.getGrau(vertice);
                    if (graus.length == 1) {
                        System.out.println("Grau do vértice " + vertice + ": " + graus[0]);
                    } else {
                        System.out.println("Grau de entrada do vértice " + vertice + ": " + graus[0]);
                        System.out.println("Grau de saída do vértice " + vertice + ": " + graus[1]);
                    }

                    System.out.println("Grau do vértice " + vertice + ": " + grausLista[0]);
                    break;

                // INFORMAÇÕES DO GRAFO
                case 6:
                    System.out.println("| 6. Informações do grafo (Simples, Regular, Completo e Bipartido) |\n");

                    System.out.print("Simples? ");
                    if (grafoLista.isSimples() && grafo.isSimples())
                        System.out.println(" SIM! ");
                    else if (!grafoLista.isSimples() && !grafo.isSimples())
                        System.out.println(" NÃO! ");

                    System.out.print("Regular? ");
                    if (grafoLista.isRegular() && grafo.isRegular())
                        System.out.println(" SIM! ");
                    else if (!grafoLista.isRegular() && !grafo.isRegular())
                        System.out.println(" NÃO! ");

                    System.out.print("Completo? ");
                    if (grafoLista.isCompleto() && grafo.isCompleto())
                        System.out.println(" SIM! ");
                    else if (!grafoLista.isCompleto() && !grafo.isCompleto())
                        System.out.println(" NÃO! ");

                    System.out.print("Bipartido? ");
                    if (grafoLista.isBipartido() && grafo.isBipartido())
                        System.out.println(" SIM! ");
                    else if (!grafoLista.isBipartido() && !grafo.isBipartido())
                        System.out.println(" NÃO! ");

                    break;

                // IMPRIMIR GRAFO
                case 7:
                    System.out.println("| 7. Imprimir grafo (Matriz e Lista de Adjacência)                 |\n");
                    grafo.imprimeGrafo(); // Matriz de Adjacência
                    grafoLista.imprimeGrafo(); // Lista de Adjacência
                    break;

                // BUSCA POR LARGURA
                case 8:
                    System.out.println("| 8. Busca por largura                                             |");
                    BuscaLargura bfs = new BuscaLargura(grafoLista);
                    bfs.buscaLargura(0);
                    break;

                // BUSCA POR PROFUNDIDADE
                case 9:
                    System.out.println("| 9. Busca por Profundidade                                        |");
                    System.out.print("--> Digite o vértice inicial: ");
                    int verticeInicial = scanner.nextInt();

                    if (verticeInicial < 0 || verticeInicial >= grafoLista.getNumeroDeVertices()) {
                        System.out.println("[ERROR]: O vértice digitado não existe!");
                    } else {
                        BuscaProfundidade.buscaProfundidade(grafoLista, verticeInicial);
                    }

                    break;

                // TESTE DE GRAFO CONEXO
                case 10:
                    System.out.println("| 10. Teste grafo conexo                                           |");

                    if (GrafoConexo.isConexo(grafoLista)) {
                        System.out.println("O grafo é conexo !");
                    } else {
                        System.out.println("O grafo NÃO é conexo !");
                    }

                    break;
                case 11:
                    // IMPLEMENTAÇÃO DA ORDENAÇÃO TOPOLÓGICA
                    OrdenacaoTopologica ordenacaoTopologica = new OrdenacaoTopologica(grafoLista);
                    System.out.println("Ordenação Topológica: " + ordenacaoTopologica.ordenar());
                    break;
                case 12:
                    // IMPLEMENTAÇÃO DA ÁRVORE GERADORA MÍNIMA (PRIM)
                    Prim prim = new Prim(grafo);
                    List<Aresta> mstPrim = prim.prim();
                    System.out.println("Árvore Geradora Mínima (Prim):");
                    for (Aresta aresta : mstPrim) {
                        System.out.println(aresta.origem + " -- " + aresta.destino + " == " + aresta.peso);
                    }
                    break;
                case 13:
                    // IMPLEMENTAÇÃO DA ÁRVORE GERADORA MÍNIMA (KRUSKAL)
                    List<Aresta> mstKruskal = Kruskal.kruskal(grafoLista);
                    System.out.println("Árvore Geradora Mínima (Kruskal):");
                    for (Aresta aresta : mstKruskal) {
                        System.out.println(aresta.origem + " -- " + aresta.destino + " == " + aresta.peso);
                    }
                    break;
                case 14:
                    System.out.println("Digite o vértice de origem para Dijkstra:");
                    int origemDijkstra = scanner.nextInt();
                    System.out.println("Digite o vértice de destino para Dijkstra:");
                    int destinoDijkstra = scanner.nextInt();

                    Dijkstra dijkstra = new Dijkstra(grafo);
                    int resultadoDijkstra = dijkstra.dijkstra(origemDijkstra, destinoDijkstra);
                    if (resultadoDijkstra == -1) {
                        System.out.println("Não há caminho disponível entre os vértices " + origemDijkstra + " e " + destinoDijkstra);
                    } else {
                        System.out.println("A distância mínima entre " + origemDijkstra + " e " + destinoDijkstra + " é " + resultadoDijkstra);
                    }
                    break;
                case 15:
                    // SAIR
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}