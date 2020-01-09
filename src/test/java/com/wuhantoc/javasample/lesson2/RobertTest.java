package com.wuhantoc.javasample.lesson2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class RobertTest {
  @Test
  void should_success_when_store_package_given_empty_locker() {
    //given
    Robert robert = new Robert();
    robert.connectLocker(Locker.newFixedSizeLocker(1));
    Object somethingToStore = new Object();

    //when
    Ticket ticket = robert.pickTicket(somethingToStore);

    //then
    assertNotNull(ticket);
  }

  @Test
  void should_failed_when_store_given_all_full_locker() {
    //given
    Robert robert = new Robert();
    robert.connectLocker(Locker.newFixedSizeLocker(1));
    robert.connectLocker(Locker.newFixedSizeLocker(1));
    robert.pickTicket(new Object());
    robert.pickTicket(new Object());

    //when
    Ticket ticket = robert.pickTicket(new Object());

    //then
    assertNull(ticket);
  }

  @Test
  void should_success_when_pick_given_correct_ticket() {
    //given
    Robert robert = new Robert();
    robert.connectLocker(Locker.newFixedSizeLocker(1));
    final Object somethingToStore = new Object();
    Ticket ticket = robert.pickTicket(somethingToStore);

    //when
    Object returnPackage = robert.pickPackage(ticket);

    //then
    assertEquals(somethingToStore, returnPackage);
  }

  @Test
  void should_failed_when_pick_given_wrong_ticket() {
    //given
    Robert robert = new Robert();
    robert.connectLocker(Locker.newFixedSizeLocker(1));
    robert.pickTicket(new Object());
    Ticket wrongTicker = new Ticket(UUID.randomUUID(),0);

    //when
    Object returnPackage = robert.pickPackage(wrongTicker);

    //then
    assertNull(returnPackage);
  }


  @Test
  void should_store_to_locker1_when_store_given_empty_locker1_and_empty_locker2() {
    //given
    Robert robert = new Robert();
    final Locker locker1 = Locker.newFixedSizeLocker(1);
    robert.connectLocker(locker1);
    robert.connectLocker(Locker.newFixedSizeLocker(1));

    //when
    final Object somethingToStore = new Object();
    Ticket ticket = robert.pickTicket(somethingToStore);

    //then
    Box box = locker1.openBox(ticket);
    assertNotNull(box);
  }

  @Test
  void should_not_store_to_locker2_when_store_given_empty_locker1_and_empty_locker2() {
    //given
    Robert robert = new Robert();
    robert.connectLocker(Locker.newFixedSizeLocker(1));
    final Locker locker = Locker.newFixedSizeLocker(1);
    robert.connectLocker(locker);

    //when
    final Object somethingToStore = new Object();
    Ticket ticket = robert.pickTicket(somethingToStore);

    //then
    Box box = locker.openBox(ticket);
    assertNull(box);
  }
}
