package com.wuhantoc.javasample.homework.robot;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Ticket;

public interface LockerRobot {

  Ticket pickTicket(Bag somethingToStore);

  Bag pickPackage(Ticket ticket);
}
