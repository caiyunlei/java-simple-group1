package com.wuhantoc.javasample.lesson2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {
    private int id;
    private Box[] boxes;
    private Map<Ticket, Box> ticketBoxRelation = new HashMap<>();

    private Locker(int i) {
        boxes = new Box[i];
        for (int j = 0; j < i; j++) {
            boxes[j] = new Box(j);
        }
    }

    public static Locker newFixedSizeLocker(int i) {
        return new Locker(i);
    }

    public Ticket pickTicket() {
        if (haveUnusedBox()) {
            Box box = selectUnusedBox();
            Ticket ticket = new Ticket(UUID.randomUUID(), box.getId());
            ticketBoxRelation.put(ticket, box);
            box.setUsed(true);
            box.open();
            return ticket;
        } else {
            return null;
        }
    }

    Box findBoxById(int id) {
        for (Box box : boxes) {
            if (box.getId() == id) {
                return box;
            }
        }
        return null;
    }

    private Box selectUnusedBox() {
        for (Box box : boxes) {
            if (!box.isUsed()) {
                return box;
            }
        }
        return null;
    }

    public Box openBox(Ticket ticket) {
        if (ticketBoxRelation.containsKey(ticket)) {
            final Box box = ticketBoxRelation.get(ticket);
            ticketBoxRelation.remove(ticket);
            box.setUsed(false);
            box.open();
            return box;
        } else {
            return null;
        }
    }

    boolean haveUnusedBox() {
        int unusedBoxesNum = 0;
        for (Box box : boxes) {
            if (!box.isUsed()) {
                unusedBoxesNum++;
            }
        }
        return unusedBoxesNum >= 1;
    }
}
