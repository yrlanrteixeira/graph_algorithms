# Algoritmos em Grafos

Este é um projeto desenvolvido em Java para manipulação de grafos. Nele está contida a representação de Grafos utilizando Matriz de Adjacência e Lista de Adjacência. 
Com esta aplicação você poderá criar um grafo de X vértices, adicionar ou remover arestas, verificar vizinhança de vértices, grau de um vértice e também realizar testes 
de grafo para determinar se ele é simples, regular, completo e bipartido.

## Como executar o programa?

Para executar o programa você pode escolher a IDE Java que preferir (Visual Studio Code, Eclipse, IntelliJ IDEA e etc), abrir a pasta do projeto (graph_algorithms) e executar
o projeto (Execute o arquivo que está no diretório graph_algorithms/src/main/java/com/graphalgorithms/application/GrafoApplication.java).

## Como funciona o programa?

1. Ao iniciar, o programa solicitará:
- O número de vértices do grafo.
- Se o grafo é **direcionado** (true/false).
- Se o grafo é **ponderado** (true/false).

### Menu

Após definir o número de vértices e o direcionamento do grafo, irá aparecer um menu com 15 opções, dentre elas adicionar aresta, remover aresta, vizinhança de um vértice, sucessores e
predecessores, grau de um vértice, informações do grafo (Simples, regular, completo e bipartido), imprimir grafo, sair e entre outras.


## Funcionalidades do Menu

1. **Adicionar Aresta:**
- Adicione uma aresta entre dois vértices. Insira o vértice de origem, o vértice de destino e, se for ponderado, o peso da aresta.

2. **Remover Aresta:**
- Remova uma aresta entre dois vértices. Insira o vértice de origem, o vértice de destino e, se for ponderado, o peso da aresta.

3. **Imprimir Grafo:**
- Exibe o grafo em suas representações de matriz de adjacência e lista de adjacência.

4. **Verificar Todas as Propriedades:**
- Verifica se o grafo é simples, regular, completo e bipartido.

5. **Verificar se é Simples:**
- Confirma se o grafo não possui laços ou arestas múltiplas.

6. **Verificar se é Regular:**
- Verifica se todos os vértices possuem o mesmo grau.

7. **Verificar se é Completo:**
- Determina se todos os vértices estão conectados diretamente entre si.

8. **Verificar se é Bipartido:**
- Verifica se o grafo é bipartido.

9. **Buscar Vértices (Sucessores, Predecessores, Grau):**
- Exibe os sucessores, predecessores e o grau de um vértice especificado.

10. **Verificar Conectividade:**
 - Determina se o grafo é conexo.

11. **Executar Busca em Largura:**
 - Realiza a busca em largura a partir de um vértice inicial.

12. **Executar Busca em Profundidade:**
 - Realiza a busca em profundidade a partir de um vértice inicial.

13. **Executar Ordenação Topológica:**
 - Realiza a ordenação topológica do grafo, se aplicável.

14. **Encontrar Ciclos no Grafo:**
 - Identifica e exibe os ciclos existentes no grafo.

15. **Encontrar Caminho Mínimo (Dijkstra):**
 - Calcula o caminho mínimo entre dois vértices usando o algoritmo de Dijkstra.

16. **Árvore Geradora Mínima (Prim):**
 - Encontra a árvore geradora mínima usando o algoritmo de Prim.

17. **Árvore Geradora Mínima (Kruskal):**
 - Encontra a árvore geradora mínima usando o algoritmo de Kruskal.

18. **Verificar se é Euleriano:**
 - Determina se o grafo é Euleriano.

19. **Definir ou Consultar Peso de Vértices:**
 - Define ou consulta o peso de um vértice.

20. **Executar Floyd-Warshall:**
 - Calcula as distâncias mínimas entre todos os pares de vértices.

21. **Executar Dijkstra para Todos os Vértices (Matriz de Adjacência):**
 - Calcula os caminhos mínimos de um vértice inicial para todos os outros vértices, usando matriz de adjacência.

22. **Executar Dijkstra para Todos os Vértices (Lista de Adjacência):**
 - Calcula os caminhos mínimos de um vértice inicial para todos os outros vértices, usando lista de adjacência.

23. **Sair:**
 - Encerra a execução do programa.
