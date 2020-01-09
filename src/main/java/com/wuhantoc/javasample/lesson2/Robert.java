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
    Locker locker = findLockerHaveEmptyBox();
    if (locker != null) {
      Ticket ticket = locker.pickTicket();
      try {
        Box box = locker.findBoxById(ticket.getBoxId());
        box.setSomethingStored(somethingToStore);
        box.close();
      } catch (Exception e) {
        //todo: give the bag to customer
        e.printStackTrace();
        return null;
      }
      return ticket;
    }
    return null;
  }

  private Locker findLockerHaveEmptyBox() {
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
        return box.getSomethingStored();
      }
    }
    return null;
  }
}
