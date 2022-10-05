package org.example.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.example.service.WriterService;

public class WriterServiceImpl implements WriterService {
    @Override
    public void write(String fileName, List<String> report) {
        try {
            Files.write(Path.of(fileName), report, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("Error write report to file " + fileName, e);
        }
    }
}
