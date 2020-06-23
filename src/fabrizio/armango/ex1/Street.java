package fabrizio.armango.ex1;

import edu.princeton.cs.algs4.DirectedEdge;

// DirectedEdge(int v, int w, double weight)
public class Street extends DirectedEdge {
  public int openTimeDuration;
  public int closeTimeDuration;
  public int cycleDuration;

  public Street(int v, int w, int openTimeDuration, int closeTimeDuration, int travelTime) {
    super(v, w, travelTime);
    this.openTimeDuration = openTimeDuration;
    this.closeTimeDuration = closeTimeDuration;
    this.cycleDuration = openTimeDuration + closeTimeDuration;
  }

  public int travelTime() {
    return (int) super.weight();
  }

  private int raceTimeToCycleTime(int time) {
    return time % this.cycleDuration;
  }

  public boolean isOpenAt(int time) {
    return this.raceTimeToCycleTime(time) < this.openTimeDuration;
  }

  public int willCloseIn(int time) {
    int cycleTime = this.raceTimeToCycleTime(time);
    // if closed
    if (cycleTime >= this.openTimeDuration) return 0;
    // if open
    return this.openTimeDuration - cycleTime;
  }

  public int neededSecondsAt(int time) {
    int secondsStreetWillBeOpen = this.willCloseIn(time);
    if (secondsStreetWillBeOpen < this.travelTime()) {
      return travelTime()
          + this.closeTimeDuration
          + (!Consts.CAN_WAIT_IN_STREET_IF_TRAVEL_STARTED_WHEN_OPEN ? secondsStreetWillBeOpen : 0);
    }
    return this.travelTime();
  }
}
