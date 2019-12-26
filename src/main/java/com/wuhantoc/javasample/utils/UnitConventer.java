package com.wuhantoc.javasample.utils;

public class UnitConventer {

  public static double convertToYard(double number, String unit) {
    if ("foot".equals(unit)) {
      return number / 3;
    }
    return -1;
  }

}
