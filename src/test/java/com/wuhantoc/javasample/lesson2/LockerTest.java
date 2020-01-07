package com.wuhantoc.javasample.lesson2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockerTest {
    @Test
    void should_get_ticket_when_store_packages_given_not_empty_box() {
        // given
        Locker locker = new Locker(0);

        // when
        Ticket ticket = locker.store();

        // then
        Assertions.assertNull(ticket);
    }

    @Test
    void should_get_ticket_when_store_packages_given_all_full_box() {
        // given
        Locker locker = new Locker(2);

        Ticket ticket1 = locker.store();
        Assertions.assertNotNull(ticket1);

        Ticket ticket2 = locker.store();
        Assertions.assertNotNull(ticket2);

        Ticket ticket3 = locker.store();
        Assertions.assertNull(ticket3);
    }

    @Test
    void should_get_ticket_when_store_packages_given_have_empty_box() {
        // given
        Locker locker = new Locker(1);

        // when
        Ticket ticket = locker.store();

        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_get_package_when_pick_packages_given_have_correct_ticket() {
        // given
        Locker locker = new Locker(1);
        Ticket correctTicket = locker.store();

        // when
        boolean successful = locker.pick(correctTicket);

        // then
        Assertions.assertTrue(successful);
    }

    @Test
    void should_get_package_when_pick_packages_given_have_wrong_ticket() {
        // given
        Locker locker = new Locker(1);
        locker.store();

        // when
        Ticket wrongTicket = new Ticket();
        boolean successful = locker.pick(wrongTicket);

        // then
        Assertions.assertFalse(successful);
    }

}
