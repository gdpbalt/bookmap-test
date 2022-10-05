package org.example.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.example.service.WriterService;

public class WriterServiceImpl implements WriterService {

    @Override
    public void write(String fileName, String report) {
        Path Directory = Path.of("").toAbsolutePath();
        try {
            Files.write(Directory.resolve(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error write report to file '%s'", fileName), e);
        }
    }
}
