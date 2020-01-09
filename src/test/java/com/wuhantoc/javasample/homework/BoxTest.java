package com.wuhantoc.javasample.homework;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoxTest {

  @Test
  void should_get_correct_package_when_pick_given_box() {
    //given
    Box box = new Box(0);
    box.open();
    final Object somethingToStore = new Object();
    box.putSomething(somethingToStore);

    //when
    Object storedThing = box.popStoredThing();

    //then
    Assertions.assertEquals(somethingToStore,storedThing);
  }

}
