package org.example.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.example.service.WriterService;

public class WriterServiceImpl implements WriterService {
    private final Path filePath;
    private boolean isFirstLine;

    public WriterServiceImpl(Path filePath) {
        this.filePath = filePath;
        this.isFirstLine = true;
    }

    @Override
    public void write(String outputLine) {
        if (outputLine == null || outputLine.isEmpty()) {
            return;
        }
        try {
            if (isFirstLine) {
                Files.writeString(filePath, outputLine + "\n");
                isFirstLine = false;
            } else {
                Files.writeString(filePath, outputLine + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error write report to file " + filePath, e);
        }
    }
}
