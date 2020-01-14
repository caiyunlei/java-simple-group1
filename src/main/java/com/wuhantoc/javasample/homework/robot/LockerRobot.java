package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Ticket;

public interface LockerRobot {

  Ticket pickTicket(Object somethingToStore);

  Object pickPackage(Ticket ticket);
}
