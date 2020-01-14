package com.wuhantoc.javasample.homework;

public class Box {
  private boolean open = false;
  private final int id;
  private Bag somethingStored;

  public Box(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public boolean isOpen() {
    return open;
  }

  public void open() {
    this.open = true;
  }

  public void close() {
    this.open = false;
  }

  public void putSomething(Bag somethingToStore) {
    this.somethingStored = somethingToStore;
  }

  public Bag popStoredThing() {
    Bag storedThing = somethingStored;
    somethingStored = null;
    return storedThing;
  }
}
