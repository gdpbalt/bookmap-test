package org.example.strategy;

import org.example.model.OperationType;
import org.example.strategy.action.ActionHandler;

public interface ActionStrategy {

    ActionHandler get(OperationType operation);
}
