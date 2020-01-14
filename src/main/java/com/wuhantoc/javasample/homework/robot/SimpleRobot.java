package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Locker;

public class SimpleRobot extends AbstractRobot implements LockerRobot {

  protected Locker findLockerToSave() {
    return controlledLockers.stream().filter(Locker::haveUnusedBox).findFirst().orElse(null);
  }
}
