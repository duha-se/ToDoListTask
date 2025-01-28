package com.toDoList.exception;

public class Violation {
    private String attribute;
    private String cause;

    public Violation(String attribute, String cause) {
        this.attribute = attribute;
        this.cause = cause;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
