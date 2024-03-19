package main.java.com.graphalgorithms.application;

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
        System.out.println("Digite o número de arestas do grafo: ");
        int numeroDeArestas = scanner.nextInt();
        for (int i = 0; i < numeroDeArestas; i++) {
            System.out.println("Digite a origem da aresta " + (i + 1) + ": ");
            int origem = scanner.nextInt();
            System.out.println("Digite o destino da aresta " + (i + 1) + ": ");
            int destino = scanner.nextInt();
            grafo.adicionarAresta(origem, destino);
        }
        System.out.println("Digite o vértice para obter os vizinhos: ");
        int vertice = scanner.nextInt();
        System.out.println("Vizinhos do vértice " + vertice + ": " + grafo.getVizinhos(vertice));
        System.out.println("Digite o vértice para obter os sucessores: ");
        vertice = scanner.nextInt();
        System.out.println("Sucessores do vértice " + vertice + ": " + grafo.getSucessores(vertice));
        System.out.println("Digite o vértice para obter os predecessores: ");
        vertice = scanner.nextInt();
        System.out.println("Predecessores do vértice " + vertice + ": " + grafo.getPredecessores(vertice));
        scanner.close();
    }
}