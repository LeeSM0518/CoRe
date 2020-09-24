package io.wisoft.core.utils;

import java.util.Random;

public class RandomValueUtil {

  public static String getRandom() {
    Random random = new Random(System.currentTimeMillis());
    StringBuilder buffer = new StringBuilder();
    while (buffer.length() < 8) {
      buffer.append(random.nextInt(10));
    }
    return buffer.toString();
  }

}
