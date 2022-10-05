package org.example.service;

import java.util.List;
import org.example.model.CommandBase;

public interface ParseService {
    List<CommandBase> parse(List<String> data);
}
