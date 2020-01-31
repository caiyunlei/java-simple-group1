package com.wuhantoc.javasample.homework.robot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Box;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import org.junit.jupiter.api.Test;

class SimpleRobotTest {
  @Test
  void should_failed_when_store_given_all_full_locker() {
    //given
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(Locker.newFixedSizeLocker(1));
    simpleRobot.connectLocker(Locker.newFixedSizeLocker(1));
    simpleRobot.pickTicket(new Bag());
    simpleRobot.pickTicket(new Bag());

    //when
    Ticket ticket = simpleRobot.pickTicket(new Bag());

    //then
    assertNull(ticket);
  }

  @Test
  void should_store_to_locker1_when_store_given_empty_locker1_and_empty_locker2() {
    //given
    SimpleRobot simpleRobot = new SimpleRobot();
    final Locker locker1 = Locker.newFixedSizeLocker(1);
    simpleRobot.connectLocker(locker1);
    final Locker locker2 = Locker.newFixedSizeLocker(1);
    simpleRobot.connectLocker(locker2);

    //when
    final Bag somethingToStore = new Bag();
    Ticket ticket = simpleRobot.pickTicket(somethingToStore);

    //then
    Box box1 = locker1.openBox(ticket);
    assertNotNull(box1);

    Box box2 = locker2.openBox(ticket);
    assertNull(box2);
  }
}
