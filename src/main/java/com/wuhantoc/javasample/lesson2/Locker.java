package com.wuhantoc.javasample.lesson2;

public class Locker {
    int boxes = 0;
    int emptyBoxes = 0;

    public Locker(int i) {
        boxes = i;
        emptyBoxes = boxes;
    }

    public Ticket store() {
        if (havaEmptyBox()) {
            --emptyBoxes;
            return new Ticket();
        } else {
            return null;
        }
    }

    private boolean havaEmptyBox() {
        return emptyBoxes >= 1;
    }

    public boolean pick(Ticket correctTicket) {
        return false;
    }
}
