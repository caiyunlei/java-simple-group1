package com.wuhantoc.javasample.lesson2;

import java.util.ArrayList;
import java.util.List;

public class Locker {
    int boxes = 0;
    int emptyBoxes = 0;
    List<Ticket> validTickets = new ArrayList<>();

    public Locker(int i) {
        boxes = i;
        emptyBoxes = boxes;
    }

    public Ticket store() {
        if (havaEmptyBox()) {
            --emptyBoxes;

            final Ticket ticket = new Ticket();
            validTickets.add(ticket);
            return ticket;
        } else {
            return null;
        }
    }

    private boolean havaEmptyBox() {
        return emptyBoxes >= 1;
    }

    public boolean pick(Ticket correctTicket) {
        return validTickets.contains(correctTicket);
    }
}
