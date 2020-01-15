package com.wuhantoc.javasample.homework;

import static com.wuhantoc.javasample.homework.Locker.newFixedSizeLocker;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockerTest {
  @Test
  void should_failed_when_store_package_given_0_boxes_locker() {
    // given
    Locker locker = newFixedSizeLocker(0);

    // when
    Ticket ticket = locker.pickTicket();

    // then
    Assertions.assertNull(ticket);
  }

  @Test
  void should_get_ticket_when_store_package_given_have_empty_box_locker() {
    // given
    Locker locker = newFixedSizeLocker(1);

    // when
    Ticket ticket = locker.pickTicket();

    // then
    Assertions.assertNotNull(ticket);
  }

  @Test
  void should_failed_when_store_package_given_all_full_box_locker() {
    // given
    Locker locker = newFixedSizeLocker(2);
    locker.pickTicket();
    locker.pickTicket();

    //when
    Ticket ticket3 = locker.pickTicket();

    //then
    Assertions.assertNull(ticket3);
  }

  @Test
  void should_open_box_when_pick_given_correct_ticket() {
    // given
    Locker locker = newFixedSizeLocker(1);
    Ticket correctTicket = locker.pickTicket();

    // when
    Box openedBox = locker.openBox(correctTicket);

    // then
    Assertions.assertTrue(openedBox.isOpen());
  }

  @Test
  void should_get_correct_package_when_pick_given_correct_ticket() {
    // given
    Locker locker = newFixedSizeLocker(1);
    Ticket correctTicket = locker.pickTicket();
    Box box = locker.findBoxById(correctTicket.getBoxId());
    Bag somethinsToStore = new Bag();
    box.putSomething(somethinsToStore);

    // when
    Box openedBox = locker.openBox(correctTicket);

    // then
    Assertions.assertEquals(somethinsToStore, openedBox.popStoredThing());
  }

  @Test
  void should_failed_when_pick_given_wrong_ticket() {
    // given
    Locker locker = newFixedSizeLocker(1);
    locker.pickTicket();

    // when
    Ticket wrongTicket = new Ticket(UUID.randomUUID(), 0);
    Box nullBox = locker.openBox(wrongTicket);

    // then
    Assertions.assertNull(nullBox);
  }

  @Test
  void should_failed_when_pick_given_used_ticket() {
    //given
    Locker locker = newFixedSizeLocker(1);
    Ticket ticket = locker.pickTicket();
    locker.openBox(ticket);

    //when
    Box nullBox = locker.openBox(ticket);

    //then
    Assertions.assertNull(nullBox);
  }

  @Test
  void should_success_when_store_after_pick_given_locker() {
    //given
    Locker locker = newFixedSizeLocker(1);
    Ticket ticket = locker.pickTicket();
    locker.openBox(ticket);

    //when
    Ticket ticket1 = locker.pickTicket();

    //then
    Assertions.assertNotNull(ticket1);
  }

}
