package main.java.com.graphalgorithms.implementation;

import main.java.com.graphalgorithms.abstracts.GrafoAbstrato;
import main.java.com.graphalgorithms.utils.Aresta;

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
            // Verifica se há uma aresta entre origem e destino
            for (Aresta aresta : listaAdjacencia[origem]) {
                if (aresta.getDestino() == destino && aresta.getPeso() == peso) {
                    listaAdjacencia[origem].remove(aresta);
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
            return true;
        }
        return false;
    }

    public int getPesoAresta(int origem, int destino) {
        for (Aresta aresta : listaAdjacencia[origem]) {
            if (aresta.destino == destino) {
                return aresta.peso;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public List<Aresta> getArestas() {
        List<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            for (Aresta aresta : listaAdjacencia[i]) {
                arestas.add(aresta);
            }
        }
        return arestas;
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
        return true;
    }

    @Override
    public void adicionarVertice() {
    }

    @Override
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    public boolean temAresta(int origem, int destino) {
        // Verifica se a origem e o destino são válidos
        if (origem >= 0 && destino >= 0 && origem < numeroDeVertices && destino < numeroDeVertices) {
            // Verifica se existe uma aresta da origem para o destino
            if (listaAdjacencia[origem].contains(destino)) {
                return true;
            }
        }
        // Se não encontrar uma aresta entre origem e destino, retorna false
        return false;
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
        //Array para mostrar os vértices visitados
        boolean[] visitado = new boolean[numeroDeVertices];

        //Fila para armazenar os vértices a serem visitados
        Queue<Integer> fila = new LinkedList<>();

        //Marca o vértice inicial como visitado e o adiciona à fila
        visitado[verticeInicial] = true;
        fila.offer(verticeInicial);

        System.out.print("(Lista de Adjacência) Busca por largura: ");

        while(!fila.isEmpty()){
            //Remove o vértice da frente da fila e o imprime
            int verticeAtual = fila.poll();
            System.out.print(verticeAtual + " ");

            //Itera sobre todos os vizinhos do vértice atual
            for(Aresta aresta : listaAdjacencia[verticeAtual]){
                int vizinho = aresta.getDestino();
                //Verifica se o vizinho não foi visitado
                if(!visitado[vizinho]){
                    //Marca o vizinho como visitado e o adiciona á fila
                    visitado[vizinho] = true;
                    fila.offer(vizinho);
                }

            }
        }
    }

    public void buscaProfundidade(int vertice){
        boolean[] visitado = new boolean[numeroDeVertices];
        System.out.print("(Lista de Adjacência) Busca em Profundidade: ");
        buscaProfundidadeRecursivo(vertice, visitado);
    }

    private void buscaProfundidadeRecursivo(int vertice, boolean[] visitado){
        visitado[vertice] = true;
        System.out.print(vertice + " ");

        for(Aresta aresta : listaAdjacencia[vertice]){
            int vizinho = aresta.getDestino();
            if(!visitado[vizinho]){
                buscaProfundidadeRecursivo(vizinho, visitado);
            }
        }
    }

    public List<Integer> ordenacaoTopologica(){
        List<Integer> ordenacao = new ArrayList<>();
        boolean[] visitado = new boolean[numeroDeVertices];
        Stack<Integer> pilha = new Stack<>();

        for(int i = 0; i < numeroDeVertices; i++){
            if(!visitado[i]){
                ordenacaoTopologicaRecursivo(i, visitado, pilha);
            }
        }

        while(!pilha.isEmpty()){
            ordenacao.add(pilha.pop());
        }

        return ordenacao;
    }

    private void ordenacaoTopologicaRecursivo(int vertice, boolean[] visitado, Stack<Integer> pilha){
        visitado[vertice] = true;

        for(Aresta aresta : listaAdjacencia[vertice]){
            int vizinho = aresta.destino;
            if(!visitado[vizinho]){
                ordenacaoTopologicaRecursivo(vizinho, visitado, pilha);
            }
        }

        pilha.push(vertice);
    }

    public void prim(int verticeInicial) {
        boolean[] visitado = new boolean[numeroDeVertices];
        PriorityQueue<Aresta> filaPrioridade = new PriorityQueue<>((a, b) -> a.peso - b.peso);
        List<Aresta> arvoreGeradoraMinima = new ArrayList<>();
        int pesoTotal = 0;

        visitado[verticeInicial] = true;
        for (Aresta aresta : listaAdjacencia[verticeInicial]) { // Assumindo que listaAdjacencia é uma matriz de adjacência
            filaPrioridade.offer(aresta);
        }

        while (!filaPrioridade.isEmpty()) {
            Aresta arestaAtual = filaPrioridade.poll();
            int verticeAtual = arestaAtual.destino;

            if (!visitado[verticeAtual]) {
                visitado[verticeAtual] = true;
                arvoreGeradoraMinima.add(arestaAtual);
                pesoTotal += arestaAtual.peso;

                for (Aresta aresta : listaAdjacencia[verticeAtual]) { // Assumindo que listaAdjacencia é uma matriz de adjacência
                    if (!visitado[aresta.destino]) {
                        filaPrioridade.offer(aresta);
                    }
                }
            }
        }

        System.out.println("Arestas da árvore geradora mínima:");
        for (Aresta aresta : arvoreGeradoraMinima) {
            System.out.println(aresta.destino + " - " + aresta.peso);
        }
        System.out.println("Peso total da árvore geradora mínima: " + pesoTotal);
    }

//    @Override
//    public List<Integer> ordenacaoTopologica() {
//        Stack<Integer> stack = new Stack<>();
//        boolean[] visited = new boolean[numeroDeVertices];
//
//        for (int i = 0; i < numeroDeVertices; i++) {
//            if (!visited[i]) {
//                topologicalSortUtil(i, visited, stack);
//            }
//        }
//
//        List<Integer> order = new ArrayList<>();
//        while (!stack.isEmpty()) {
//            order.add(stack.pop());
//        }
//        return order;
//    }
//
//    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
//        visited[v] = true;
//
//        for (Aresta aresta : listaAdjacencia[v]) {
//            int vizinho = aresta.destino;
//
//            if (!visited[vizinho]) {
//                topologicalSortUtil(vizinho, visited, stack);
//            }
//        }
//
//        stack.push(v);
//    }

    public List<Aresta> getArestasOrdenadas() {
        List<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < numeroDeVertices; i++) {
            arestas.addAll(listaAdjacencia[i]);
        }
        arestas.sort(Comparator.comparingInt(a -> a.peso));
        return arestas;
    }

}
