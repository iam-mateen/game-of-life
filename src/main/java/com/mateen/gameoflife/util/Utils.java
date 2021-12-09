package com.mateen.gameoflife.util;

public class Utils {

  private Utils() {
  }

  public static String getSqlURL(String url, int port, String databaseName) {
    final String completeUrl = "jdbc:mysql://" + url+ ":" + port + "/" + databaseName;
    return completeUrl;
  }

  public static int requirePositiveNumber(int number, String message) {
    if (number <= 0) {
      throw new IllegalArgumentException(message);
    }
    return number;
  }
}
