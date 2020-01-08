package com.wuhantoc.javasample.lesson2;

public class Box {
  private boolean open = false;
  private boolean used = false;

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
}
