package com.wuhantoc.javasample.lesson2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {
    private Box[] boxes;
    private Map<Ticket, Box> ticketBoxRelation = new HashMap<>();

    private Locker(int i) {
        boxes = new Box[i];
        for (int j = 0; j < i; j++) {
            boxes[j] = new Box();
        }
    }

    public static Locker allocateFixedSizeLocker(int i) {
        return new Locker(i);
    }

    public Ticket pickTicket() {
        if (haveUnusedBox()) {
            Ticket ticket = new Ticket(UUID.randomUUID());
            Box box = selectUnusedBox();
            ticketBoxRelation.put(ticket, box);
            box.setUsed(true);
            box.open();
            return ticket;
        } else {
            return null;
        }
    }

    public void closeBox(Box box) {
        box.close();
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

    private boolean haveUnusedBox() {
        int unusedBoxesNum = 0;
        for (Box box : boxes) {
            if (!box.isUsed()) {
                unusedBoxesNum++;
            }
        }
        return unusedBoxesNum >= 1;
    }
}
