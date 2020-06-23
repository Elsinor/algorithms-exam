package fabrizio.armango.ex1;

import edu.princeton.cs.algs4.Bag;

// super class constructor
// EdgeWeightedDigraph(int V, int E)
public class City {
  private final int V; // number of vertices in this digraph
  private int E; // number of edges in this digraph
  private Bag<Street>[] adj; // adj[v] = adjacency list for vertex v
  private int[] indegree; // indegree[v] = indegree of vertex v

  public City(int numberOfCrossroads) {
    // super(numberOfCrossroads, numberOfStreets);
    this.V = numberOfCrossroads;
    this.E = 0;
    this.indegree = new int[V];
    adj = (Bag<Street>[]) new Bag[V];
    for (int v = 0; v < V; v++) adj[v] = new Bag<Street>();
    // new Street(v, w, 2s, 5s,);
  }

  /**
   * Adds the directed edge {@code e} to this edge-weighted digraph.
   *
   * @param e the edge
   * @throws IllegalArgumentException unless endpoints of edge are between {@code 0} and {@code V-1}
   */
  // super class method
  // public void addEdge(Street e)
  public void addStreet(int v, int w, int openTimeDuration, int closeTimeDuration, int travelTime) {
    Street e = new Street(v, w, openTimeDuration, closeTimeDuration, travelTime);
    validateVertex(v);
    validateVertex(w);
    adj[v].add(e);
    indegree[w]++;
    E++;
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(int v) {
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
  }

  /**
   * Returns the directed edges incident from vertex {@code v}.
   *
   * @param v the vertex
   * @return the directed edges incident from vertex {@code v} as an Iterable
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public Iterable<Street> adj(int v) {
    validateVertex(v);
    return adj[v];
  }

  /**
   * Returns all directed edges in this edge-weighted digraph. To iterate over the edges in this
   * edge-weighted digraph, use foreach notation: {@code for (Street e : G.edges())}.
   *
   * @return all edges in this edge-weighted digraph, as an iterable
   */
  public Iterable<Street> edges() {
    Bag<Street> list = new Bag<Street>();
    for (int v = 0; v < V; v++) {
      for (Street e : adj(v)) {
        list.add(e);
      }
    }
    return list;
  }

  /**
   * Returns the number of vertices in this edge-weighted digraph.
   *
   * @return the number of vertices in this edge-weighted digraph
   */
  public int V() {
    return V;
  }

  /**
   * Returns the number of edges in this edge-weighted digraph.
   *
   * @return the number of edges in this edge-weighted digraph
   */
  public int E() {
    return E;
  }
}
