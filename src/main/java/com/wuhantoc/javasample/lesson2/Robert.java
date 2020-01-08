package com.wuhantoc.javasample.lesson2;

import java.util.HashSet;
import java.util.Set;

public class Robert {
  private Set<Locker> underControlLockers = new HashSet<>();

  public void connectLocker(Locker newLocker) {
    this.underControlLockers.add(newLocker);
  }

  public void disconnectLocker(Locker connectedLocker) {
    if (underControlLockers.contains(connectedLocker)) {
      this.underControlLockers.remove(connectedLocker);
    }
  }

  public Ticket pickTicket(Object somethingToStore){
    Locker locker = findLockerHaveEmptyBox();
    if (locker != null) {
      Ticket ticket = locker.pickTicket();
      try {
        storePackageToBox();
        locker.closeBox(ticket.getBoxId());
      } catch (Exception e) {
        //todo: give the bag to customer
        e.printStackTrace();
        return null;
      }
      return ticket;
    }
    return null;
  }

  private void storePackageToBox() {
    //todo:
  }

  private Locker findLockerHaveEmptyBox() {
    for (Locker underControlLocker : underControlLockers) {
      if (underControlLocker.haveUnusedBox()) {
        return underControlLocker;
      }
    }
    return null;
  }

  public Object pickPackage(Ticket ticket) {
    for (Locker underControlLocker : underControlLockers) {
      Box box = underControlLocker.openBox(ticket);
      if (box != null && box.isOpen() && box.isUsed()) {
         return getPackageFromBox();
      }
    }
    return null;
  }

  private Object getPackageFromBox() {

    return null;
  }
}
