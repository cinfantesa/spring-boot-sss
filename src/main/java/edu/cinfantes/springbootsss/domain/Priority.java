package edu.cinfantes.springbootsss.domain;

import edu.cinfantes.springbootsss.domain.exception.PriorityNotFoundException;

import java.util.Arrays;

public enum Priority {
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final int value;

  Priority(int V) {
    value = V;
  }

  public int getValue() {
    return value;
  }

  public static Priority fromValue(int v) {
    return Arrays.stream(Priority.values())
      .filter(p -> p.getValue() == v)
      .findFirst()
      .orElseThrow(PriorityNotFoundException::new);
  }
}
