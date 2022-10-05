package org.example.strategy.action;

import org.example.dao.SharesDao;
import org.example.exception.InvalidCommandCastException;
import org.example.exception.OperationUnknownException;
import org.example.model.CommandBase;
import org.example.model.CommandOrder;

public class OrderHandler implements ActionHandler {
    @Override
    public String process(SharesDao sharesDao, CommandBase command) {
        if (!(command instanceof CommandOrder)) {
            throw new InvalidCommandCastException("Error cast to CommandOrder class");
        }
        CommandOrder commandOrder = (CommandOrder) command;
        switch (commandOrder.getType()) {
            case BUY:
//                sharesType = SharesType.ASK;
                break;
            case SELL:
//                sharesType = SharesType.BID;
                break;
            default:
                throw new OperationUnknownException("Unknown command order type: " +
                        commandOrder.getType());
        }
        return "";
    }
}
