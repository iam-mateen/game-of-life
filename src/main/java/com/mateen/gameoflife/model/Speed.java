package com.mateen.gameoflife.model;

public enum Speed {

  SLOW(800),

  MEDIUM(200),

  FAST(80),

  FASTEST(10);

  private final double milliseconds;

  Speed(double milliseconds) {
    this.milliseconds = milliseconds;
  }

  public double getMilliseconds() {
    return milliseconds;
  }
}
