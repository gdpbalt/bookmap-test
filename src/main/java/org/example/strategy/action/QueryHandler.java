package org.example.strategy.action;

import org.example.dao.SharesDao;
import org.example.exception.InputValidationException;
import org.example.exception.InvalidCommandCastException;
import org.example.exception.OperationUnknownException;
import org.example.model.CommandBase;
import org.example.model.CommandQuery;
import org.example.model.SharesType;

public class QueryHandler implements ActionHandler {
    @Override
    public String process(SharesDao sharesDao, CommandBase command) {
        if (!(command instanceof CommandQuery)) {
            throw new InvalidCommandCastException("Error cast to CommandQuery class");
        }
        CommandQuery commandQuery = (CommandQuery) command;
        String result;
        switch (commandQuery.getType()) {
            case BEST_BID:
                result = sharesDao.getMaxPriceAndNotZeroSizeByType(SharesType.BID);
                break;
            case BEST_ASK:
                result = sharesDao.getMinPriceAndNotZeroSizeByType(SharesType.ASK);
                break;
            case SIZE:
                result = sharesDao.getByPrice(commandQuery.getPrice());
                break;
            default:
                throw new OperationUnknownException("Unknown command query type: " +
                        commandQuery.getType());
        }
        return result;
    }
}
