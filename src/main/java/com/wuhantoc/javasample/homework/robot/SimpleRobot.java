package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Box;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;

public class SimpleRobot extends AbstractRobot implements LockerRobot {

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