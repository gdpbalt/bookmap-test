package org.example.model;

import java.util.Map;
import org.example.exception.InputValidationException;

public class CommandOrder extends CommandBase {
    public static final Map<String, CommandOrderType> SUBCOMMANDS;
    private static final int INDEX_SUBCOMMAND = 1;
    private static final int INDEX_SIZE = 2;

    static {
        SUBCOMMANDS = Map.of(
                "buy", CommandOrderType.BUY,
                "sell", CommandOrderType.SELL);
    }

    private final CommandOrderType type;
    private final int size;

    private CommandOrder(OperationType command, CommandOrderType type, int size) {
        super(command);
        this.type = type;
        this.size = size;
    }

    public CommandOrderType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public static CommandOrder of(OperationType command, String[] fields) {
        if (!SUBCOMMANDS.containsKey(fields[INDEX_SUBCOMMAND])) {
            throw new InputValidationException("Error validation input fields");
        }
        return new CommandOrder(command,
                SUBCOMMANDS.get(fields[INDEX_SUBCOMMAND]),
                Integer.parseInt(fields[INDEX_SIZE]));
    }

    @Override
    public String toString() {
        return "CommandOrder{"
                + "command=" + getOperation()
                + ", type=" + type
                + ", size=" + size
                + '}';
    }
}
