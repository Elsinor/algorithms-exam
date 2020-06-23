# Università di Palermo
## Corso di Laurea in Informatica
## Esame di "Laboratorio di Algoritmi"
## Prova Pratica - I appello di Giugno 2020

#### Fabrizio Armango (0632440)

##### Esercizio 1.
L'Esercizio 1 viene svolto nella classe `fabrizio.armango.Ex1` e nelle relative dipendenze nel package `fabrizio.armango.ex1`.

La traccia chiede di trovare il cammino più veloce (considerando quindi come pesi degli archi il tempo di percorrenza) da un incrocio S a un incrocio T.

Consideriamo come incroci i nodi di un grafo orientato e pesato.

Non potendo creare una sottoclasse e fare l'override di metodi che risultano essere `private`,
alcuni file sono scritti sulla falsa riga di classi presenti nelle librerie di `algs4`. Segue un elenco con i rispettivi file originali:

- `fabrizio.armango.Ex1` -> `edu.princeton.cs.algs4.DijkstraSP`
- `fabrizio.armango.ex1.City` -> `edu.princeton.cs.algs4.DirectedEdge`


La classe `fabrizio.armango.ex1.Street` è sottoclasse di `edu.princeton.cs.algs4.DirectedEdge`, in quanto rappresentiamo il tempo di percorrenza di una strada come il peso di arco orientato.
Inoltre, sono stati aggiunti dei metodi per calcolare l'effettivo tempo necessario, tenendo in considerazione i periodi di chiusura e di apertura di tali strade. Ad esempio:
- `public int neededSecondsAt(int time)`
  - restituisce il `weight` dinamico, dipendente dal tempo trascorso.

Viene appunto utilizzato `neededSecondsAt` nella funzione di `relax` in sostituzione a `weight`, ed effettua il rilassamento su ogni arco (nell'ordine stabilito dalla coda a priorità) facendo uso dell'array `secondsTo` (di tipo `int[]`) che sostituisce `distTo` del `DijkstraSP` originale.

Viene aggiunta una costante booleana (`Consts.CAN_WAIT_IN_STREET_IF_TRAVEL_STARTED_WHEN_OPEN`) per consentire all'algoritmo di soddisfare due possibili interpretazioni della traccia:
- false (default): se il tempo di apertura della strada non è sufficiente per attraversarla completamente, si attende all'incrocio.
- true: se la strada che si inizia a percorrere (quindi è aperta) viene chiusa, ci si ferma e si continua dal posto in cui ci si è fermati.
# algorithms-exam
