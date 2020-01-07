package com.wuhantoc.javasample;

import com.wuhantoc.javasample.Locker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.Ticket;

public class LockerTest {
    @Test
    void should_get_ticket_when_store_packages_given_not_empty_box() {
        // given
        Locker locker = new Locker(0);

        // when
        boolean successful = locker.store();

        // then
        Assertions.assertFalse(successful);
    }

    @Test
    void should_get_ticket_when_store_packages_given_have_empty_box() {
        // given
        Locker locker = new Locker(1);

        // when
        boolean successful = locker.store();

        // then
        Assertions.assertTrue(successful);
    }

    @Test
    void should_get_package_when_pick_packages_given_have_correct_ticket() {
        // given
        Ticket correctTicket = locker.store();

        // when
        boolean successful = locker.pick(correctTicket);

        // then
        Assertions.assertTrue(successful);
    }

}
