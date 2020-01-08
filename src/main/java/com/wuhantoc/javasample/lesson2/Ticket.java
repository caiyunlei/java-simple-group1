package com.wuhantoc.javasample.lesson2;

import java.util.UUID;

public class Ticket {
  private final UUID uuid;
  private final int boxId;

  public Ticket(UUID uuid, int boxId) {
    this.uuid = uuid;
    this.boxId = boxId;
  }

  public int getBoxId() {
    return boxId;
  }

  public UUID getUuid() {
    return uuid;
  }
}
