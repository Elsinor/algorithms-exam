package fabrizio.armango.ex4;

import edu.princeton.cs.algs4.StdOut;

public class HashTable<Key, Value> extends LinearProbingHashST<Key, Value> {

  private int numAttempts = 0;

  public HashTable(int m) {
    super(m);
  }

  public int hash(Key key, int i) {
    return (h1(key) + 3 * i + i * i) % m;
  }

  private int h1(Key key) {
    return key.hashCode() % m;
  }

  public void resetNumAttempts() {
    numAttempts = 0;
  }

  public int getNumAttempts() {
    return numAttempts;
  }

  @Override
  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("first argument to put() is null");

    if (val == null) {
      delete(key);
      return;
    }

    // double table size if 50% full
    if (n >= m / 2) resize(2 * m);

    int i = 0, hashCode = hash(key, i);
    do {
      if (keys[hashCode] != null) {
        if (keys[hashCode].equals(key)) {
          vals[hashCode] = val;
          return;
        }
      } else break;
      i++;
      hashCode = hash(key, i);
    } while (i < m);

    keys[hashCode] = key;
    vals[hashCode] = val;
    n++;
  }

  @Override
  public Value get(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to get() is null");
    for (int i = 0; i < m; i++) {
      this.numAttempts++;
      int hashCode = hash(key, i);
      if (keys[hashCode] == null) {
        break;
      }

      if (keys[hashCode].equals(key)) {
        return vals[hashCode];
      }
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("");
    s.append("Index\t\t\tKey\t\t\tValue\n");
    for (int i = 0; i < m; i++) {
      if (keys[i] != null)
        s.append("i:" + i + "\t\t\t" + "k: " + keys[i] + "\t\t\t" + vals[i] + "\n");
      else s.append("i:" + i + "\t\t\t" + "k: " + keys[i] + "\t\t\tempty\n");
    }
    return s.toString();
  }

  public void print() {
    StdOut.println(this);
  }
}
