package org.keyin.StackControls;

import java.util.Map;


import java.util.Map;

public class Action {
    private String operation;
    private Long entityId; // Assuming entityId is of type Long
    private Object originalEntity; // Assuming originalEntity is of type Object

    public Action(String operation, Long entityId, Object originalEntity) {
        this.operation = operation;
        this.entityId = entityId;
        this.originalEntity = originalEntity;
    }

    public String getOperation() {
        return operation;
    }

    public Long getEntityId() {
        return entityId;
    }

    public Object getOriginalEntity() {
        return originalEntity;
    }
}
