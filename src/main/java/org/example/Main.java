package org.example;

import java.util.List;
import java.util.Map;
import org.example.dao.SharesDao;
import org.example.dao.SharesDaoImpl;
import org.example.model.CommandBase;
import org.example.model.OperationType;
import org.example.service.ParseService;
import org.example.service.ReaderService;
import org.example.service.impl.ParseServiceImpl;
import org.example.service.impl.ReaderServiceImpl;
import org.example.strategy.ActionStrategy;
import org.example.strategy.ActionStrategyImpl;
import org.example.strategy.action.ActionHandler;
import org.example.strategy.action.QueryHandler;
import org.example.strategy.action.UpdateHandler;

public class Main {
    public static final String INPUT_FILE_NAME = "input.txt";
    public static final String OUTPUT_FILE_NAME = "output.txt";

    public static void main(String[] args) {
        ReaderService readerService = new ReaderServiceImpl();
        List<String> inputList = readerService.read(INPUT_FILE_NAME);

        ParseService parseService = new ParseServiceImpl();
        List<CommandBase> parsedList = parseService.parse(inputList);
        SharesDao sharesDao = new SharesDaoImpl();
        ActionStrategy actionStrategy = new ActionStrategyImpl(
                Map.of(OperationType.UPDATE, new UpdateHandler(),
                        OperationType.QUERY, new QueryHandler(),
                        OperationType.ORDER, new UpdateHandler()));
        for (CommandBase record : parsedList) {
            ActionHandler actionHandler = actionStrategy.get(record.getOperation());
            String result = actionHandler.process(sharesDao, record);
            System.out.printf("%s - %s\n", record, result);
        }
    }
}
