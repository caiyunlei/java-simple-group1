package com.wuhantoc.javasample.homework;

import com.wuhantoc.javasample.homework.robot.LockerRobot;
import com.wuhantoc.javasample.homework.robot.SimpleRobot;
import java.util.LinkedHashSet;
import java.util.Set;

public class RobotManager extends SimpleRobot {
  private Set<LockerRobot> controlledRobots = new LinkedHashSet<>();

  public void connectRobot(LockerRobot robot) {
    controlledRobots.add(robot);
  }

  public void disconnectRobot(LockerRobot robot) {
    controlledRobots.remove(robot);
  }

  @Override
  public Ticket pickTicket(Bag somethingToStore) {
    for (LockerRobot controlledRobot : controlledRobots) {
      final Ticket ticket = controlledRobot.pickTicket(somethingToStore);
      if (ticket != null) {
        return ticket;
      }
    }
    return super.pickTicket(somethingToStore);
  }

  @Override
  public Bag pickPackage(Ticket ticket) {
    for (LockerRobot controlledRobot : controlledRobots) {
      final Bag bag = controlledRobot.pickPackage(ticket);
      if (bag != null) {
        return bag;
      }
    }
    return super.pickPackage(ticket);
  }

}
