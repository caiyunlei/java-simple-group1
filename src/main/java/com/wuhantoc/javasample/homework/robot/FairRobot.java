package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Locker;

public class FairRobot extends AbstractRobot {

  @Override
  protected Locker findLockerToSave() {
    return findLowestUsageLocker();
  }

  private Locker findLowestUsageLocker() {
    return connectedLockers.stream()
        .filter(Locker::haveUnusedBox)
        .min(FairRobot::compare)
        .orElse(null);
  }

  private static int compare(Locker locker1, Locker locker2) {
    return (int) (locker1.getUsageRatio() - locker2.getUsageRatio());
  }
}
