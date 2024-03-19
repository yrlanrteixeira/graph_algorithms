package main.java.com.graphalgorithms.interfaces;
import java.util.ArrayList;
import java.util.List;

public interface Grafo {
    void adicionarVertice();
    boolean adicionarAresta(int origem, int destino);
    boolean removerAresta(int origem, int destino);
    int getNumeroDeVertices();
    boolean temAresta(int origem, int destino);
    List<Integer> getVizinhos(int vertice);
    List<Integer> getSucessores(int vertice);
    List<Integer> getPredecessores(int vertice);
    int getGrau(int vertice);



}