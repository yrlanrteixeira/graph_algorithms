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

                    if(!isPonderado){
                        if (grafo.removerAresta(origem, destino) && grafoLista.removerAresta(origem, destino)) {
                            System.out.println("\nAresta removida!\n");
                        } else if (!grafo.removerAresta(origem, destino) && !grafoLista.removerAresta(origem, destino)) {
                            System.out.println(
                                    "NÃO foi possível remover a aresta, verifique os vértices de origem e destino!");
                        }
                    } else {
                        System.out.print("--> Digite o peso da aresta que deseja remover: ");
                        int peso = scanner.nextInt();

                        if(grafo.removerAresta(origem, destino, peso) && grafoLista.removerAresta(origem, destino, peso)){
                            System.out.println("\nAresta removida!\n");
                        } else if (!grafo.removerAresta(origem, destino) && !grafoLista.removerAresta(origem, destino)) {
                            System.out.println(
                                    "NÃO foi possível remover a aresta, verifique os vértices de origem, destino e peso!");
                        }
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
                    int[] grausLista = grafoLista.getGrau(vertice);
                    int[] grauMatriz = grafo.getGrau(vertice);

                    if(!isDirecionado){
                        System.out.println("(Lista de Adjacência) Grau do vértice " + vertice + ": " + grausLista[0]);
                        System.out.println("(Matriz de Adjecência) Grau do vértice " + vertice + ": " + grauMatriz[0]);
                    } else {
                        System.out.println("(Lista de Adjacência) Grau de Saída: " + grausLista[1] + "; Grau de Entrada: " + grausLista[0] + "; Grau do Vértice: " + (grausLista[0] + grausLista[1]));
                        System.out.println("(Matriz de Adjacência) Grau de Saída: " + grauMatriz[1] + "; Grau de Entrada: " + grauMatriz[0] + "; Grau do Vértice: " + (grauMatriz[0] + grauMatriz[1]));
                    }

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
                    System.out.print("--> Digite o vértice inicial: ");
                    int verticeInicial = scanner.nextInt();

                    if (verticeInicial < 0 || verticeInicial >= grafoLista.getNumeroDeVertices()) {
                        System.out.println("[ERROR]: O vértice digitado não existe!");
                    } else {
                        grafo.buscaLargura(verticeInicial);
                        System.out.println();
                        grafoLista.buscaLargura(verticeInicial);
                    }
                    break;

                // BUSCA POR PROFUNDIDADE
                case 9:
                    System.out.println("| 9. Busca por Profundidade                                        |");
                    System.out.print("--> Digite o vértice inicial: ");
                    verticeInicial = scanner.nextInt();

                    if (verticeInicial < 0 || verticeInicial >= grafoLista.getNumeroDeVertices()) {
                        System.out.println("[ERROR]: O vértice digitado não existe!");
                    } else {
                        grafo.buscaProfundidade(verticeInicial);
                        System.out.println();
                        grafoLista.buscaProfundidade(verticeInicial);
                    }

                    break;

                // TESTE DE GRAFO CONEXO
                case 10:
                    System.out.println("| 10. Teste grafo conexo                                           |");
                    if(grafo.isConexo() && grafoLista.isConexo()){
                        System.out.println("O grafo é conexo !");
                    } else {
                        System.out.println("O grafo NÃO é conexo !");
                    }

                    break;

                // ORDENAÇÃO TOPOLÓGICA
                case 11:
                    System.out.println("| 11. Ordenação Topológica                                         |");
                    System.out.print("(Matriz de Adjacência) Ordenação Topológica: ");
                    List<Integer> ordenacao = grafo.ordenacaoTopologica();
                    for(int v : ordenacao){
                        System.out.print(v + " ");
                    }

                    System.out.println(" ");

                    System.out.print("(Lista de Adjacência) Ordenação Topológica: ");
                    List<Integer> ordenacaoLista = grafoLista.ordenacaoTopologica();
                    for(int v : ordenacaoLista){
                        System.out.print(v + " ");
                    }
                    break;

                // ÁRVORE GERADORA MINIMA (PRIM)
                case 12:
                    System.out.println("| 12. Árvore Geradora Mínima (Prim)                                |");
                    System.out.print("--> Digite o vértice inicial: ");
                    verticeInicial = scanner.nextInt();
                    System.out.println("(Lista de Adjacência): ");
                    grafoLista.prim(verticeInicial);
                    System.out.println("Matriz de Adjacência: ");
                    grafo.prim(verticeInicial);
                    break;

                // ÁRVORE GERADORA MINIMA (KRUSKAL)
                case 13:
                    System.out.println("| 13. Árvore Geradora Mínima (Kruskal)                             |");
                    System.out.println("Matriz de Adjacência: ");
                    grafo.kruskal();
                    System.out.println("\nLista de Adjacência: ");
                    grafoLista.kruskal();
                    break;

                // CAMINHO MÍNIMO ENTRE DOIS VÉRTICES (DIJKSTRA)
                case 14:
                    System.out.println("| 14. Caminho mínimo entre dois vértices (Dijkstra)                |");
                    System.out.print("--> Digite o vértice de origem para Dijkstra: ");
                    int origemDijkstra = scanner.nextInt();
                    System.out.print("--> Digite o vértice de destino para Dijkstra: ");
                    int destinoDijkstra = scanner.nextInt();

                    System.out.println("Matriz de Adjacência: ");
                    grafo.dijkstra(origemDijkstra, destinoDijkstra);

                    System.out.println("\nLista de Adjacência: ");
                    grafoLista.dijkstra(origemDijkstra, destinoDijkstra);
                    break;

                // SAIR
                case 15:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}