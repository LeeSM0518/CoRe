package io.wisoft.core.utils;

import java.util.Arrays;

public class DirectoryManagementUtilTest {

  public static void main(String[] args) {
    String[] test = {
        "qwe@a.q",
        "qwe@ab.q",
        "qwe@abc.q",
        "qwe@abc.d",
        "qwe@abcd.asd",
        "qwe@q.abve"
    };

    for (String str : test) {
      String[] split = str.split("@");
      System.out.println(Arrays.toString(split));
      System.out.println(str.split("@")[0]);
//      System.out.println(str.split("\\.")[split.length - 1]);
    }
  }

}
