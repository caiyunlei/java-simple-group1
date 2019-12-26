package com.wuhantoc.javasample.utils;

public class UnitConventer {

  private static final double FOOT_TO_YARD_RATIO = 1d / 3d;

  public static double convertToYard(double number, String unit) {
    if ("foot".equals(unit)) {
      return number * FOOT_TO_YARD_RATIO;
    }
    return -1;
  }

}
