package com.wuhantoc.javasample.lesson2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Locker {
    int size = 0;
    int emptyBoxesNum = 0;
    List<Ticket> validTickets = new ArrayList<>();
    Map<Ticket, Box> ticketBoxRelation = new HashMap<>();

    public Locker(int i) {
        size = i;
        emptyBoxesNum = size;
    }

    public Ticket store() {
        if (haveEmptyBox()) {
            --emptyBoxesNum;

            final Ticket ticket = new Ticket(UUID.randomUUID());
            validTickets.add(ticket);
            return ticket;
        } else {
            return null;
        }
    }

    public boolean pick(Ticket correctTicket) {
        if (validTickets.contains(correctTicket)) {
            emptyBoxesNum++;
            return validTickets.remove(correctTicket);
        } else {
            return false;
        }
    }

    private boolean haveEmptyBox() {
        return emptyBoxesNum >= 1;
    }
}
