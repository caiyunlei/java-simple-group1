package com.wuhantoc.javasample;

public class Locker {
    int boxes = 0;
    int emptyBoxes = 0;

    public Locker(int i) {
        boxes = i;
        emptyBoxes = boxes;
    }

    public boolean store() {
        if (havaEmptyBox()) {
            --emptyBoxes;
            return true;
        } else {
            return false;
        }
    }

    private boolean havaEmptyBox() {
        return emptyBoxes >= 1;
    }
}
