package com.graphalgorithms.implementation;

import com.graphalgorithms.abstracts.GrafoAbstrato;
import com.graphalgorithms.utils.Aresta;

import java.util.*;

public class GrafoListaAdjacencia extends GrafoAbstrato {
    private final int numeroDeVertices;
    private final boolean isDirecionado;

    private final LinkedList<Aresta>[] listaAdjacencia;

    /**
     * Construtor do grafo de adjacência
     *
     * @param numeroDeVertices Número de vértices do grafo (tipo int)
     * @param isDirecionado    Grafo direcionado (true), grafo NÃO direcionado
     *                         (false)
     */
    @SuppressWarnings("unchecked")
    public GrafoListaAdjacencia(int numeroDeVertices, boolean isDirecionado) {
        super(numeroDeVertices);
        this.numeroDeVertices = numeroDeVertices;
        this.isDirecionado = isDirecionado;
        this.listaAdjacencia = new LinkedList[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            listaAdjacencia[i] = new LinkedList<>();
        }
    }

    /**
     * Método para adicionar arestas sem peso
     *
     * @param origem  Vértice de origem da aresta
     * @param destino Vértice de destino da aresta
     * @return Retorna true se conseguiu adicionar a aresta e false se falhou
     */
    public boolean adicionarAresta(int origem, int destino) {
        return adicionarAresta(origem, destino, 1);
    }

    @Override
    public boolean adicionarAresta(int origem, int destino, int peso) {
        // Verifica se a aresta é válida
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            listaAdjacencia[origem].add(new Aresta(origem, destino, peso));

            // Se for um grafo não direcionado, adiciona nas duas direções
            if (!isDirecionado)
                listaAdjacencia[destino].add(new Aresta(destino, origem, peso));

            return true;
        }
        return false;
    }

    public boolean removerAresta(int origem, int destino){
        return removerAresta(origem, destino, 1);
    }

    @Override
    public boolean removerAresta(int origem, int destino, int peso) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            boolean arestaRemovida = false;

            // Remove a aresta da origem para o destino
            for (Aresta aresta : listaAdjacencia[origem]) {
                if (aresta.getDestino() == destino && aresta.getPeso() == peso) {
                    listaAdjacencia[origem].remove(aresta);
                    arestaRemovida = true;
                    break;
                }
            }

            // Se o grafo não for direcionado, remove a aresta do destino para a origem
            if (!isDirecionado) {
                for (Aresta aresta : listaAdjacencia[destino]) {
                    if (aresta.getDestino() == origem && aresta.getPeso() == peso) {
                        listaAdjacencia[destino].remove(aresta);
                        break;
                    }
                }
            }

            return arestaRemovida;
        }
        return false;
    }

    @Override
    public List<Integer> getVizinhos(int vertice) {
        List<Integer> vizinhos = new ArrayList<>();
        for (Aresta aresta : listaAdjacencia[vertice]) {
            vizinhos.add(aresta.destino);
        }
        return vizinhos;
    }

    @Override
    public List<Integer> getSucessores(int vertice) {
        List<Integer> successors = new ArrayList<>();
        for (Aresta aresta : listaAdjacencia[vertice]) {
            successors.add(aresta.destino);
        }
        return successors;
    }

    @Override
    public List<Integer> getPredecessores(int vertice) {
        List<Integer> predecessors = new ArrayList<>();

        for (int i = 0; i < numeroDeVertices; i++) {
            if (i != vertice) {
                for (Aresta aresta : listaAdjacencia[i]) {
                    if (aresta.destino == vertice) {
                        predecessors.add(i);
                        break;
                    }
                }
            }
        }

        return predecessors;
    }

    @Override
    public int[] getGrau(int vertice) {
        int grauEntrada = 0;
        int grauSaida = 0;

        for (int i = 0; i < numeroDeVertices; i++) {
            for (Aresta aresta : listaAdjacencia[i]) {
                if (aresta.destino == vertice)
                    grauEntrada++;
            }
        }

        grauSaida = listaAdjacencia[vertice].size();

        return new int[] { grauEntrada, grauSaida};
    }

    public boolean isSimples() {
        // Verificação de laços no grafo
        for (int i = 0; i < numeroDeVertices; i++) {
            for (Aresta aresta : listaAdjacencia[i]) {
                if (aresta.destino == i) {
                    System.out.println("Laço");
                    return false;
                }
            }
        }

        // Verifica se existe arestas paralelas
        for (int i = 0; i < numeroDeVertices; i++) {
            Set<Integer> vizinhos = new HashSet<>();
            for (Aresta aresta : listaAdjacencia[i]) {
                if (vizinhos.contains(aresta.destino)) {
                    System.out.println("aresta multipla");
                    return false;
                } else
                    vizinhos.add(aresta.destino);
            }
        }

        return true;
    }

    public boolean isRegular() {
        int[] grauPrimeiroVertice = getGrau(0);

        for (int i = 1; i < numeroDeVertices; i++) {
            int[] grauVerticeAtual = getGrau(i);
            if (grauVerticeAtual[0] != grauPrimeiroVertice[0]) {
                return false;
            }
        }

        return true;
    }

    public boolean isCompleto() {
        for (int i = 0; i < numeroDeVertices; i++) {
            if (listaAdjacencia[i].size() != numeroDeVertices - 1)
                return false;
        }

        return true;
    }

    public boolean isBipartido() {
        // Array para armazenar cores dos vértices(-1: não colorido, 0: cor 1, 1: cor 2)
        int[] cores = new int[numeroDeVertices];
        Arrays.fill(cores, -1);

        // Fila para realizar busca em largura
        Queue<Integer> fila = new LinkedList<>();

        // Inicia busca a partir do primeiro vértice
        fila.add(0);
        cores[0] = 0;

        // Realiza busca em largura e atribui cores aos vértices
        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            for (Aresta aresta : listaAdjacencia[vertice]) {
                int vizinho = aresta.destino;

                if (cores[vizinho] == -1) {
                    cores[vizinho] = 1 - cores[vertice];
                    fila.add(vizinho);
                } else if (cores[vizinho] == cores[vertice]) {
                    return false;
                }
            }
        }
        System.out.println("Lista de Adjacência: \n");
        return true;
    }

    @Override
    public int[][] floydWarshall() {
        int[][] dist = new int[numeroDeVertices][numeroDeVertices];

        // Inicializa a matriz de distâncias com os pesos das arestas
        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Distância de um vértice para ele mesmo é 0
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // Sem aresta, distância infinita
                }
            }

            // Atualiza distâncias com os pesos das arestas
            for (Aresta aresta : listaAdjacencia[i]) {
                dist[i][aresta.getDestino()] = aresta.getPeso();
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

    public void imprimeGrafo() {
        System.out.println("Lista de Adjacência: \n");
        for (int i = 0; i < numeroDeVertices; i++) {
            System.out.print(i + " --> ");
            for (Aresta aresta : listaAdjacencia[i]) {
                System.out.print("|" + aresta.destino + "(" + aresta.peso + ")");
            }

            System.out.println();
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

            for (Aresta aresta : listaAdjacencia[vertice]) {
                int vizinho = aresta.getDestino();
                if (!visitado[vizinho]) {
                    fila.add(vizinho);
                    visitado[vizinho] = true;
                    nivel[vizinho] = nivel[vertice] + 1;
                    pai[vizinho] = vertice;
                    System.out.println("Aresta pai encontrada: " + vertice + " -> " + vizinho);
                } else {
                    if (pai[vertice] != vizinho) {
                        if (nivel[vertice] == nivel[vizinho]) {
                            System.out.println("Aresta irmão encontrada: " + vertice + " -> " + vizinho);
                        } else if (nivel[vertice] == nivel[vizinho] + 1) {
                            System.out.println("Aresta tio encontrada: " + vertice + " -> " + vizinho);
                        } else if (nivel[vertice] == nivel[vizinho] - 1) {
                            System.out.println("Aresta primo encontrada: " + vertice + " -> " + vizinho);
                        }
                    }
                }
            }
        }
    }



//    public void buscaProfundidade(int vertice){
//        boolean[] visitado = new boolean[numeroDeVertices];
//        System.out.print("(Lista de Adjacência) Busca em Profundidade: ");
//        buscaProfundidadeRecursivo(vertice, visitado);
//    }
//
//    private void buscaProfundidadeRecursivo(int vertice, boolean[] visitado){
//        visitado[vertice] = true;
//        System.out.print(vertice + " ");
//
//        for(Aresta aresta : listaAdjacencia[vertice]){
//            int vizinho = aresta.getDestino();
//            if(!visitado[vizinho]){
//                buscaProfundidadeRecursivo(vizinho, visitado);
//            }
//        }
//    }

    public void buscaProfundidade(int vertice){
        boolean[] visitado = new boolean[numeroDeVertices];
        System.out.print("(Lista de Adjacência) Busca em Profundidade: ");
        buscaProfundidadeRecursivo(vertice, visitado, 0);
    }

    private void buscaProfundidadeRecursivo(int vertice, boolean[] visitado, int nivel){
        visitado[vertice] = true;
        System.out.println("Nível " + nivel + ": " + vertice);

        for(Aresta aresta : listaAdjacencia[vertice]){
            int vizinho = aresta.getDestino();
            if(!visitado[vizinho]){
                buscaProfundidadeRecursivo(vizinho, visitado, nivel + 1);
            } else {
                System.out.println("Aresta de retorno encontrada: " + aresta.getOrigem() + " -> " + aresta.getDestino());
            }
        }
    }




    public List<Integer> ordenacaoTopologica() {
        int[] grauEntrada = new int[numeroDeVertices];
        for (int i = 0; i < numeroDeVertices; i++) {
            for (Aresta aresta : listaAdjacencia[i]) {
                grauEntrada[aresta.getDestino()]++;
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

            for (Aresta aresta : listaAdjacencia[vertice]) {
                int vizinho = aresta.getDestino();
                grauEntrada[vizinho]--;
                if (grauEntrada[vizinho] == 0) {
                    fila.offer(vizinho);
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
        int[] chave = new int[numeroDeVertices];
        int[] pai = new int[numeroDeVertices];
        boolean[] mstSet = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            chave[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        chave[verticeInicial] = 0;
        pai[verticeInicial] = -1;

        for (int count = 0; count < numeroDeVertices - 1; count++) {
            int u = minKey(chave, mstSet);
            mstSet[u] = true;

            for (Aresta aresta : listaAdjacencia[u]) {
                int v = aresta.getDestino();
                if (!mstSet[v] && aresta.getPeso() < chave[v]) {
                    pai[v] = u;
                    chave[v] = aresta.getPeso();
                }
            }
        }

        printMST(pai, chave);
    }

    private int minKey(int[] chave, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numeroDeVertices; v++)
            if (!mstSet[v] && chave[v] < min) {
                min = chave[v];
                min_index = v;
            }

        return min_index;
    }

    private void printMST(int[] pai, int[] chave) {
        System.out.println("Arestas da árvore geradora mínima:");
        int pesoTotal = 0;
        for (int i = 1; i < numeroDeVertices; i++) {
            System.out.println("Aresta adicionada: (" + pai[i] + " --- " + i + ") com peso " + chave[i]);
            pesoTotal += chave[i];
        }
        System.out.println("Peso total da árvore geradora mínima: " + pesoTotal);
    }

    public void kruskal() {
        // Cria uma lista para armazenar todas as arestas
        List<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            arestas.addAll(listaAdjacencia[i]);
        }

        // Ordena as arestas em ordem crescente de peso
        arestas.sort(Comparator.comparingInt(Aresta::getPeso));

        // Cria um conjunto disjunto
        int[] pai = new int[numeroDeVertices];
        for (int i = 0; i < numeroDeVertices; i++) {
            pai[i] = i;
        }

        // Variável para armazenar o peso total da MST
        int pesoTotal = 0;

        // Processa as arestas em ordem crescente de peso
        for (Aresta aresta : arestas) {
            int u = aresta.getOrigem();
            int v = aresta.getDestino();

            // Se u e v não estiverem no mesmo conjunto, adiciona a aresta à MST
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
        int[] prev = new int[numeroDeVertices];
        boolean[] mstSet = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            mstSet[i] = false;
        }

        dist[verticeInicial] = 0;

        for (int count = 0; count < numeroDeVertices - 1; count++) {
            int u = minDistance(dist, mstSet);

            mstSet[u] = true;

            for (Aresta aresta : listaAdjacencia[u]) {
                int v = aresta.getDestino();
                if (!mstSet[v] && aresta.getPeso() + dist[u] < dist[v]) {
                    prev[v] = u;
                    dist[v] = aresta.getPeso() + dist[u];
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
        if (prev[j] != -1)
            printPath(prev, prev[j]);

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
        for (Aresta aresta : listaAdjacencia[v]) {
            if (!visitado[aresta.getDestino()]) {
                dfs(aresta.getDestino(), visitado);
            }
        }
    }


    @Override
    public boolean saoAdjacentes(int origem, int destino) {
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            for (Aresta aresta : listaAdjacencia[origem]) {
                if (aresta.getDestino() == destino) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean temCiclo() {
        boolean[] visitado = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!visitado[i] && temCicloRecursivo(i, visitado, -1)) {
                return true;
            }
        }
        return false;
    }


    private boolean temCicloRecursivo(int vertice, boolean[] visitado, int pai) {
        visitado[vertice] = true;

        for (Aresta aresta : listaAdjacencia[vertice]) {
            int vizinho = aresta.getDestino();

            if (!visitado[vizinho]) {
                if (temCicloRecursivo(vizinho, visitado, vertice)) {
                    return true;
                }
            } else if (vizinho != pai) {
                return true;
            }
        }

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

        for (Aresta aresta : listaAdjacencia[vertice]) {
            getCiclosRecursivo(aresta.getDestino(), visitado, recStack, caminhoAtual, ciclos);
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
            if (!isDirecionado && getGrau(i)[0] % 2 != 0) {
                return false;
            } else if (isDirecionado && getGrau(i)[0] != getGrau(i)[1]) {
                return false;
            }
        }
        return true;
    }

    public int[] dijkstraTodos(int verticeInicial) {
        int[] dist = new int[numeroDeVertices];
        int[] prev = new int[numeroDeVertices];
        boolean[] mstSet = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            mstSet[i] = false;
        }

        dist[verticeInicial] = 0;

        for (int count = 0; count < numeroDeVertices - 1; count++) {
            int u = minDistance(dist, mstSet);

            mstSet[u] = true;

            for (Aresta aresta : listaAdjacencia[u]) {
                int v = aresta.getDestino();
                if (!mstSet[v] && aresta.getPeso() + dist[u] < dist[v]) {
                    prev[v] = u;
                    dist[v] = aresta.getPeso() + dist[u];
                }
            }
        }

        return dist;
    }





}
