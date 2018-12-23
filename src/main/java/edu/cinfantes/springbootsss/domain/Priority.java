package edu.cinfantes.springbootsss.domain;

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
}
