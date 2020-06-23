package fabrizio.armango;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import fabrizio.armango.ex1.City;
import fabrizio.armango.ex1.Street;

/*
 State partecipando ad una insolita corsa automobilistica
*/
public class Ex1 {

  private int[] secondsTo; // distTo[v] = distance  of shortest s->v path
  private Street[] streetTo; // edgeTo[v] = last edge on shortest s->v path
  private IndexMinPQ<Integer> pq; // priority queue of vertices

  private City city;
  private int finalCrossroad;

  /**
   * Computes a shortest-paths tree from the source vertex {@code s} to every other vertex in the
   * edge-weighted digraph {@code G}.
   *
   * @param city the edge-weighted digraph
   * @param startCrossroad the source vertex
   * @param finalCrossroad the destination vertex
   * @throws IllegalArgumentException if an edge weight is negative
   * @throws IllegalArgumentException unless {@code 0 <= s < V}
   */
  public Ex1(In in) {

    System.out.println("Prova Pratica I appello Giugno 2020 :: Ex1");

    // Parsing input file
    // number of vertices
    int N = in.readInt();

    // number of edges
    int M = in.readInt();

    // source vertex
    int S = in.readInt() - 1;

    // destination vertex
    int T = in.readInt() - 1;

    // Aliasing input
    int numberOfCrossroads = N;
    int numberOfStreets = M;
    this.city = new City(numberOfCrossroads);

    int startCrossroad = S;
    int streetParsed = 0;
    while (!in.isEmpty() && streetParsed++ < numberOfStreets) {
      int v = in.readInt() - 1;
      int w = in.readInt() - 1;
      int A = in.readInt();
      int B = in.readInt();
      int t = in.readInt();
      this.city.addStreet(v, w, A, B, t);
    }
    this.finalCrossroad = T;

    for (Street e : city.edges()) {
      if (e.weight() < 0) throw new IllegalArgumentException("edge " + e + " has negative weight");
    }

    secondsTo = new int[city.V()];
    streetTo = new Street[city.V()];
    validateVertex(startCrossroad);

    for (int v = 0; v < city.V(); v++) secondsTo[v] = Integer.MAX_VALUE;
    secondsTo[startCrossroad] = 0;

    // relax vertices in order of distance from s
    pq = new IndexMinPQ<Integer>(city.V());
    pq.insert(startCrossroad, secondsTo[startCrossroad]);
    while (!pq.isEmpty()) {
      int v = pq.delMin();
      for (Street e : city.adj(v)) relax(e);
    }

    // check optimality conditions
    assert check(city, startCrossroad);
  }

  // relax edge e and update pq if changed
  private void relax(Street s) {
    int v = s.from(), w = s.to(), elapsedSeconds = secondsTo[v];
    if (secondsTo[w] > elapsedSeconds + s.neededSecondsAt(elapsedSeconds)) {
      secondsTo[w] = secondsTo[v] + s.neededSecondsAt(elapsedSeconds);
      streetTo[w] = s;
      if (pq.contains(w)) pq.decreaseKey(w, secondsTo[w]);
      else pq.insert(w, secondsTo[w]);
    }
  }

  /**
   * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
   *
   * @param v the destination vertex
   * @return {@code true} if there is a path from the source vertex {@code s} to vertex {@code v};
   *     {@code false} otherwise
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public boolean hasPathTo(int v) {
    validateVertex(v);
    return secondsTo[v] < Integer.MAX_VALUE;
  }

  /**
   * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
   *
   * @param v the destination vertex
   * @return a shortest path from the source vertex {@code s} to vertex {@code v} as an iterable of
   *     edges, and {@code null} if no such path
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public Iterable<Street> pathTo(int v) {
    validateVertex(v);
    if (!hasPathTo(v)) return null;
    Stack<Street> path = new Stack<Street>();
    for (Street e = streetTo[v]; e != null; e = streetTo[e.from()]) {
      path.push(e);
    }
    return path;
  }

  // check optimality conditions:
  // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
  // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
  private boolean check(City G, int s) {

    // check that edge weights are nonnegative
    for (Street e : G.edges()) {
      if (e.weight() < 0) {
        System.err.println("negative edge weight detected");
        return false;
      }
    }

    // check that distTo[v] and edgeTo[v] are consistent
    if (secondsTo[s] != 0 || streetTo[s] != null) {
      System.err.println("secondsTo[s] and streetTo[s] inconsistent");
      return false;
    }
    for (int v = 0; v < G.V(); v++) {
      if (v == s) continue;
      if (streetTo[v] == null && secondsTo[v] != Integer.MAX_VALUE) {
        System.err.println("secondsTo[] and streetTo[] inconsistent");
        return false;
      }
    }

    // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
    for (int v = 0; v < G.V(); v++) {
      for (Street e : G.adj(v)) {
        int w = e.to();
        if (secondsTo[v] + e.weight() < secondsTo[w]) {
          System.err.println("edge " + e + " not relaxed");
          return false;
        }
      }
    }

    // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
    for (int w = 0; w < G.V(); w++) {
      if (streetTo[w] == null) continue;
      Street e = streetTo[w];
      int v = e.from();
      if (w != e.to()) return false;
      if (secondsTo[v] + e.weight() != secondsTo[w]) {
        System.err.println("edge " + e + " on shortest path not tight");
        return false;
      }
    }
    return true;
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(int v) {
    int V = secondsTo.length;
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
  }

  public Iterable<Street> path() {
    return this.pathTo(this.finalCrossroad);
  }

  public int elapsedSeconds() {
    return this.secondsTo[this.finalCrossroad];
  }

  public static void main(String[] args) {
    String filename;

    if (args.length == 0) {
      System.out.println("No filename specified for retrieving graph data.");
      filename = "input/ex1_001.txt";
    } else filename = args[0];

    System.out.println("Attempting to read graph data from " + filename);
    In in = new In(filename);

    Ex1 demo = new Ex1(in);
    System.out.println(demo.path());
    System.out.println(demo.elapsedSeconds());
  }
}
