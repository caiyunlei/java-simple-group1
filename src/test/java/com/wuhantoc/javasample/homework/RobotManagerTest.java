package com.wuhantoc.javasample.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.wuhantoc.javasample.homework.robot.FairRobot;
import com.wuhantoc.javasample.homework.robot.SimpleRobot;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class RobotManagerTest {
  @Test
  void should_success_when_save_package_given_robot_manager_and_simple_robot_and_empty_locker() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(simpleRobot);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNotNull(ticket);
  }

  @Test
  void should_save_to_locker_when_save_package_given_robot_manager_and_fair_robot_and_empty_locker() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(emptyLocker);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNotNull(ticket);
  }

  @Test
  void should_success_when_save_package_given_robot_manager_and_fair_robot_and_simple_robot_and_empty_locker() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(emptyLocker);

    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);
    robotManager.connectRobot(simpleRobot);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNotNull(ticket);
  }

  @Test
  void should_failed_when_save_package_given_robot_manager_and_simple_robot_and_full_locker() {
    //given
    Locker fullLocker = createFullLocker();
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(fullLocker);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(simpleRobot);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNull(ticket);
  }

  @Test
  void should_failed_when_save_package_given_robot_manager_and_fair_robot_and_full_locker() {
    //given
    Locker fullLocker = createFullLocker();

    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(fullLocker);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNull(ticket);
  }

  @Test
  void should_failed_when_save_package_given_robot_manager_and_simple_robor_and_simple_robot_and_full_locker() {
    //given
    Locker fullLocker = createFullLocker();

    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(fullLocker);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(fullLocker);
    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);
    robotManager.connectRobot(fairRobot);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNull(ticket);
  }

  @Test
  void should_get_bag_when_pick_package_given_right_ticket() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker);
    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(simpleRobot);
    final Bag somethingToStore = new Bag();
    Ticket ticket = robotManager.pickTicket(somethingToStore);

    //when
    Bag returnBag = robotManager.pickPackage(ticket);

    //then
    assertEquals(somethingToStore,returnBag);
  }

  @Test
  void should_failed_when_pick_package_given_used_ticket() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker);
    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(simpleRobot);
    final Bag somethingToStore = new Bag();
    Ticket ticket = robotManager.pickTicket(somethingToStore);
    robotManager.pickPackage(ticket);

    //when
    Bag returnBag = robotManager.pickPackage(ticket);

    //then
    assertNull(returnBag);
  }

  @Test
  void should_failed_when_pick_package_given_fake_ticket() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker);
    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(simpleRobot);
    final Bag somethingToStore = new Bag();
    robotManager.pickTicket(somethingToStore);

    //when
    Bag returnBag = robotManager.pickPackage(new Ticket(UUID.randomUUID(),0));

    //then
    assertNull(returnBag);
  }

  @Test
  void should_get_bag_from_manager_when_save_package_given_ticket_from_second_save_package() {
    //given
    Locker emptyLocker1 = Locker.newFixedSizeLocker(1);
    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(emptyLocker1);

    Locker emptyLocker2 = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker2);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);
    robotManager.connectRobot(simpleRobot);
    robotManager.pickTicket(new Bag());
    final Bag somethingToStore = new Bag();
    Ticket secondTicket = robotManager.pickTicket(somethingToStore);

    //when
    Bag returnBag = robotManager.pickPackage(secondTicket);

    //then
    assertEquals(somethingToStore, returnBag);
  }

  @Test
  void should_get_bag_from_second_robot_when_save_package_given_ticket_from_second_save_package() {
    //given
    Locker emptyLocker1 = Locker.newFixedSizeLocker(1);
    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(emptyLocker1);

    Locker emptyLocker2 = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker2);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);
    robotManager.connectRobot(simpleRobot);
    robotManager.pickTicket(new Bag());
    final Bag somethingToStore = new Bag();
    Ticket secondTicket = robotManager.pickTicket(somethingToStore);

    //when
    Bag returnBag = simpleRobot.pickPackage(secondTicket);

    //then
    assertEquals(somethingToStore, returnBag);
  }

  @Test
  void should_get_bag_from_manager_when_save_package_given_ticket_from_third_save_package() {
    //given
    Locker emptyLocker1 = Locker.newFixedSizeLocker(1);
    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(emptyLocker1);

    Locker emptyLocker2 = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker2);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);
    robotManager.connectRobot(simpleRobot);

    Locker emptyLocker3 = Locker.newFixedSizeLocker(1);
    robotManager.connectLocker(emptyLocker3);
    robotManager.pickTicket(new Bag());
    robotManager.pickTicket(new Bag());
    final Bag somethingToStore = new Bag();
    Ticket thirdTicket = robotManager.pickTicket(somethingToStore);

    //when
    Bag returnBag = robotManager.pickPackage(thirdTicket);

    //then
    assertEquals(somethingToStore, returnBag);
  }

  @Test
  void should_get_bag_from_locker3_when_save_package_given_ticket_from_third_save_package() {
    //given
    Locker emptyLocker1 = Locker.newFixedSizeLocker(1);
    FairRobot fairRobot = new FairRobot();
    fairRobot.connectLocker(emptyLocker1);

    Locker emptyLocker2 = Locker.newFixedSizeLocker(1);
    SimpleRobot simpleRobot = new SimpleRobot();
    simpleRobot.connectLocker(emptyLocker2);

    RobotManager robotManager = new RobotManager();
    robotManager.connectRobot(fairRobot);
    robotManager.connectRobot(simpleRobot);

    Locker emptyLocker3 = Locker.newFixedSizeLocker(1);
    robotManager.connectLocker(emptyLocker3);
    robotManager.pickTicket(new Bag());
    robotManager.pickTicket(new Bag());

    final Bag somethingToStore = new Bag();
    Ticket thirdTicket = robotManager.pickTicket(somethingToStore);

    //when
    Bag returnBag = emptyLocker3.openBox(thirdTicket).popStoredThing();

    //then
    assertEquals(somethingToStore, returnBag);
  }

  private Locker createFullLocker() {
    Locker locker = Locker.newFixedSizeLocker(1);
    locker.pickTicket();
    return locker;
  }
}
