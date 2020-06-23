package fabrizio.armango;

import edu.princeton.cs.algs4.StdDraw;
import fabrizio.armango.ex3.CustomGraph;
import java.awt.Font;
import java.util.Arrays;

public class Ex3 {
  public static final int NUMBER_OF_RANDOM_GRAPHS = 20;
  public static final int CANVAS_WIDTH = 1280;
  public static final int CANVAS_HEIGHT = 800;

  private static double hPadding, vPadding, distY1, distY2, distY4, distX1;
  private static double boxWidth, boxHeight, boxY;

  public static void main(String[] args) {
    int[] x = new int[NUMBER_OF_RANDOM_GRAPHS];
    int[] y = new int[NUMBER_OF_RANDOM_GRAPHS];

    for (int n = 0; n < NUMBER_OF_RANDOM_GRAPHS; n++) {
      int V = (n + 1) * 25;
      int E = 2 * V * (int) Math.log(V);

      CustomGraph G = new CustomGraph(V, E);
      int K = G.averageWeight();
      int numShortestPaths = Ex2.getShortestPathsWithCostGreaterThan(G, K).size();

      x[n] = V;
      y[n] = numShortestPaths;
    }

    init(x, y);
    drawDescription();
    draw(x, y);
  }

  public static void init(int[] x, int[] y) {
    StdDraw.enableDoubleBuffering();
    StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    boxWidth = Arrays.stream(x).max().getAsInt();
    boxHeight = Arrays.stream(y).max().getAsInt();

    hPadding = boxWidth / 4.0;
    vPadding = boxHeight / 4.0;

    StdDraw.setXscale(-hPadding, boxWidth + hPadding);
    StdDraw.setYscale(-vPadding, boxHeight + vPadding);
    StdDraw.setPenRadius(0.001);

    distY1 = vPadding / 10;
    distY2 = distY1 * 2;
    distY4 = distY2 * 2;

    distX1 = hPadding / 10;

    boxY = boxHeight + distY1;
  }

  public static void drawDescription() {
    StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
    double _currentY = boxY;

    StdDraw.textLeft(distX1, _currentY, "Prova Pratica - I appello Giugno 2020 - Esercizio 3");
    StdDraw.setFont(new Font("Arial", Font.BOLD, 13));
    _currentY -= distY2;

    StdDraw.textLeft(distX1, _currentY, "Fabrizio Armango");

    _currentY -= distY4;
    StdDraw.textLeft(distX1, _currentY, "Legenda");
    _currentY -= distY2;
    StdDraw.textLeft(distX1, _currentY, "x: Numero di vertici");
    _currentY -= distY1;
    StdDraw.textLeft(
        distX1,
        _currentY,
        "y: Numero di coppie di vertici il cui cammino minimo ha un costo maggiore di K.");

    _currentY -= distY2;
    StdDraw.textLeft(distX1, _currentY, "( K = valore medio dei pesi )");
  }

  public static void draw(int[] x, int[] y) {
    StdDraw.setFont(new Font("Arial", Font.BOLD, 13));

    // x axis
    StdDraw.line(0, 0, boxWidth, 0);
    StdDraw.text(boxWidth + distX1, 0, "x");

    // y axis
    StdDraw.line(0, 0, 0, boxHeight);
    StdDraw.text(0, boxHeight + distY1, "y");

    // origin (0,0)
    StdDraw.text(-distX1, -distY1, "(0,0)");

    double segmentY0 = distY1 / 4, segmentY1 = -segmentY0;
    double segmentX0 = distX1 / 8, segmentX1 = -segmentX0;
    for (int i = 0; i < x.length; i++) {
      StdDraw.setPenRadius(0.01);
      StdDraw.point(x[i], y[i]);
      StdDraw.text(x[i], -distY2, "" + x[i]);
      StdDraw.text(x[i], y[i] + distY1, "" + y[i]);

      // x axis segments
      StdDraw.setPenRadius(0.0005);
      StdDraw.line(x[i], segmentY0, x[i], segmentY1);
      // y axis segments
      StdDraw.line(segmentX0, y[i], segmentX1, y[i]);
    }

    StdDraw.show();
  }
}
