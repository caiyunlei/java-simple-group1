package com.wuhantoc.javasample.homework;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoxTest {

  @Test
  void should_get_correct_package_when_pick_given_box() {
    //given
    Box box = new Box(0);
    box.open();
    final Bag somethingToStore = new Bag();
    box.putSomething(somethingToStore);

    //when
    Bag storedThing = box.popStoredThing();

    //then
    Assertions.assertEquals(somethingToStore,storedThing);
  }

}
