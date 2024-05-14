# Algoritmos em Grafos

Este é um projeto desenvolvido em Java para manipulação de grafos. Nele está contida a representação de Grafos utilizando Matriz de Adjacência e Lista de Adjacência. 
Com esta aplicação você poderá criar um grafo de X vértices, adicionar ou remover arestas, verificar vizinhança de vértices, grau de um vértice e também realizar testes 
de grafo para determinar se ele é simples, regular, completo e bipartido.

## Como executar o programa?

Para executar o programa você pode escolher a IDE Java que preferir (Visual Studio Code, Eclipse, IntelliJ IDEA e etc), abrir a pasta do projeto (graph_algorithms) e executar
o projeto (Execute o arquivo que está no diretório graph_algorithms/src/main/java/com/graphalgorithms/application/GrafoApplication.java).

## Como funciona o programa?

Quando estiver em execução, primeiramente ele irá te pedir para definir o número de vértices do grafo e o direcionamento dele. Caso seja direcionado digite true e para não direcionado 
digite false. Caso seja ponderado digite true e caso não seja digite false.

### Menu

Após definir o número de vértices e o direcionamento do grafo, irá aparecer um menu com 15 opções, dentre elas adicionar aresta, remover aresta, vizinhança de um vértice, sucessores e
predecessores, grau de um vértice, informações do grafo (Simples, regular, completo e bipartido), imprimir grafo, sair e entre outras.


1. **Adicionar Aresta:**
    - Esta opção permite adicionar uma aresta ao grafo.
    - Após escolher a opção 1, você será solicitado a digitar o vértice de origem e o vértice de destino da aresta. Se o grafo for ponderado, você também precisará inserir o peso da aresta.


2. **Remover Aresta:**
    - Esta opção permite remover uma aresta do grafo.
    - Após escolher a opção 2, você precisará digitar o vértice de origem e o vértice de destino da aresta que deseja remover. Se o grafo for ponderado, também será solicitado o peso da aresta.


3. **Vizinhança de um Vértice (Grafo Não Direcionado):**
    - Esta opção mostra os vizinhos de um vértice em um grafo não direcionado.
    - Ao escolher a opção 3, digite o número do vértice para o qual deseja obter a vizinhança.


4. **Sucessores e Predecessores de um Vértice (Grafo Direcionado):**
    - Esta opção mostra os sucessores e predecessores de um vértice em um grafo direcionado.
    - Ao selecionar a opção 4, digite o número do vértice para o qual deseja obter os sucessores e predecessores.


5. **Grau de um Vértice:**
    - Esta opção exibe o grau de um vértice.
    - Após escolher a opção 5, digite o número do vértice para o qual deseja obter o grau.


6. **Informações do Grafo (Simples, Regular, Completo e Bipartido):**
    - Esta opção fornece informações sobre o grafo, incluindo se é simples, regular, completo e bipartido.
    - Basta selecionar a opção 6 no menu, e o programa verificará automaticamente essas propriedades do grafo.


7. **Imprimir Grafo (Matriz e Lista de Adjacência):**
    - Esta opção imprime o grafo em sua representação por matriz de adjacência e por lista de adjacência.
    - Basta escolher a opção 7 no menu para visualizar as representações.


8. **Busca por Largura:**
    - Esta opção realiza a busca por largura no grafo a partir de um vértice inicial.
    - Após selecionar a opção 8, insira o vértice inicial da busca.


9. **Busca por Profundidade:**
    - Esta opção realiza a busca por profundidade no grafo a partir de um vértice inicial.
    - Após escolher a opção 9, insira o vértice inicial da busca.


10. **Teste de Grafo Conexo:**
    - Esta opção verifica se o grafo é conexo.
    - Basta selecionar a opção 10 no menu, e o programa informará se o grafo é ou não conexo.


11. **Ordenação Topológica:**
    - Esta opção realiza a ordenação topológica do grafo.
    - Ao escolher a opção 11, o programa exibirá a ordenação topológica do grafo.


12. **Árvore Geradora Mínima (Prim):**
    - Esta opção encontra a árvore geradora mínima do grafo usando o algoritmo de Prim.
    - Após selecionar a opção 12, insira o vértice inicial para o algoritmo de Prim.


13. **Árvore Geradora Mínima (Kruskal):**
    - Esta opção encontra a árvore geradora mínima do grafo usando o algoritmo de Kruskal.
    - Ao escolher a opção 13, o programa exibirá a árvore geradora mínima encontrada.


14. **Caminho Mínimo entre Dois Vértices (Dijkstra):**
    - Esta opção encontra o caminho mínimo entre dois vértices usando o algoritmo de Dijkstra.
    - Após selecionar a opção 14, insira o vértice de origem e o vértice de destino.


15. **Sair:**
    - Esta opção encerra a execução do programa.

