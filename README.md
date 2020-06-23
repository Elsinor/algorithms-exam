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

#### Algoritmo `Ex1`
- legge il file in input
- costruisce il grafo orientato (`City`)
- costruisce l'albero dei cammini minimi con Dijkstra (versione con coda a priorità) effettuando una routine di rilassamento specifica per il quesito
- costruisce il cammino a ritroso con l'ausilio dell'array `streetTo`
- stampa il cammino
- stampa i secondi necessari per raggiungere l'incrocio T partendo dall'incrocio S

#### input
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

#### Esercizio 2.

#### Esecuzione
E' possibile eseguire direttamente il file jar compilato
```
  java -jar main.jar fabrizio.armango.Ex2 [<nome_file>]
```
oppure ricompilare il jar, e infine eseguirlo.
```
  python3 prova_pratica.py Ex2 [<nome_file>]
```

#### Algoritmo `Ex2`


#### input

#### Descrizione

##### Note
-----

#### Esercizio 3.

#### Esecuzione
E' possibile eseguire direttamente il file jar compilato
```
  java -jar main.jar fabrizio.armango.Ex3 [<nome_file>]
```
oppure ricompilare il jar, e infine eseguirlo.
```
  python3 prova_pratica.py Ex3 [<nome_file>]
```

#### Algoritmo `Ex3`


#### input

#### Descrizione

##### Note
-----

#### Esercizio 4.

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


#### input

#### Descrizione

##### Note
