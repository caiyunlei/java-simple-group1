package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import java.util.LinkedHashSet;
import java.util.Set;

public class RobotManager extends AbstractRobot {
  private Set<LockerRobot> connectedRobots = new LinkedHashSet<>();

  public void connectRobot(LockerRobot robot) {
    connectedRobots.add(robot);
  }

  public void disconnectRobot(LockerRobot robot) {
    connectedRobots.remove(robot);
  }

  @Override
  public Ticket pickTicket(Bag somethingToStore) {
    for (LockerRobot connectedRobot : connectedRobots) {
      final Ticket ticket = connectedRobot.pickTicket(somethingToStore);
      if (ticket != null) {
        return ticket;
      }
    }
    return super.pickTicket(somethingToStore);
  }

  @Override
  public Bag pickPackage(Ticket ticket) {
    for (LockerRobot connectedRobot : connectedRobots) {
      final Bag bag = connectedRobot.pickPackage(ticket);
      if (bag != null) {
        return bag;
      }
    }
    return super.pickPackage(ticket);
  }

  protected Locker findLockerToSave() {
    return connectedLockers.stream().filter(Locker::haveUnusedBox).findFirst().orElse(null);
  }

}
