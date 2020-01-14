package com.wuhantoc.javasample.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.util.UUID;
import org.junit.jupiter.api.Test;

class RobotTest {
  @Test
  void should_success_when_store_package_given_empty_locker() {
    //given
    Robot robot = new Robot();
    robot.connectLocker(Locker.newFixedSizeLocker(1));
    Object somethingToStore = new Object();

    //when
    Ticket ticket = robot.pickTicket(somethingToStore);

    //then
    assertNotNull(ticket);
  }

  @Test
  void should_failed_when_store_given_all_full_locker() {
    //given
    Robot robot = new Robot();
    robot.connectLocker(Locker.newFixedSizeLocker(1));
    robot.connectLocker(Locker.newFixedSizeLocker(1));
    robot.pickTicket(new Object());
    robot.pickTicket(new Object());

    //when
    Ticket ticket = robot.pickTicket(new Object());

    //then
    assertNull(ticket);
  }

  @Test
  void should_success_when_pick_given_correct_ticket() {
    //given
    Robot robot = new Robot();
    robot.connectLocker(Locker.newFixedSizeLocker(1));
    final Object somethingToStore = new Object();
    Ticket ticket = robot.pickTicket(somethingToStore);

    //when
    Object returnPackage = robot.pickPackage(ticket);

    //then
    assertEquals(somethingToStore, returnPackage);
  }

  @Test
  void should_failed_when_pick_given_wrong_ticket() {
    //given
    Robot robot = new Robot();
    robot.connectLocker(Locker.newFixedSizeLocker(1));
    robot.pickTicket(new Object());
    Ticket wrongTicker = new Ticket(UUID.randomUUID(),0);

    //when
    Object returnPackage = robot.pickPackage(wrongTicker);

    //then
    assertNull(returnPackage);
  }


  @Test
  void should_store_to_locker1_when_store_given_empty_locker1_and_empty_locker2() {
    //given
    Robot robot = new Robot();
    final Locker locker1 = Locker.newFixedSizeLocker(1);
    robot.connectLocker(locker1);
    robot.connectLocker(Locker.newFixedSizeLocker(1));

    //when
    final Object somethingToStore = new Object();
    Ticket ticket = robot.pickTicket(somethingToStore);

    //then
    Box box = locker1.openBox(ticket);
    assertNotNull(box);
  }

  @Test
  void should_not_store_to_locker2_when_store_given_empty_locker1_and_empty_locker2() {
    //given
    Robot robot = new Robot();
    robot.connectLocker(Locker.newFixedSizeLocker(1));
    final Locker locker2 = Locker.newFixedSizeLocker(1);
    robot.connectLocker(locker2);

    //when
    final Object somethingToStore = new Object();
    Ticket ticket = robot.pickTicket(somethingToStore);

    //then
    Box box = locker2.openBox(ticket);
    assertNull(box);
  }
}
