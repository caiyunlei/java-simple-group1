package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractRobot implements LockerRobot{
  protected Set<Locker> controlledLockers = new LinkedHashSet<>();

  public void connectLocker(Locker newLocker) {
    this.controlledLockers.add(newLocker);
  }

  public void disconnectLocker(Locker connectedLocker) {
    if (controlledLockers.contains(connectedLocker)) {
      this.controlledLockers.remove(connectedLocker);
    }
  }

  @Override
  public abstract Ticket pickTicket(Object somethingToStore);

  @Override
  public abstract Object pickPackage(Ticket ticket);
}
