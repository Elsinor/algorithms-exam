package fabrizio.armango;
import fabrizio.armango.ex4.HashTable;

// import edu.princeton.cs.algs4.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class Ex4 {
  private static int m = 17;

  public static void main(String[] args) {
    String filename;
    if (args.length == 0) {
      System.out.println("No filename specified for input integers.");
      filename = "input/ex4_001.txt";
    } else filename = args[0];

    System.out.println("Attempting to read integers from " + filename);
    In in = new In(filename);

    HashTable<Integer, Integer> hashTable = new HashTable<Integer, Integer>(m);
    for (int i = 0; !in.isEmpty(); i++) {
      hashTable.put(i, in.readInt());
    }

    hashTable.print();

    System.out.println("Chiave da cercare: ");
    int keyToSearch = StdIn.readInt();
    Integer found = hashTable.get(keyToSearch);
    System.out.println(
        "element with key \"" + keyToSearch + "\"" + (found == null ? " not" : "") + " found");
    System.out.println("with " + hashTable.getNumAttempts() + " attempts");
  }
}
