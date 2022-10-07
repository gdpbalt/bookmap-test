package org.example.service.impl;

import java.util.regex.Pattern;
import org.example.exception.InputValidationException;
import org.example.model.CommandBase;
import org.example.model.CommandOrder;
import org.example.model.CommandQuery;
import org.example.model.CommandUpdate;
import org.example.model.OperationType;
import org.example.service.ParseService;

public class ParseServiceImpl implements ParseService {
    private static final int OPERATION_INDEX = 0;
    private final Pattern splitPattern = Pattern.compile("\\s*,\\s*");

    @Override
    public CommandBase parse(String data) {
        String[] fields = splitPattern.split(data);
        return commandFactory(getOperation(fields[OPERATION_INDEX]), fields);
    }

    private OperationType getOperation(String command) {
        if (CommandBase.COMMANDS.containsKey(command)) {
            return CommandBase.COMMANDS.get(command);
        }
        throw new InputValidationException("Can't find known command in input record");
    }

    private CommandBase commandFactory(OperationType command, String[] fields) {
        try {
            //noinspection EnhancedSwitchMigration
            switch (command) {
                case UPDATE:
                    return CommandUpdate.of(command, fields);
                case QUERY:
                    return CommandQuery.of(command, fields);
                case ORDER:
                    return CommandOrder.of(command, fields);
                default:
                    throw new InputValidationException("Unknown command " + command);
            }
        } catch (RuntimeException e) {
            throw new InputValidationException("Something go wrong. " + e);
        }
    }
}
