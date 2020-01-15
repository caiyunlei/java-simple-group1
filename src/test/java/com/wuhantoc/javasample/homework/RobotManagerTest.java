package com.wuhantoc.javasample.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.wuhantoc.javasample.homework.robot.SimpleRobot;
import org.junit.jupiter.api.Test;

class RobotManagerTest {
  @Test
  void should_success_when_save_package_given_robot_manager_and_empty_locker() {
    //given
    Locker emptyLocker = Locker.newFixedSizeLocker(1);
    RobotManager robotManager = new RobotManager();
    robotManager.connectLocker(emptyLocker);

    //when
    Ticket ticket = robotManager.pickTicket(new Bag());

    //then
    assertNotNull(ticket);
  }

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
    assertEquals(1,2);
  }

  @Test
  void should_success_when_save_package_given_robot_manager_and_fair_robot_and_simple_and_empty_locker() {

  }

  @Test
  void should_failed_when_save_package_given_robot_manage_and_full_locker() {

  }

  @Test
  void should_failer_when_save_package_given_robot_manager_and_simple_robot_and_full_locker() {

  }

  @Test
  void should_failed_when_save_package_given_robot_manager_and_fair_robot_and_full_locker() {

  }

  @Test
  void should_failed_when_save_package_given_robot_manager_and_simple_robor_and_simple_robot_and_full_locker() {

  }



}
