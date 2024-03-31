# Algoritmos em Grafos

Este é um projeto desenvolvido em Java para manipulação de grafos. Nele está contida a representação de Grafos utilizando Matriz de Adjacência e Lista de Adjacência. 
Com esta aplicação você poderá criar um grafo de X vértices, adicionar ou remover arestas, verificar vizinhança de vértices, grau de um vértice e também realizar testes 
de grafo para determinar se ele é simples, regular, completo e bipartido.

## Como executar o programa?

Para executar o programa você pode escolher a IDE Java que preferir (Visual Studio Code, Eclipse, IntelliJ IDEA e etc), abrir a pasta do projeto (graph_algorithms) e executar
o projeto.

## Como funciona o programa?

Quando estiver em execução, primeiramente ele irá te pedir para definir o número de vértices do grafo e o direcionamento dele. Caso seja direcionado digite true e para não direcionado 
digite false. 

### Menu

Após definir o número de vértices e o direcionamento do grafo, irá aparecer um menu com 8 opções, dentre elas adicionar aresta, remover aresta, vizinhança de um vértice, sucessores e
predecessores, grau de um vértice, informações do grafo (Simples, regular, completo e bipartido), imprimir grafo e sair.

#### 1. Adicionar aresta

Para adicionar uma aresta você deverá definir o número (int) do vértice de origem e destino dessa aresta. Caso algum vértice escolhido seja inválido, ele irá mostrar uma mensagem para você verificar 
os vértices digitados. Caso contrário mostrará uma mensagem de Aresta adicionada!

#### 2. Remover aresta

Assim como a opção anterior, você deverpa definir o número do vértice de origem e destino e caso algum vértice digitado seja inválido ele te alertará para você verificar os vértices escolhidos.

#### 3. Vizinhança de um vértice (Grafo NÃO direcionado)

Essa opção só serve para grafos NÃO direcionados ! 
Ao escolher essa opção ele pedirá para você digitar o número do vértice e te mostrará a vizinhança daquele vértice, tanto pela função
da matriz de adjacência como pela função da lista de adjacência.

#### 4. Sucessores e predecessores de um vértice (Grafo Direcionado)

Essa opção só funciona para grafos direcionados !
Ao escolher essa opção ele pedirá para você digitar o número do vértice e te mostrará os sucessores e predecessores daquele vértice, tanto pela função
da matriz de adjacência como pela função da lista de adjacência.

#### 5. Grau de um vértice

Ao escolher essa opção ele pedirá para você digitar o número do vértice e te mostrará o grau daquele vértice, tanto pela função
da matriz de adjacência como pela função da lista de adjacência.

#### 6. Informações do Grafo

Escolhendo essa opção ele irá realizar os testes do grafo e te responderá se ele é simples, regular completo e bipartido.

#### 7. Imprimir grafo

Ao escolher essa opção, será impressa a matriz de adjacência do grafo e também a lista de adjacência.

#### 8. Sair

Para para de executar o programa basta escolher a opção de sair e ele irá finalizar a execução do programa.
