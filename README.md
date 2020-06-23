## Università di Palermo
#### Corso di Laurea in Informatica - Esame di "Laboratorio di Algoritmi" - Prova Pratica - I appello di Giugno 2020

###### Fabrizio Armango (0632440)


#### Esercizio 1.
L'Esercizio 1 viene svolto nella classe `fabrizio.armango.Ex1` e nelle relative dipendenze nel package `fabrizio.armango.ex1`.

La traccia chiede di trovare il cammino più veloce (considerando quindi come pesi degli archi il tempo di percorrenza) da un incrocio S a un incrocio T.

#### Esecuzione
E' possibile eseguire direttamente il file jar compilato
```
  java -jar main.jar fabrizio.armango.Ex1 [<nome_file>]
```
oppure ricompilare il jar, e infine eseguirlo.
```
  python3 prova_pratica.py Ex1 [<nome_file>]
```

### Algoritmo `Ex1`
- legge il file in input
- costruisce il grafo orientato e pesato (`City`)
- costruisce l'albero dei cammini minimi con Dijkstra (versione con coda a priorità) effettuando una routine di rilassamento specifica per il quesito
- costruisce il cammino a ritroso con l'ausilio dell'array `streetTo`
- stampa il cammino
- stampa i secondi necessari per raggiungere l'incrocio T partendo dall'incrocio S

#### Input
L'input è un file di interi con la seguente struttura:

```
N M incrocio_iniziale incrocio_finale
(1) incrocio_di_partenza_1 incrocio_di_arrivo_1 tempo_apertura_1 tempo_chiusura_1 tempo_di_percorrenza_1
(2) incrocio_di_partenza_2 incrocio_di_arrivo_2 tempo_apertura_2 tempo_chiusura_2 tempo_di_percorrenza_2
...
(M) incrocio_di_partenza_m incrocio_di_arrivo_m tempo_apertura_m tempo_chiusura_m tempo_di_percorrenza_m
```


#### Descrizione
Consideriamo come incroci i nodi di un grafo orientato e pesato (`DirectedEdge`).

I seguenti file sono scritti sulla falsa riga di classi presenti nelle librerie di `algs4`:
- `fabrizio.armango.Ex1` -> `edu.princeton.cs.algs4.DijkstraSP`
- `fabrizio.armango.ex1.City` -> `edu.princeton.cs.algs4.EdgeWeightedDigraph`


La classe `fabrizio.armango.ex1.Street` è sottoclasse di `edu.princeton.cs.algs4.DirectedEdge`, in quanto rappresentiamo il tempo di percorrenza di una strada come il peso di un arco orientato.
Inoltre, sono stati aggiunti dei metodi per calcolare l'effettivo tempo necessario, tenendo in considerazione i periodi di chiusura e di apertura di tali strade. Ad esempio:
- `public int neededSecondsAt(int time)`
  - restituisce il `weight` dinamico, dipendente dal tempo trascorso.
  - viene utilizzato `neededSecondsAt` nella funzione di `relax` in sostituzione a `weight`, ed effettua il rilassamento su ogni arco (nell'ordine stabilito dalla coda a priorità) facendo uso dell'array `secondsTo` (di tipo `int[]`) che sostituisce `distTo` del `DijkstraSP` originale.

Viene aggiunta una costante booleana (`Consts.CAN_WAIT_IN_STREET_IF_TRAVEL_STARTED_WHEN_OPEN`) per consentire all'algoritmo di soddisfare due possibili interpretazioni della traccia:
- false (default): se il tempo di apertura della strada non è sufficiente per attraversarla completamente, si attende all'incrocio.
- true: se la strada che si inizia a percorrere (quindi è aperta) viene chiusa, ci si ferma e si continua dal posto in cui ci si è fermati.



##### Note
> Una possibile ottimizzazione (in termini di spazio in memoria) potrebbe essere l'utilizzo del tipo `int` nella classe `Street` per la rappresentazione dei pesi (attualmente `double` come in `edu.princeton.cs.algs4.DirectedEdge`).


-----

### Esercizio 2.
L'Esercizio 2 viene svolto nella classe `fabrizio.armango.Ex2`.
La traccia chiede di trovare, dato un grafo orientato e pesato G con pesi numeri reali strettamente positivi e un valore reale positivo K, la lista delle coppie di vertici il cui cammino minimo ha un costo maggiore di K.

#### Esecuzione
E' possibile eseguire direttamente il file jar compilato
```
  java -jar main.jar fabrizio.armango.Ex2 [<nome_file>]
```
oppure ricompilare il jar, e infine eseguirlo.
```
  python3 prova_pratica.py Ex2 [<nome_file>]
```


```
// Esempio
java -jar main.jar fabrizio.armango.Ex2 input/mediumEWD.txt
// Insert K:
1.4

```

#### Algoritmo `Ex2`
- legge il file in input // O(E)
- inizializza una lista `L` // costo costante
- costruisce il grafo orientato e pesato `G` (`EdgeWeightedDigraph`)  // costo O(E)
- per ogni nodo `s` in `G.V()`: // O(V)
  - costruisce l'albero dei cammini minimi con `DijkstraSP` avente come root(o sorgente) `s` // costo E*log(V)
  - per ogni nodo `v` in `G.V()`: // V
    - se esiste il cammino minimo da `s` a `v`: // costo costante
      - e se tale cammino ha costo strettamente maggiore di `K`: // costo costante
        - aggiungi tale cammino alla lista `L` // costo costante

Non considerando i costi costanti:
```
E+E+ V*(E*log(V) + V)
2E + EV log(V) + V^2

E*V*log(V) + V^2
```


#### Input
Seguendo lo standard di `algs4`:
```
|V|
|E|
v1 w1 c1
v2 w2 c2
....
vn wn cn
```

#### Descrizione
Il metodo statico `public static Stack<Iterable<DirectedEdge>> getShortestPathsWithCostGreaterThan(EdgeWeightedDigraph G, double K)` esegue l'algoritmo di DijkstraSP per ogni nodo dell'arco e restituisce uno Stack di cammini con costo maggiore al K specificato in input.

-----

### Esercizio 3.
Questo esercizio esegue dei test sull'algoritmo dell'esercizio precedente (`Ex2`).
Generando randomicamente dei grafi con un numero di vertici specificato dal quesito, eseguende l'esercizio 2 ed disegna un grafico.

Le ascisse sono il numero di nodi.
Le ordinate sono il numero degli archi il cui cammino minimo ha un costo maggiore di K.


#### Esecuzione
E' possibile eseguire direttamente il file jar compilato
```
  java -jar main.jar fabrizio.armango.Ex3
```
oppure ricompilare il jar, e infine eseguirlo.
```
  python3 prova_pratica.py Ex3
```


#### Algoritmo `Ex3`

- inizializza un array per le ascisse
- inizializza un array per le ordinate
- per ogni grafo appartente alla famiglia
```
int V = (n + 1) * 25;
int E = 2 * V * (int) Math.log(V);
```
`per n = {0, 1, 2, ...n - 1}`

  - calcola K come peso medio degli archi del grafo corrente
  - calcola il numero richiesto degli archi il cui cammino minimo ha un costo maggiore di K (numShortestPaths).
  - aggiungi (V,numShortestPaths) agli array.

- disegna il grafico.


#### Input

#### Descrizione
La classe `CustomGraph` è sottoclasse di `EdgeWeightedDigraph`, genera archi con pesi random e fornisce un metodo
`averageWeight` per trovare il valore K.

-----

### Esercizio 4.
Questo esercizio viene svolto nella classe `fabrizio.armango.Ex4`

#### Esecuzione
E' possibile eseguire direttamente il file jar compilato
```
  java -jar main.jar fabrizio.armango.Ex4 [<nome_file>]
```
oppure ricompilare il jar, e infine eseguirlo.
```
  python3 prova_pratica.py Ex4 [<nome_file>]
```

#### Algoritmo `Ex4`


#### Input

#### Descrizione

##### Note
