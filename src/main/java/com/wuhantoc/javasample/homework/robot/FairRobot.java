package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Locker;

public class FairRobot extends AbstractRobot {

  @Override
  protected Locker findLockerToSave() {
    return findLowestUsageLocker();
  }

  private Locker findLowestUsageLocker() {
    return controlledLockers.stream()
        .filter(Locker::haveUnusedBox)
        .min(FairRobot::compare)
        .orElse(null);
  }


  private static int compare(Locker locker1, Locker locker2) {
    if (locker1.getUsageRatio() >= locker2.getUsageRatio()) {
      return -1;
    } else {
      return 1;
    }
  }
}
