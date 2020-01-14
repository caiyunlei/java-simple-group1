package com.wuhantoc.javasample.homework;

import java.util.LinkedHashSet;
import java.util.Set;

public class Robot implements LockerRobot {
  private Set<Locker> controlledLockers = new LinkedHashSet<>();

  @Override
  public void connectLocker(Locker newLocker) {
    this.controlledLockers.add(newLocker);
  }

  @Override
  public void disconnectLocker(Locker connectedLocker) {
    if (controlledLockers.contains(connectedLocker)) {
      this.controlledLockers.remove(connectedLocker);
    }
  }

  @Override
  public Ticket pickTicket(Object somethingToStore){
    Locker locker = findLockerHaveUnusedBox();
    if (locker != null) {
      Ticket ticket = locker.pickTicket();
      Box box = locker.findBoxById(ticket.getBoxId());
      box.putSomething(somethingToStore);
      box.close();
      return ticket;
    }
    return null;
  }

  @Override
  public Object pickPackage(Ticket ticket) {
    for (Locker controlledLocker : controlledLockers) {
      Box box = controlledLocker.openBox(ticket);
      if (box != null) {
        return box.popStoredThing();
      }
    }
    return null;
  }

  private Locker findLockerHaveUnusedBox() {
    return controlledLockers.stream().filter(Locker::haveUnusedBox).findFirst().orElse(null);
  }
}
