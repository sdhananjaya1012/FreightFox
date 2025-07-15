package com.example.dispatch.enumeration;
public enum OrderPriority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String label;

    OrderPriority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

