package com.wuhantoc.javasample.lesson2;

import java.util.LinkedHashSet;
import java.util.Set;

public class Robert {
  private Set<Locker> controlledLockers = new LinkedHashSet<>();

  public void connectLocker(Locker newLocker) {
    this.controlledLockers.add(newLocker);
  }

  public void disconnectLocker(Locker connectedLocker) {
    if (controlledLockers.contains(connectedLocker)) {
      this.controlledLockers.remove(connectedLocker);
    }
  }

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

  private Locker findLockerHaveUnusedBox() {
    for (Locker controlledLocker : controlledLockers) {
      if (controlledLocker.haveUnusedBox()) {
        return controlledLocker;
      }
    }
    return null;
  }

  public Object pickPackage(Ticket ticket) {
    for (Locker controlledLocker : controlledLockers) {
      Box box = controlledLocker.openBox(ticket);
      if (box != null) {
        return box.popStoredThing();
      }
    }
    return null;
  }
}
