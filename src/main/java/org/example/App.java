package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.example.dao.SharesDao;
import org.example.dao.SharesDaoImpl;
import org.example.model.CommandBase;
import org.example.model.OperationType;
import org.example.service.impl.ParseServiceImpl;
import org.example.service.impl.ReaderServiceImpl;
import org.example.service.impl.WriterServiceImpl;
import org.example.strategy.ActionStrategy;
import org.example.strategy.ActionStrategyImpl;
import org.example.strategy.action.ActionHandler;
import org.example.strategy.action.OrderHandler;
import org.example.strategy.action.QueryHandler;
import org.example.strategy.action.UpdateHandler;

public class App {
    public static final String INPUT_FILE_NAME = "input.txt";
    public static final String OUTPUT_FILE_NAME = "output.txt";

    public static void main(String[] args) {
        List<String> outputList = new ArrayList<>();
        SharesDao sharesDao = new SharesDaoImpl();
        ActionStrategy actionStrategy = new ActionStrategyImpl(
                Map.of(OperationType.UPDATE, new UpdateHandler(),
                        OperationType.QUERY, new QueryHandler(),
                        OperationType.ORDER, new OrderHandler()));

        List<String> inputList = new ReaderServiceImpl().read(INPUT_FILE_NAME);
        List<CommandBase> parsedList = new ParseServiceImpl().parse(inputList);
        for (CommandBase record : parsedList) {
            ActionHandler actionHandler = actionStrategy.get(record.getOperation());
            String result = actionHandler.process(sharesDao, record);
            if (!result.isEmpty()) {
                outputList.add(result);
            }
        }
        new WriterServiceImpl().write(OUTPUT_FILE_NAME, outputList);
    }
}
