package org.example.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.example.service.ReaderService;

public class ReaderServiceImpl implements ReaderService {
    @Override
    public List<String> read(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error read data from file '%s'", fileName), e);
        }
    }
}
