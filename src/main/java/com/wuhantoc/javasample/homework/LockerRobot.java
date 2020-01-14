package com.wuhantoc.javasample.homework;

public interface LockerRobot {
  void connectLocker(Locker newLocker);

  void disconnectLocker(Locker connectedLocker);

  Ticket pickTicket(Object somethingToStore);

  Object pickPackage(Ticket ticket);
}
