package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;

public interface LockerRobot {
  void connectLocker(Locker newLocker);

  void disconnectLocker(Locker connectedLocker);

  Ticket pickTicket(Object somethingToStore);

  Object pickPackage(Ticket ticket);
}
