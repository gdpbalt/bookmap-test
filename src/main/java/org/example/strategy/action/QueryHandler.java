package org.example.strategy.action;

import org.example.dao.SharesDao;
import org.example.exception.InputValidationException;
import org.example.exception.InvalidCommandCastException;
import org.example.exception.OperationUnknownException;
import org.example.model.CommandBase;
import org.example.model.CommandQuery;
import org.example.model.Cost;
import org.example.model.SharesType;

public class QueryHandler implements ActionHandler {
    @Override
    public String process(SharesDao sharesDao, CommandBase command) {
        if (!(command instanceof CommandQuery)) {
            throw new InvalidCommandCastException("Error cast to CommandQuery class");
        }
        CommandQuery commandQuery = (CommandQuery) command;
        //noinspection EnhancedSwitchMigration
        switch (commandQuery.getType()) {
            case BEST_BID:
                return convertCost2String(sharesDao.getMaxPriceAndNotZeroSizeByType(SharesType.BID)
                        .orElseThrow(() -> new OperationUnknownException(
                                "Not found the best BEST_BID price")));
            case BEST_ASK:
                return convertCost2String(sharesDao.getMinPriceAndNotZeroSizeByType(SharesType.ASK)
                        .orElseThrow(() -> new OperationUnknownException(
                                "Not found the best BEST_ASK price")));
            case SIZE:
                return convertSize2String(sharesDao.getSizeByPrice(commandQuery.getPrice()));
            default:
                throw new InputValidationException("Unknown command " + commandQuery.getType());
        }
    }

    private String convertCost2String(Cost input) {
        return String.format("%d,%d", input.getPrice(), input.getSize());
    }

    private String convertSize2String(Integer input) {
        return String.valueOf(input);
    }
}
