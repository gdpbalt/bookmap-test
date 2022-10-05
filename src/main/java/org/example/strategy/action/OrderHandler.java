package org.example.strategy.action;

import java.util.Optional;
import org.example.dao.SharesDao;
import org.example.exception.InvalidCommandCastException;
import org.example.exception.OperationUnknownException;
import org.example.model.CommandBase;
import org.example.model.CommandOrder;
import org.example.model.Cost;
import org.example.model.Shares;
import org.example.model.SharesType;

public class OrderHandler implements ActionHandler {
    @Override
    public String process(SharesDao sharesDao, CommandBase command) {
        if (!(command instanceof CommandOrder)) {
            throw new InvalidCommandCastException("Error cast to CommandOrder class");
        }
        CommandOrder commandOrder = (CommandOrder) command;
        Optional<Cost> cost;
        //noinspection EnhancedSwitchMigration
        switch (commandOrder.getType()) {
            case BUY:
                cost = sharesDao.getMinPriceAndNotZeroSizeByType(SharesType.ASK);
                break;
            case SELL:
                cost = sharesDao.getMaxPriceAndNotZeroSizeByType(SharesType.BID);
                break;
            default:
                throw new OperationUnknownException("Unknown command update type: "
                        + commandOrder.getType());
        }
        if (cost.isEmpty() || cost.get().getSize() < commandOrder.getSize()) {
            throw new OperationUnknownException("In result we will get negative size");
        }
        Shares shares = sharesDao.getValue(cost.get().getPrice());
        shares.setSize(shares.getSize() - commandOrder.getSize());
        sharesDao.setValue(cost.get().getPrice(), shares);
        return "";
    }
}
