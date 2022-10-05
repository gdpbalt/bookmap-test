package org.example.strategy.action;

import org.example.dao.SharesDao;
import org.example.model.CommandBase;

public interface ActionHandler {
    String process(SharesDao sharesDao, CommandBase command);
}
