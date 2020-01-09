package com.wuhantoc.javasample.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {
    private final Box[] boxes;
    private final Map<Ticket, Box> ticketBoxRelation = new HashMap<>();

    private Locker(int size) {
        boxes = new Box[size];
        for (int j = 0; j < size; j++) {
            boxes[j] = new Box(j);
        }
    }

    public static Locker newFixedSizeLocker(int size) {
        return new Locker(size);
    }

    public Ticket pickTicket() {
        Box box = selectUsableBox();
        if (box != null) {
            Ticket ticket = new Ticket(UUID.randomUUID(), box.getId());
            ticketBoxRelation.put(ticket, box);
            box.open();
            return ticket;
        } else {
            return null;
        }
    }

    public Box openBox(Ticket ticket) {
        if (ticketBoxRelation.containsKey(ticket)) {
            final Box box = ticketBoxRelation.get(ticket);
            ticketBoxRelation.remove(ticket);
            box.open();
            return box;
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

    boolean haveUnusedBox() {
        return ticketBoxRelation.size() < boxes.length;
    }

    private boolean checkBoxUsability(Box box) {
        return ticketBoxRelation.containsValue(box);
    }

    private Box selectUsableBox() {
        for (Box box : boxes) {
            if (!checkBoxUsability(box)) {
                return box;
            }
        }
        return null;
    }
}
