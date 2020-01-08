package com.wuhantoc.javasample.lesson2;

import java.util.UUID;

public class Ticket {
  private final UUID uuid;

  public Ticket(UUID uuid) {
    this.uuid = uuid;
  }

  public UUID getUuid() {
    return uuid;
  }
}
