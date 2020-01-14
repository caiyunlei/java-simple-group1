package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Box;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FairRobotTest {
  @Test
  void should_save_to_locker1_when_save_package_given_empty_locker1_and_half_usage_locker2() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(2);
    Locker halfUsageLocker = makeHalfUsageLocker();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(emptyLocker);
    lockerRobot.connectLocker(halfUsageLocker);
    final Bag somethingToStore = new Bag();

    //when
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);

    //then
    final Box box = emptyLocker.openBox(ticket);
    final Bag storedThing = box.popStoredThing();
    Assertions.assertEquals(somethingToStore, storedThing);

    Box openBox = halfUsageLocker.openBox(ticket);
    Assertions.assertNull(openBox);
  }

  @Test
  void should_save_to_locker1_when_save_package_given_half_usage_locker1_half_usage_locker2() {
    //given
    Locker halfUsageLocker1 = makeHalfUsageLocker();
    Locker halfUsageLocker2 = makeHalfUsageLocker();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(halfUsageLocker1);
    lockerRobot.connectLocker(halfUsageLocker2);
    final Bag somethingToStore = new Bag();

    //when
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);

    //then
    final Box box = halfUsageLocker1.openBox(ticket);
    final Bag storedThing = box.popStoredThing();
    Assertions.assertEquals(somethingToStore, storedThing);

    Box openBox = halfUsageLocker2.openBox(ticket);
    Assertions.assertNull(openBox);
  }

  @Test
  void should_failed_when_save_package_given_full_usage_locker1_full_usage_locker2() {
    //given
    Locker fullLocker1 = makeFullUsageLocker();
    Locker fullLocker2 = makeFullUsageLocker();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(fullLocker1);
    lockerRobot.connectLocker(fullLocker2);
    final Bag somethingToStore = new Bag();

    //when
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);

    //then
    Assertions.assertNull(ticket);
  }

  @Test
  void should_get_bag_when_pick_package_given_right_ticket() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(2);
    Locker halfUsageLocker = makeHalfUsageLocker();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(emptyLocker);
    lockerRobot.connectLocker(halfUsageLocker);
    final Bag somethingToStore = new Bag();
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);

    //when
    Bag returnBag = lockerRobot.pickPackage(ticket);

    //then
    Assertions.assertEquals(somethingToStore,returnBag);
  }

  @Test
  void should_failed_when_pick_bag_given_fake_ticket() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(2);
    Locker halfUsageLocker = makeHalfUsageLocker();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(emptyLocker);
    lockerRobot.connectLocker(halfUsageLocker);
    final Bag somethingToStore = new Bag();
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);
    Ticket fakeTicket = new Ticket(UUID.randomUUID(),1);

    //when
    Bag bag = lockerRobot.pickPackage(fakeTicket);

    //then
    Assertions.assertNull(bag);
  }

  @Test
  void should_failed_when_pick_package_given_used_ticket() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(2);
    Locker halfUsageLocker = makeHalfUsageLocker();

    FairRobot lockerRobot = new FairRobot();
    lockerRobot.connectLocker(emptyLocker);
    lockerRobot.connectLocker(halfUsageLocker);
    final Bag somethingToStore = new Bag();
    final Ticket ticket = lockerRobot.pickTicket(somethingToStore);
    lockerRobot.pickPackage(ticket);

    //when
    Bag bag = lockerRobot.pickPackage(ticket);

    //then
    Assertions.assertNull(bag);
  }

  private Locker makeHalfUsageLocker() {
    Locker halfUsageLocker = Locker.newFixedSizeLocker(2);
    halfUsageLocker.pickTicket();
    return halfUsageLocker;
  }

  private Locker makeFullUsageLocker() {
    Locker fullLocker = Locker.newFixedSizeLocker(2);
    fullLocker.pickTicket();
    fullLocker.pickTicket();
    return fullLocker;
  }
}
