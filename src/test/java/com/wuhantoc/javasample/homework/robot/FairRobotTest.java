package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Box;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FairRobotTest {
  @Test
  void should_save_to_locker1_when_save_package_given_empty_locker1_and_half_usage_locker2() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(2);
    Locker halfUsageLocker = Locker.newFixedSizeLocker(2);
    halfUsageLocker.pickTicket();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(emptyLocker);
    lockerRobot.connectLocker(halfUsageLocker);
    final Bag somethingToStore = new Bag();

    //when
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);

    //then
    final Box box = emptyLocker.openBox(ticket);
    final Bag storedThing = box.popStoredThing();
    Assertions.assertEquals(somethingToStore, somethingToStore);
  }

}
