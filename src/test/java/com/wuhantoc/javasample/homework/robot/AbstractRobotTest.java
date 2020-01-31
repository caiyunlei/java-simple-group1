package com.wuhantoc.javasample.homework.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.wuhantoc.javasample.homework.Bag;
import com.wuhantoc.javasample.homework.Box;
import com.wuhantoc.javasample.homework.Locker;
import com.wuhantoc.javasample.homework.Ticket;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AbstractRobotTest {
  @Test
  void should_failed_when_store_package_given_none_locker() {
    //given
    AbstractRobot abstractRobot = Mockito.spy(AbstractRobot.class);
    Bag somethingToStore = new Bag();

    //when
    Ticket ticket = abstractRobot.pickTicket(somethingToStore);

    //then
    assertNull(ticket);
  }

  @Test
  void should_success_when_store_package_given_empty_locker() {
    //given
    final Locker givenLocker = Locker.newFixedSizeLocker(1);
    AbstractRobot abstractRobot = Mockito.spy(AbstractRobot.class);
    abstractRobot.connectLocker(givenLocker);
    Mockito.when(abstractRobot.findLockerToSave()).thenReturn(givenLocker);

    Bag somethingToStore = new Bag();

    //when
    Ticket ticket = abstractRobot.pickTicket(somethingToStore);

    //then
    Box openedBox = givenLocker.openBox(ticket);
    Bag returnBag = openedBox.popStoredThing();
    assertEquals(somethingToStore,returnBag);
  }

  @Test
  void should_success_when_pick_given_correct_ticket() {
    //given
    final Locker givenLocker = Locker.newFixedSizeLocker(1);
    AbstractRobot abstractRobot = Mockito.spy(AbstractRobot.class);
    abstractRobot.connectLocker(givenLocker);
    Mockito.when(abstractRobot.findLockerToSave()).thenReturn(givenLocker);

    Bag somethingToStore = new Bag();
    Ticket ticket = abstractRobot.pickTicket(somethingToStore);

    //when
    Bag returnBag = abstractRobot.pickPackage(ticket);


    //then
    assertEquals(somethingToStore,returnBag);
  }

  @Test
  void should_failed_when_pick_given_wrong_ticket() {
    //given
    final Locker givenLocker = Locker.newFixedSizeLocker(1);
    AbstractRobot abstractRobot = Mockito.spy(AbstractRobot.class);
    abstractRobot.connectLocker(givenLocker);
    Mockito.when(abstractRobot.findLockerToSave()).thenReturn(givenLocker);

    Ticket wrongTicker = Mockito.mock(Ticket.class);

    //when
    Object returnPackage = abstractRobot.pickPackage(wrongTicker);

    //then
    assertNull(returnPackage);
  }
}
