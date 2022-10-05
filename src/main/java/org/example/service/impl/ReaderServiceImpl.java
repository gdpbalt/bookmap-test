package org.example.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.example.service.ReaderService;

public class ReaderServiceImpl implements ReaderService {
    @Override
    public List<String> read(String fileName) {
        Path Directory = Path.of("").toAbsolutePath();
        try {
            return Files.readAllLines(Directory.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error read data from file '%s'", fileName), e);
        }
    }
}
