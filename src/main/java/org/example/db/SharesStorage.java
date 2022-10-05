package org.example.db;

import java.util.Map;
import java.util.TreeMap;
import org.example.model.Shares;

public class SharesStorage {
    public static final Map<Integer, Shares> shares = new TreeMap<>();
}
