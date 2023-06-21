package org.keyin.StackControls;

public class Action {
    private String operation;
    private Long entityId;
    private Object originalEntity;

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
