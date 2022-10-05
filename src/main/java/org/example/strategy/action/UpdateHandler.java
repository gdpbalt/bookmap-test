package org.example.strategy.action;

import org.example.dao.SharesDao;
import org.example.exception.InvalidCommandCastException;
import org.example.exception.OperationUnknownException;
import org.example.model.CommandBase;
import org.example.model.CommandUpdate;
import org.example.model.Shares;
import org.example.model.SharesType;

public class UpdateHandler implements ActionHandler {
    @Override
    public String process(SharesDao sharesDao, CommandBase command) {
        if (!(command instanceof CommandUpdate)) {
            throw new InvalidCommandCastException("Error cast to CommandUpdate class");
        }
        CommandUpdate commandUpdate = (CommandUpdate) command;
        SharesType sharesType;
        //noinspection EnhancedSwitchMigration
        switch (commandUpdate.getType()) {
            case BID:
                sharesType = SharesType.BID;
                break;
            case ASK:
                sharesType = SharesType.ASK;
                break;
            default:
                throw new OperationUnknownException("Unknown command update type: "
                        + commandUpdate.getType());
        }
        sharesDao.setValue(commandUpdate.getPrice(),
                new Shares(commandUpdate.getSize(), sharesType));
        return "";
    }
}
