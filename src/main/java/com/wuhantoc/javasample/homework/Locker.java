package com.wuhantoc.javasample.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Locker {
    private final List<Box> boxes;
    private final Map<Ticket, Box> ticketBoxRelation = new HashMap<>();

    private Locker(int size) {
        boxes = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            boxes.add(new Box(j));
        }
    }

    public static Locker newFixedSizeLocker(int size) {
        return new Locker(size);
    }

    public Ticket pickTicket() {
        Box box = selectUnusedBox();
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
        return boxes.stream().filter(box -> box.getId() == id).findFirst().orElse(null);
    }

    boolean haveUnusedBox() {
        return ticketBoxRelation.size() < boxes.size();
    }

    private boolean checkBoxUnuse(Box box) {
        return !ticketBoxRelation.containsValue(box);
    }

    private Box selectUnusedBox() {
        return boxes.stream().filter(this::checkBoxUnuse).findFirst().orElse(null);
    }
}
