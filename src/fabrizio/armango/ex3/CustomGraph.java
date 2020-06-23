package fabrizio.armango.ex3;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdRandom;

public class CustomGraph extends EdgeWeightedDigraph {

  public CustomGraph(int V, int E) {
    super(V);
    if (E < 0)
      throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
    for (int i = 0; i < E; i++) {
      int v = StdRandom.uniform(V);
      int w = StdRandom.uniform(V);
      double weight = StdRandom.uniform(100);
      DirectedEdge e = new DirectedEdge(v, w, weight);
      addEdge(e);
    }
  }

  public int averageWeight() {
    int sum = 0;
    for (DirectedEdge e : this.edges()) {
      sum += e.weight();
    }
    return sum / this.E();
  }
}
