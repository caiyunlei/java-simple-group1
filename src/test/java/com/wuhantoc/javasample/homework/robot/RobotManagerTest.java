package com.wuhantoc.javasample.homework.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Box;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RobotManagerTest {
  @Test
  void should_success_when_save_package_given_robot_manager_and_available_robot() {
    //given
    Bag bag = new Bag();
    LockerRobot lockerRobot = Mockito.mock(LockerRobot.class);
    final Ticket mockTicket = new Ticket(UUID.randomUUID(), 0);
    Mockito.when(lockerRobot.pickTicket(bag)).thenReturn(mockTicket);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(lockerRobot);

    //when
    Ticket ticket = robotManager.pickTicket(bag);

    //then
    assertEquals(mockTicket,ticket);
  }

  @Test
  void should_failed_when_save_package_given_robot_manager_and_unavailable_robot() {
    //given
    Bag bag = new Bag();
    LockerRobot lockerRobot = Mockito.mock(LockerRobot.class);
    Mockito.when(lockerRobot.pickTicket(bag)).thenReturn(null);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(lockerRobot);

    //when
    Ticket ticket = robotManager.pickTicket(bag);

    //then
    assertNull(ticket);
  }

  @Test
  void should_success_when_save_package_given_robot_manager_and_two_available_robot() {
    //given
    Bag firstBag = new Bag();
    Bag secondBag = new Bag();
    LockerRobot firstRobot = Mockito.mock(LockerRobot.class);
    LockerRobot secondRobot = Mockito.mock(LockerRobot.class);
    final Ticket mockTicket = new Ticket(UUID.randomUUID(), 0);
    Mockito.when(secondRobot.pickTicket(secondBag)).thenReturn(mockTicket);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(firstRobot);
    robotManager.connectRobot(secondRobot);

    //when
    robotManager.pickTicket(firstBag);
    Ticket ticket = robotManager.pickTicket(secondBag);

    //then
    assertEquals(mockTicket,ticket);
  }

  @Test
  void should_success_when_store_package_given_empty_locker() {
    //given
    RobotManager robotManager = new RobotManager();
    robotManager.connectLocker(Locker.newFixedSizeLocker(1));
    Bag somethingToStore = new Bag();

    //when
    Ticket ticket = robotManager.pickTicket(somethingToStore);

    //then
    assertNotNull(ticket);
  }

  @Test
  void should_failed_when_store_given_all_full_locker() {
    //given
    RobotManager robotManager = new RobotManager();
    robotManager.connectLocker(Locker.newFixedSizeLocker(1));
    robotManager.connectLocker(Locker.newFixedSizeLocker(1));
    robotManager.pickTicket(new Bag());
    robotManager.pickTicket(new Bag());

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNull(ticket);
  }

  @Test
  void should_success_when_pick_given_correct_ticket() {
    //given
    RobotManager robotManager = new RobotManager();
    robotManager.connectLocker(Locker.newFixedSizeLocker(1));
    final Bag somethingToStore = new Bag();
    Ticket ticket = robotManager.pickTicket(somethingToStore);

    //when
    Bag returnPackage = robotManager.pickPackage(ticket);

    //then
    assertEquals(somethingToStore, returnPackage);
  }

  @Test
  void should_failed_when_pick_given_wrong_ticket() {
    //given
    RobotManager robotManager = new RobotManager();
    robotManager.connectLocker(Locker.newFixedSizeLocker(1));
    robotManager.pickTicket(new Bag());
    Ticket wrongTicker = new Ticket(UUID.randomUUID(), 0);

    //when
    Object returnPackage = robotManager.pickPackage(wrongTicker);

    //then
    assertNull(returnPackage);
  }

  @Test
  void should_store_to_locker1_when_store_given_empty_locker1_and_empty_locker2() {
    //given
    RobotManager robotManager = new RobotManager();
    final Locker locker1 = Locker.newFixedSizeLocker(1);
    robotManager.connectLocker(locker1);
    final Locker locker2 = Locker.newFixedSizeLocker(1);
    robotManager.connectLocker(locker2);

    //when
    final Bag somethingToStore = new Bag();
    Ticket ticket = robotManager.pickTicket(somethingToStore);

    //then
    Box box1 = locker1.openBox(ticket);
    assertNotNull(box1);

    Box box2 = locker2.openBox(ticket);
    assertNull(box2);
  }
}
