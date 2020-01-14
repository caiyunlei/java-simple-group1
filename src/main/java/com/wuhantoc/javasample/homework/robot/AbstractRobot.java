package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Box;
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
  public Ticket pickTicket(Bag somethingToStore){
    Locker locker = findLockerToSave();
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
  public Bag pickPackage(Ticket ticket) {
    for (Locker controlledLocker : controlledLockers) {
      Box box = controlledLocker.openBox(ticket);
      if (box != null) {
        return box.popStoredThing();
      }
    }
    return null;
  }

  protected abstract Locker findLockerToSave();
}
