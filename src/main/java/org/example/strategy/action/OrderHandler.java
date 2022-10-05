package org.example.strategy.action;

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
        int size = commandOrder.getSize();
        while (size > 0) {
            Cost cost;
            //noinspection EnhancedSwitchMigration
            switch (commandOrder.getType()) {
                case BUY:
                    cost = sharesDao.getMinPriceAndNotZeroSizeByType(SharesType.ASK)
                            .orElseThrow(() -> new OperationUnknownException(
                                    "Not found the best price for BUY"));
                    break;
                case SELL:
                    cost = sharesDao.getMaxPriceAndNotZeroSizeByType(SharesType.BID)
                            .orElseThrow(() -> new OperationUnknownException(
                                    "Not found the best price for SELL"));
                    break;
                default:
                    throw new OperationUnknownException("Unknown command update type: "
                            + commandOrder.getType());
            }
            int diff;
            if (size > cost.getSize()) {
                diff = cost.getSize();
                size -= diff;
            } else {
                diff = size;
                size = 0;
            }
            Shares shares = sharesDao.getValue(cost.getPrice());
            shares.setSize(shares.getSize() - diff);
            sharesDao.setValue(cost.getPrice(), shares);
        }
        return "";
    }
}
