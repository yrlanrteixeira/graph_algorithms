package com.graphalgorithms.implementation;

import com.graphalgorithms.abstracts.GrafoAbstrato;
import com.graphalgorithms.utils.Aresta;

import java.util.*;

public class GrafoMatrizAdjacencia extends GrafoAbstrato {
    private final int numeroDeVertices;
    private final boolean isDirecionado;

    private final List<Aresta>[][] matrizAdjacencia;

    public GrafoMatrizAdjacencia(int numeroDeVertices, boolean isDirecionado) {
        super(numeroDeVertices);
        this.numeroDeVertices = numeroDeVertices;
        this.isDirecionado = isDirecionado;
        matrizAdjacencia = new ArrayList[numeroDeVertices][numeroDeVertices];

        // Inicializando cada elemento da matriz com uma lista vazia
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                matrizAdjacencia[i][j] = new ArrayList<>();
            }
        }
    }

    public boolean adicionarAresta(int origem, int destino) {
        return adicionarAresta(origem, destino, 1);
    }

    @Override
    public boolean adicionarAresta(int origem, int destino, int peso) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            matrizAdjacencia[origem][destino].add(new Aresta(origem, destino, peso));
            if (!isDirecionado) {
                matrizAdjacencia[destino][origem].add(new Aresta(destino, origem, peso));
            }
            return true;
        }
        return false;
    }

    public boolean removerAresta(int origem, int destino) {
        return removerAresta(origem, destino, 1);
    }

    @Override
    public boolean removerAresta(int origem, int destino, int peso) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            List<Aresta> arestas = matrizAdjacencia[origem][destino];
            for (Aresta aresta : arestas) {
                if (aresta.getPeso() == peso) {
                    arestas.remove(aresta);
                    if (!isDirecionado) {
                        // Se não for direcionado, remove também a aresta inversa
                        List<Aresta> arestasInversas = matrizAdjacencia[destino][origem];
                        for (Aresta inversa : arestasInversas) {
                            if (inversa.getPeso() == peso) {
                                arestasInversas.remove(inversa);
                                break;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int[][] floydWarshall() {
        int[][] dist = new int[numeroDeVertices][numeroDeVertices];

        // Inicializa a matriz de distâncias com os pesos das arestas
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Distância de um vértice para ele mesmo é 0
                } else if (!matrizAdjacencia[i][j].isEmpty()) {
                    dist[i][j] = matrizAdjacencia[i][j].get(0).getPeso(); // Peso da aresta
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // Sem aresta, distância infinita
                }
            }
        }

        // Aplica o algoritmo de Floyd-Warshall
        for (int k = 0; k < numeroDeVertices; k++) {
            for (int i = 0; i < numeroDeVertices; i++) {
                for (int j = 0; j < numeroDeVertices; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    private List<Integer> getAdjacentes(int vertice, boolean isPredecessor) {
        List<Integer> adjacentes = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if ((isPredecessor ? matrizAdjacencia[i][vertice] : matrizAdjacencia[vertice][i]) != null) {
                adjacentes.add(i);
            }
        }
        return adjacentes;
    }

    @Override
    public List<Integer> getVizinhos(int vertice) {
        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[vertice][i].isEmpty() || !matrizAdjacencia[i][vertice].isEmpty()) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    @Override
    public List<Integer> getSucessores(int vertice) {
        List<Integer> sucessores = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[vertice][i].isEmpty()) {
                sucessores.add(i);
            }
        }
        return sucessores;
    }

    public List<Integer> getPredecessores(int vertice) {
        List<Integer> sucessores = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[i][vertice].isEmpty()) {
                sucessores.add(i);
            }
        }
        return sucessores;
    }

    public int[] getGrau(int vertice) {
        int grauEntrada = 0;
        int grauSaida = 0;

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[i][vertice].isEmpty()) {
                grauEntrada += matrizAdjacencia[i][vertice].size();
            }
            if (!matrizAdjacencia[vertice][i].isEmpty()) {
                grauSaida += matrizAdjacencia[vertice][i].size();
            }
        }
        return new int[]{grauEntrada, grauSaida};
    }

    @Override
    public boolean isSimples() {
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                List<Aresta> arestas = matrizAdjacencia[i][j];
                if (!arestas.isEmpty()) {
                    if (i == j || arestas.size() > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isRegular() {
        int[] grauPrimeiroVertice = getGrau(0);
        int grau = grauPrimeiroVertice[0] + grauPrimeiroVertice[1];

        for (int i = 1; i < numeroDeVertices; i++) {
            int[] grauVerticeAtual = getGrau(i);
            int grauAtual = grauVerticeAtual[0] + grauVerticeAtual[1];
            if (grau != grauAtual) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCompleto() {
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = i + 1; j < numeroDeVertices; j++) {
                if (matrizAdjacencia[i][j].isEmpty() && matrizAdjacencia[j][i].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isBipartido() {
        //Array para armazenar as cores dos vértices (-1: não visitado, 0: cor 1, 1: cor 2)
        int[] cores = new int[numeroDeVertices];
        Arrays.fill(cores, -1);

        // Fila para realizar a busca em largura
        Queue<Integer> fila = new LinkedList<>();

        for (int i = 0; i < numeroDeVertices; i++) {
            if (cores[i] == -1) {
                fila.offer(i);
                cores[i] = 0;

                while (!fila.isEmpty()) {
                    int vertice = fila.poll();

                    for (int j = 0; j < numeroDeVertices; j++) {
                        for (Aresta aresta : matrizAdjacencia[vertice][j]) {
                            int vizinho = aresta.getDestino();

                            if (cores[vizinho] == -1) {
                                fila.offer(vizinho);
                                cores[vizinho] = 1 - cores[vertice];
                            } else if (cores[vizinho] == cores[vertice]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Matriz Grafo Bipartido: \n");
        return true;
    }

    @Override
    public void imprimeGrafo() {
        for (int colunas = -1; colunas < numeroDeVertices; colunas++) {
            if (colunas == -1) {
                System.out.print("   ");
            } else System.out.print("V" + colunas + " ");
        }
        System.out.println(" ");

        for (int i = 0; i < numeroDeVertices; i++) {
            System.out.print("V" + i + "  ");
            for (int j = 0; j < numeroDeVertices; j++) {
                int somaPesos = 0;
                for (Aresta aresta : matrizAdjacencia[i][j]) {
                    somaPesos += aresta.getPeso();
                }
                System.out.print(somaPesos + "  ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    public void buscaLargura(int verticeInicial){
        boolean[] visitado = new boolean[numeroDeVertices];
        int[] nivel = new int[numeroDeVertices];
        int[] pai = new int[numeroDeVertices];
        Queue<Integer> fila = new LinkedList<>();

        for (int i = 0; i < numeroDeVertices; i++) {
            pai[i] = -1;
        }

        fila.add(verticeInicial);
        visitado[verticeInicial] = true;
        nivel[verticeInicial] = 0;

        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            System.out.println("Nível " + nivel[vertice] + ": " + vertice);

            for (int i = 0; i < numeroDeVertices; i++) {
                if (!matrizAdjacencia[vertice][i].isEmpty() && !visitado[i]) {
                    fila.add(i);
                    visitado[i] = true;
                    nivel[i] = nivel[vertice] + 1;
                    pai[i] = vertice;
                    System.out.println("Aresta pai encontrada: " + vertice + " -> " + i);
                } else if (!matrizAdjacencia[vertice][i].isEmpty() && visitado[i] && pai[vertice] != i) {
                    if (nivel[vertice] == nivel[i]) {
                        System.out.println("Aresta irmão encontrada: " + vertice + " -> " + i);
                    } else if (nivel[vertice] == nivel[i] + 1) {
                        System.out.println("Aresta tio encontrada: " + vertice + " -> " + i);
                    } else if (nivel[vertice] == nivel[i] - 1) {
                        System.out.println("Aresta primo encontrada: " + vertice + " -> " + i);
                    }
                }
            }
        }
    }


    public void buscaProfundidade(int vertice){
        boolean[] visitado = new boolean[numeroDeVertices];
        System.out.print("(Matriz de Adjacência) Busca em Profundidade: ");
        buscaProfundidadeRecursivo(vertice, visitado, 0);
    }

    private void buscaProfundidadeRecursivo(int vertice, boolean[] visitado, int nivel){
        visitado[vertice] = true;
        System.out.println("Nível " + nivel + ": " + vertice);

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[vertice][i].isEmpty()) {
                if (!visitado[i]) {
                    buscaProfundidadeRecursivo(i, visitado, nivel + 1);
                } else {
                    System.out.println("Aresta de retorno encontrada: " + vertice + " -> " + i);
                }
            }
        }
    }


    public List<Integer> ordenacaoTopologica() {
        int[] grauEntrada = new int[numeroDeVertices];
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (!matrizAdjacencia[i][j].isEmpty()) {
                    grauEntrada[j]++;
                }
            }
        }

        Queue<Integer> fila = new LinkedList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            if (grauEntrada[i] == 0) {
                fila.offer(i);
            }
        }

        List<Integer> ordenacao = new ArrayList<>();
        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            ordenacao.add(vertice);

            for (int i = 0; i < numeroDeVertices; i++) {
                if (!matrizAdjacencia[vertice][i].isEmpty()) {
                    grauEntrada[i]--;
                    if (grauEntrada[i] == 0) {
                        fila.offer(i);
                    }
                }
            }
        }

        if (ordenacao.size() != numeroDeVertices) {
            //throw new RuntimeException("O grafo contém um ciclo!");
            System.out.println("O grafo contém um ciclo!");
            return null;
        }

        return ordenacao;
    }

    public void prim(int verticeInicial) {
        List<Aresta> mst = new ArrayList<>();
        boolean[] mstSet = new boolean[numeroDeVertices];

        int[] key = new int[numeroDeVertices];
        Arrays.fill(key, Integer.MAX_VALUE);

        key[verticeInicial] = 0;
        int pesoTotal = 0;

        for (int count = 0; count < numeroDeVertices - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < numeroDeVertices; v++) {
                if (!matrizAdjacencia[u][v].isEmpty() && !mstSet[v] && matrizAdjacencia[u][v].get(0).getPeso() < key[v]) {
                    key[v] = matrizAdjacencia[u][v].get(0).getPeso();
                    mst.add(matrizAdjacencia[u][v].get(0));
                    System.out.println("Aresta adicionada: (" + u + " --- " + v + ") com peso " + key[v]);
                    pesoTotal += key[v];
                }
            }
        }

        System.out.println("Peso total da árvore geradora mínima: " + pesoTotal);
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numeroDeVertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    public void kruskal() {
        List<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (!matrizAdjacencia[i][j].isEmpty()) {
                    arestas.addAll(matrizAdjacencia[i][j]);
                }
            }
        }

        //Ordena as arestas em ordem crescente de peso
        arestas.sort(Comparator.comparingInt(Aresta::getPeso));

        int[] pai = new int[numeroDeVertices];
        for (int i = 0; i < numeroDeVertices; i++) {
            pai[i] = i;
        }

        int pesoTotal = 0;

        for (Aresta aresta : arestas) {
            int u = aresta.getOrigem();
            int v = aresta.getDestino();

            if (find(u, pai) != find(v, pai)) {
                union(u, v, pai);
                System.out.println("Aresta adicionada: (" + u + " --- " + v + ") com peso " + aresta.getPeso());
                pesoTotal += aresta.getPeso();
            }
        }

        System.out.println("Peso total da árvore geradora mínima: " + pesoTotal);
    }

    private int find(int x, int[] pai) {
        if (pai[x] == x) {
            return x;
        } else {
            return find(pai[x], pai);
        }
    }

    private void union(int x, int y, int[] pai) {
        int xRoot = find(x, pai);
        int yRoot = find(y, pai);
        pai[xRoot] = yRoot;
    }

    public void dijkstra(int verticeInicial, int verticeFinal) {
        int[] dist = new int[numeroDeVertices];
        boolean[] mstSet = new boolean[numeroDeVertices];
        int[] prev = new int[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            prev[i] = -1;
        }

        dist[verticeInicial] = 0;

        for (int count = 0; count < numeroDeVertices - 1; count++) {
            int u = minDistance(dist, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < numeroDeVertices; v++) {
                if (!mstSet[v] && !matrizAdjacencia[u][v].isEmpty() && dist[u] != Integer.MAX_VALUE && dist[u] + matrizAdjacencia[u][v].get(0).getPeso() < dist[v]) {
                    dist[v] = dist[u] + matrizAdjacencia[u][v].get(0).getPeso();
                    prev[v] = u;
                }
            }
        }

        printPath(prev, verticeFinal);
    }

    private int minDistance(int[] dist, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numeroDeVertices; v++)
            if (!mstSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    private void printPath(int[] prev, int j) {
        if (prev[j] != -1) printPath(prev, prev[j]);

        System.out.print(j + " ");
    }

    public boolean isConexo() {
        boolean[] visitado = new boolean[numeroDeVertices];

        // Faz uma busca em profundidade a partir do primeiro vértice
        dfs(0, visitado);

        // Verifica se todos os vértices foram visitados
        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    private void dfs(int v, boolean[] visitado) {
        // Marca o vértice como visitado
        visitado[v] = true;

        // Visita todos os vértices adjacentes que ainda não foram visitados
        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[v][i].isEmpty() && !visitado[i]) {
                dfs(i, visitado);
            }
        }
    }


    @Override
    public boolean saoAdjacentes(int origem, int destino) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            // Verifica se há pelo menos uma aresta entre origem e destino
            return !matrizAdjacencia[origem][destino].isEmpty();
        }
        return false;
    }

    public boolean temCiclo() {
        boolean[] visitado = new boolean[numeroDeVertices];
        boolean[] recStack = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            if (temCicloRecursivo(i, visitado, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean temCicloRecursivo(int vertice, boolean[] visitado, boolean[] recStack) {
        if (recStack[vertice]) {
            return true;
        }
        if (visitado[vertice]) {
            return false;
        }
        visitado[vertice] = true;
        recStack[vertice] = true;

        // Itera sobre os possíveis destinos do vértice atual na matriz de adjacência
        for (int i = 0; i < numeroDeVertices; i++) {
            // Se houver uma aresta (peso maior que zero) entre `vertice` e `i`
            if (!matrizAdjacencia[vertice][i].isEmpty()) {
                if (temCicloRecursivo(i, visitado, recStack)) {
                    return true;
                }
            }
        }
        recStack[vertice] = false;
        return false;
    }

    public List<List<Integer>> getCiclos() {
        List<List<Integer>> ciclos = new ArrayList<>();
        boolean[] visitado = new boolean[numeroDeVertices];
        boolean[] recStack = new boolean[numeroDeVertices];
        List<Integer> caminhoAtual = new ArrayList<>();

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!visitado[i]) {
                getCiclosRecursivo(i, visitado, recStack, caminhoAtual, ciclos);
            }
        }
        return ciclos;
    }

    private void getCiclosRecursivo(int vertice, boolean[] visitado, boolean[] recStack,
                                    List<Integer> caminhoAtual, List<List<Integer>> ciclos) {
        if (recStack[vertice]) {
            // Ciclo encontrado: adiciona o caminho atual ao resultado
            int cicloInicio = caminhoAtual.indexOf(vertice);
            ciclos.add(new ArrayList<>(caminhoAtual.subList(cicloInicio, caminhoAtual.size())));
            return;
        }

        if (visitado[vertice]) {
            return;
        }

        visitado[vertice] = true;
        recStack[vertice] = true;
        caminhoAtual.add(vertice);

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!matrizAdjacencia[vertice][i].isEmpty()) {
                getCiclosRecursivo(i, visitado, recStack, caminhoAtual, ciclos);
            }
        }

        // Remove o vértice atual da pilha de recursão e do caminho
        recStack[vertice] = false;
        caminhoAtual.remove(caminhoAtual.size() - 1);
    }

    public boolean isEuleriano() {
        if (!isConexo()) {
            return false;
        }

        for (int i = 0; i < numeroDeVertices; i++) {
            int[] grau = getGrau(i);
            if (!isDirecionado && grau[0] % 2 != 0) {
                return false;
            } else if (isDirecionado && grau[0] != grau[1]) {
                return false;
            }
        }
        return true;
    }

    public int[] dijkstraTodos(int verticeInicial) {
        int[] dist = new int[numeroDeVertices];
        boolean[] visitado = new boolean[numeroDeVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[verticeInicial] = 0;

        for (int count = 0; count < numeroDeVertices - 1; count++) {
            int u = minDistance(dist, visitado);
            visitado[u] = true;

            for (int v = 0; v < numeroDeVertices; v++) {
                if (!matrizAdjacencia[u][v].isEmpty() && !visitado[v] && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + matrizAdjacencia[u][v].get(0).getPeso() < dist[v]) {
                    dist[v] = dist[u] + matrizAdjacencia[u][v].get(0).getPeso();
                }
            }
        }
        return dist;
    }

}