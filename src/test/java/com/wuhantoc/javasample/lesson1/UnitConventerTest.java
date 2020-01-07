package com.wuhantoc.javasample.lesson1;

import com.wuhantoc.javasample.lesson1.enums.UnitEnum;
import com.wuhantoc.javasample.lesson1.utils.UnitConventer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitConventerTest {

  @Test
  // should_xxx_when_xxx_given_xxx
  void should_be_1_out_of_3_when_convent_to_yard_given_1_foot() {
    // given
    double number = 1;
    UnitEnum unit = UnitEnum.FOOT;

    // when
    double result = UnitConventer.convertToYard(number, unit);

    // then
    Assertions.assertEquals(1d/3d, result);
  }
}
