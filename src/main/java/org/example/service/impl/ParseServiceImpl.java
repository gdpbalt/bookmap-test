package org.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.example.exception.InputValidationException;
import org.example.model.CommandBase;
import org.example.model.CommandOrder;
import org.example.model.CommandQuery;
import org.example.model.OperationType;
import org.example.model.CommandUpdate;
import org.example.service.ParseService;

public class ParseServiceImpl implements ParseService {
    private static final int OPERATION_INDEX = 0;

    @Override
    public List<CommandBase> parse(List<String> data) {
        List<CommandBase> outputList = new ArrayList<>();
        for (String record : data) {
            if (record.isEmpty()) {
                continue;
            }
            String[] fields = record.trim().split("\\s*,\\s*");
            OperationType operation = getOperation(fields[OPERATION_INDEX]);
            outputList.add(strategyCreate(operation, fields));
        }
        return outputList;
    }

    private OperationType getOperation(String command) {
        if (CommandBase.COMMANDS.containsKey(command)) {
            return CommandBase.COMMANDS.get(command);
        }
        throw new InputValidationException("Can't find known command in input record");
    }

    private CommandBase strategyCreate(OperationType command, String[] fields) {
        try {
            return switch (command) {
                case UPDATE -> CommandUpdate.of(command, fields);
                case QUERY -> CommandQuery.of(command, fields);
                case ORDER -> CommandOrder.of(command, fields);
            };
        } catch (RuntimeException e) {
            throw new InputValidationException("Something go wrong. " + e);
        }
    }
}
