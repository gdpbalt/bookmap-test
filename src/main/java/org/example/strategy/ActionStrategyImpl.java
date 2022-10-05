package org.example.strategy;

import java.util.Map;
import org.example.exception.OperationUnknownException;
import org.example.model.OperationType;
import org.example.strategy.action.ActionHandler;

public class ActionStrategyImpl implements ActionStrategy {
    private final Map<OperationType, ActionHandler> actionHandlerMap;

    public ActionStrategyImpl(Map<OperationType, ActionHandler> actionHandlerMap) {
        this.actionHandlerMap = actionHandlerMap;
    }

    @Override
    public ActionHandler get(OperationType operation) {
        ActionHandler actionHandler = actionHandlerMap.get(operation);
        if (actionHandler == null) {
            throw new OperationUnknownException("Unknown operation " + operation);
        }
        return actionHandler;
    }
}
