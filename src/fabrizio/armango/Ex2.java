package fabrizio.armango;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class Ex2 {

  public static Stack<Iterable<DirectedEdge>> getShortestPathsWithCostGreaterThan(
      EdgeWeightedDigraph G, double K) {
    Stack<Iterable<DirectedEdge>> output = new Stack<Iterable<DirectedEdge>>();
    DijkstraSP dijkstraSP;

    for (int s = 0; s < G.V(); s++) { // for each vertex in Graph
      dijkstraSP = new DijkstraSP(G, s); // execute Dijkstra algorithm considering it as source
      for (int v = 0; v < G.V(); v++) {
        if (dijkstraSP.hasPathTo(v)) {
          if (dijkstraSP.distTo(v) > K) {
            output.push(dijkstraSP.pathTo(v));
          }
        }
      }
    }
    return output;
  }

  public static void main(String[] args) {
    String filename;
    double K;

    if (args.length > 0) {
      filename = args[0];
      System.out.println("Attempting to read graph data from " + filename);
      System.out.println("Insert K: ");
      K = StdIn.readDouble();
      System.out.println("Found K " + K);

      System.out.println(
          Ex2.getShortestPathsWithCostGreaterThan(new EdgeWeightedDigraph(new In(filename)), K));

    } else {
      K = 1.37;
      System.out.println("No filename specified for retrieving graph data.");
      filename = "input/mediumEWD.txt";
      System.out.println("Attempting to read graph data from " + filename);
      System.out.println(
          Ex2.getShortestPathsWithCostGreaterThan(new EdgeWeightedDigraph(new In(filename)), K));
    }
  }
}
