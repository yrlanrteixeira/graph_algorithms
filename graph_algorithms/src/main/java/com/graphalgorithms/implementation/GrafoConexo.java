package main.java.com.graphalgorithms.implementation;

public class GrafoConexo {

    public static boolean testeGrafoConexo(GrafoListaAdjacencia grafo) {
        boolean[] visitado = new boolean[grafo.getNumeroDeVertices()];

        int verticeInicial = 0;

        dfs(grafo, verticeInicial, visitado);

        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(GrafoListaAdjacencia grafo, int vertice, boolean[] visitado) {
        visitado[vertice] = true;

        for (int vizinho : grafo.getVizinhos(vertice)) {
            if (!visitado[vizinho]) {
                dfs(grafo, vizinho, visitado);
            }
        }
    }

}
