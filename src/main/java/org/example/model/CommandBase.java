package org.example.model;

import java.util.Map;

public abstract class CommandBase {
    public static final Map<String, OperationType> COMMANDS;

    static {
        COMMANDS = Map.of("u", OperationType.UPDATE,
                "q", OperationType.QUERY,
                "o", OperationType.ORDER);
    }

    private final OperationType operation;

    public CommandBase(OperationType operation) {
        this.operation = operation;
    }

    public OperationType getOperation() {
        return operation;
    }
}
