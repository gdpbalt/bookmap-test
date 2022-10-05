package org.example.strategy.action;

import java.util.Optional;
import org.example.dao.SharesDao;
import org.example.exception.InputValidationException;
import org.example.exception.InvalidCommandCastException;
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
                Optional<Cost> maxCost = sharesDao.getMaxPriceAndNotZeroSizeByType(SharesType.BID);
                return maxCost.isPresent() ? convertCost2String(maxCost.get()) : "";
            case BEST_ASK:
                Optional<Cost> minCost = sharesDao.getMinPriceAndNotZeroSizeByType(SharesType.ASK);
                return minCost.isPresent() ? convertCost2String(minCost.get()) : "";
            case SIZE:
                Optional<Integer> size = sharesDao.getSizeByPrice(commandQuery.getPrice());
                return size.isPresent() ? convertSize2String(size.get()) : "";
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
