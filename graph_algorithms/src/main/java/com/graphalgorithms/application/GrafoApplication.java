package main.java.com.graphalgorithms.application;

import main.java.com.graphalgorithms.implementation.GrafoListaAdjacencia;
import main.java.com.graphalgorithms.implementation.GrafoMatrizAdjacencia;

import java.util.Scanner;

public class GrafoApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número de vértices do grafo: ");
        int numeroDeVertices = scanner.nextInt();
        System.out.println("O grafo é direcionado? (true/false): ");
        boolean isDirecionado = scanner.nextBoolean();
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(numeroDeVertices, isDirecionado);
        GrafoListaAdjacencia grafoLista = new GrafoListaAdjacencia(numeroDeVertices, isDirecionado);

        boolean continuar = true;
        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar aresta");
            System.out.println("2. Obter vizinhos de um vértice");
            System.out.println("3. Obter sucessores de um vértice");
            System.out.println("4. Obter predecessores de um vértice");
            System.out.println("5. Obter grau de um vértice");
            System.out.println("6. Teste de grafo simples");
            System.out.println("7. Teste de grafo regular");
            System.out.println("8. Teste de grafo completo");
            System.out.println("9. Teste de grafo bipartido");
            System.out.println("10. Imprimir grafo");
            System.out.println("11. Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a origem da aresta: ");
                    int origem = scanner.nextInt();
                    System.out.println("Digite o destino da aresta: ");
                    int destino = scanner.nextInt();
                    grafo.adicionarAresta(origem, destino);
                    grafoLista.adicionarAresta(origem, destino);
                    break;
                case 2:
                    System.out.println("Digite o vértice para obter os vizinhos: ");
                    int vertice = scanner.nextInt();
                    System.out.println("Vizinhos do vértice " + vertice + ": " + grafo.getVizinhos(vertice));
                    System.out.println("(Lista Adjacência) Vizinhos do vértice " + vertice + ": "
                            + grafoLista.getVizinhos(vertice));
                    break;
                case 3:
                    System.out.println("Digite o vértice para obter os sucessores: ");
                    vertice = scanner.nextInt();
                    System.out.println("Sucessores do vértice " + vertice + ": " + grafo.getSucessores(vertice));
                    System.out.println("(Lista Adjacência) Sucessores do vértice " + vertice + ": "
                            + grafoLista.getSucessores(vertice));
                    break;
                case 4:
                    System.out.println("Digite o vértice para obter os predecessores: ");
                    vertice = scanner.nextInt();
                    System.out.println("Predecessores do vértice " + vertice + ": " + grafo.getPredecessores(vertice));
                    System.out.println("(Lista Adjacência) Predecessores do vértice " + vertice + ": "
                            + grafo.getPredecessores(vertice));
                    break;
                case 5:
                    System.out.println("Digite o vértice para obter o grau: ");
                    vertice = scanner.nextInt();
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

                case 6:
                    if (grafoLista.isSimples()) {
                        System.out.println("O grafo é Simples!");
                    } else {
                        System.out.println("O grafo NÃO é simples!");
                    }
                    break;

                case 7:
                    if (grafoLista.isRegular()) {
                        System.out.println("O grafo é Regular!");
                    } else {
                        System.out.println("O grafo NÃO é Regular!");
                    }
                    break;

                case 8:
                    if (grafoLista.isCompleto()) {
                        System.out.println("O grafo é Completo!");
                    } else {
                        System.out.println("O grafo NÃO é Completo!");
                    }
                    break;

                case 9:
                    if (grafoLista.isBipartido()) {
                        System.out.println("O grafo é Bipartido!");
                    } else {
                        System.out.println("O grafo NÃO é Bipartido!");
                    }
                    break;

                case 10:
                    grafoLista.imprimeGrafo();
                    break;

                case 11:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}