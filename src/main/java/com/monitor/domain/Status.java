package com.monitor.domain;

public enum Status {
    RED("RED"),
    AMBER("AMBER"),
    GREEN("GREEN"),
    GREY("GREY"),
    BLUE("BLUE");
    private final String statusString;

    Status(String statusString) {
        this.statusString = statusString;
    }

    public String toString() {
        return this.statusString;
    }
}
