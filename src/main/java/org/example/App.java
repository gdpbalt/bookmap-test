package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.example.dao.SharesDao;
import org.example.dao.SharesDaoImpl;
import org.example.model.CommandBase;
import org.example.model.OperationType;
import org.example.service.ParseService;
import org.example.service.WriterService;
import org.example.service.impl.ParseServiceImpl;
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
        Path outputPath = Path.of(OUTPUT_FILE_NAME);
        try {
            Files.deleteIfExists(outputPath);
            Files.createFile(outputPath);
        } catch (IOException e) {
            throw new RuntimeException("Error create output file " + OUTPUT_FILE_NAME, e);
        }
        WriterService writerService = new WriterServiceImpl(outputPath);

        SharesDao sharesDao = new SharesDaoImpl();
        ParseService parseService = new ParseServiceImpl();
        ActionStrategy actionStrategy = new ActionStrategyImpl(
                Map.of(OperationType.UPDATE, new UpdateHandler(),
                        OperationType.QUERY, new QueryHandler(),
                        OperationType.ORDER, new OrderHandler()));

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                if (inputLine.isEmpty()) {
                    continue;
                }
                CommandBase parsedData = parseService.parse(inputLine);
                ActionHandler actionHandler = actionStrategy.get(parsedData.getOperation());
                writerService.write(actionHandler.process(sharesDao, parsedData));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error read data from file " + INPUT_FILE_NAME, e);
        }
    }
}
