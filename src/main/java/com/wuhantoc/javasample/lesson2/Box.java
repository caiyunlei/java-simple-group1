package com.wuhantoc.javasample.lesson2;

public class Box {
  private boolean open = false;
  private boolean used = false;
  private final int id;
  private Object somethingStored;

  public Box(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public boolean isUsed() {
    return used;
  }

  public void setUsed(boolean used) {
    this.used = used;
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

  public void setSomethingStored(Object somethingToStore) {
    this.somethingStored = somethingToStore;
  }

  public Object getSomethingStored() {
    return somethingStored;
  }
}
