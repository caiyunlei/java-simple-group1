package com.wuhantoc.javasample.lesson2;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockerTest {
    @Test
    void should_failed_when_store_package_given_0_boxes_locker() {
        // given
        Locker locker = new Locker(0);

        // when
        Ticket ticket = locker.store();

        // then
        Assertions.assertNull(ticket);
    }

    @Test
    void should_get_ticket_when_store_package_given_have_empty_box_locker() {
        // given
        Locker locker = new Locker(1);

        // when
        Ticket ticket = locker.store();

        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_failed_when_store_package_given_all_full_box_locker() {
        // given
        Locker locker = new Locker(2);
        locker.store();
        locker.store();

        //when
        Ticket ticket3 = locker.store();

        //then
        Assertions.assertNull(ticket3);
    }

    @Test
    void should_get_package_when_pick_given_correct_ticket() {
        // given
        Locker locker = new Locker(1);
        Ticket correctTicket = locker.store();

        // when
        boolean successful = locker.pick(correctTicket);

        // then
        Assertions.assertTrue(successful);
    }

    @Test
    void should_failed_when_pick_given_wrong_ticket() {
        // given
        Locker locker = new Locker(1);
        locker.store();

        // when
        Ticket wrongTicket = new Ticket(UUID.randomUUID());
        boolean pickResult = locker.pick(wrongTicket);

        // then
        Assertions.assertFalse(pickResult);
    }

    @Test
    void should_failed_when_pick_given_used_ticket() {
        //given
        Locker locker = new Locker(1);
        Ticket ticket = locker.store();
        locker.pick(ticket);

        //when
        boolean repeatPickResult = locker.pick(ticket);

        //then
        Assertions.assertFalse(repeatPickResult);
    }

    @Test
    void should_success_when_store_after_pick_given_locker() {
        //given
        Locker locker = new Locker(1);
        Ticket ticket = locker.store();
        locker.pick(ticket);

        //when
        Ticket ticket1 = locker.store();

        //then
        Assertions.assertNotNull(ticket1);
    }

}
