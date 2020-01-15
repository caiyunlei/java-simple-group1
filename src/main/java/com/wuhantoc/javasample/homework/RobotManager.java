package com.wuhantoc.javasample.homework;

import com.wuhantoc.javasample.homework.robot.AbstractRobot;
import com.wuhantoc.javasample.homework.robot.LockerRobot;
import java.util.LinkedHashSet;
import java.util.Set;

public class RobotManager extends AbstractRobot {
  private Set<LockerRobot> controlledRobots = new LinkedHashSet<>();

  @Override
  protected Locker findLockerToSave() {
    return controlledLockers.stream().filter(Locker::haveUnusedBox).findFirst().orElse(null);
  }

  public void connectRobot(LockerRobot robot) {
    controlledRobots.add(robot);
  }

  public void disconnectRobot(LockerRobot robot) {
    controlledRobots.remove(robot);
  }
}
