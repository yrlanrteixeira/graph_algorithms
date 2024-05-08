package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.utils.Aresta;

import java.util.*;

public class Kruskal {
    class Subconjunto {
        int pai, rank;
    };

    int vertices, arestas;
    Aresta[] aresta;

    public Kruskal(int v, int e) {
        vertices = v;
        arestas = e;
        aresta = new Aresta[arestas];
        for (int i = 0; i < e; ++i)
            aresta[i] = new Aresta();
    }

    int encontrar(Subconjunto[] subconjuntos, int i) {
        if (subconjuntos[i].pai != i)
            subconjuntos[i].pai = encontrar(subconjuntos, subconjuntos[i].pai);

        return subconjuntos[i].pai;
    }

    void unir(Subconjunto[] subconjuntos, int x, int y) {
        int raizX = encontrar(subconjuntos, x);
        int raizY = encontrar(subconjuntos, y);

        if (subconjuntos[raizX].rank < subconjuntos[raizY].rank)
            subconjuntos[raizX].pai = raizY;
        else if (subconjuntos[raizX].rank > subconjuntos[raizY].rank)
            subconjuntos[raizY].pai = raizX;
        else {
            subconjuntos[raizY].pai = raizX;
            subconjuntos[raizX].rank++;
        }
    }

    public void KruskalMST() {
        Aresta[] resultado = new Aresta[vertices];
        int e = 0;
        int i;
        for (i = 0; i < vertices; ++i)
            resultado[i] = new Aresta();

        Arrays.sort(aresta);

        Subconjunto[] subconjuntos = new Subconjunto[vertices];
        for (i = 0; i < vertices; ++i)
            subconjuntos[i] = new Subconjunto();

        for (int v = 0; v < vertices; ++v) {
            subconjuntos[v].pai = v;
            subconjuntos[v].rank = 0;
        }

        i = 0;

        while (e < vertices - 1) {
            Aresta proximaAresta = aresta[i++];

            int x = encontrar(subconjuntos, proximaAresta.origem);
            int y = encontrar(subconjuntos, proximaAresta.destino);

            if (x != y) {
                resultado[e++] = proximaAresta;
                unir(subconjuntos, x, y);
            }
        }

        System.out.println("Arestas da Árvore Geradora Mínima");
        for (i = 0; i < e; ++i)
            System.out.println(resultado[i].origem + " -- " + resultado[i].destino + " == " + resultado[i].peso);
    }
}
