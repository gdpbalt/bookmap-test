package org.example.model;

import java.util.Map;
import org.example.exception.InputValidationException;

public class CommandUpdate extends CommandBase {
    public static final Map<String, CommandUpdateType> SUBCOMMANDS;
    private static final int INDEX_PRICE = 1;
    private static final int INDEX_SIZE = 2;
    private static final int INDEX_SUBCOMMAND = 3;

    static {
        SUBCOMMANDS = Map.of(
                "bid", CommandUpdateType.BID,
                "ask", CommandUpdateType.ASK);
    }

    private final CommandUpdateType type;
    private final int price;
    private final int size;

    private CommandUpdate(OperationType command, int price, int size, CommandUpdateType type) {
        super(command);
        this.price = price;
        this.size = size;
        this.type = type;
    }

    public CommandUpdateType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public static CommandUpdate of(OperationType command, String[] fields) {
        if (!SUBCOMMANDS.containsKey(fields[INDEX_SUBCOMMAND])) {
            throw new InputValidationException("Error validation input fields");
        }
        return new CommandUpdate(command,
                Integer.parseInt(fields[INDEX_PRICE]),
                Integer.parseInt(fields[INDEX_SIZE]),
                SUBCOMMANDS.get(fields[INDEX_SUBCOMMAND]));
    }

    @Override
    public String toString() {
        return "CommandUpdate{"
                + "command=" + getOperation()
                + ", price=" + price
                + ", size=" + size
                + ", type=" + type
                + '}';
    }
}
