package com.toDoList.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final Long resourceId;

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s not found with id: %d", resourceName, resourceId)); // Construct a meaningful message
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
