package org.example.model;

import java.util.Map;
import org.example.exception.InputValidationException;

public class CommandQuery extends CommandBase {
    public static final Map<String, CommandQueryType> SUBCOMMANDS;
    private static final int INDEX_SUBCOMMAND = 1;
    private static final int INDEX_PRICE = 2;

    static {
        SUBCOMMANDS = Map.of(
                "best_bid", CommandQueryType.BEST_BID,
                "best_ask", CommandQueryType.BEST_ASK,
                "size", CommandQueryType.SIZE);
    }

    private final CommandQueryType type;
    private final Integer price;

    private CommandQuery(OperationType command, CommandQueryType type, Integer price) {
        super(command);
        this.type = type;
        this.price = price;
    }

    public CommandQueryType getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public static CommandQuery of(OperationType command, String[] fields) {
        if (!SUBCOMMANDS.containsKey(fields[INDEX_SUBCOMMAND])) {
            throw new InputValidationException("Error validation input fields");
        }
        CommandQueryType subcommand = SUBCOMMANDS.get(fields[INDEX_SUBCOMMAND]);
        Integer price = null;
        if (subcommand == CommandQueryType.SIZE) {
            price = Integer.parseInt(fields[INDEX_PRICE]);
        }
        return new CommandQuery(command, subcommand, price);
    }

    @Override
    public String toString() {
        return "CommandQuery{"
                + "command=" + getOperation()
                + ", type=" + type
                + ", price=" + price
                + '}';
    }
}
