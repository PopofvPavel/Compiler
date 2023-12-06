package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Symbol {
    private final Map<Integer, List<String>> row = new HashMap<>();

    public Symbol(int index, List<String> information) {
        row.put(index, information);
    }

    public Map<Integer, List<String>> getRow() {
        return row;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<String>> entry : row.entrySet()) {
            result.append(entry.getKey()).append(": ").append(String.join(", ", entry.getValue())).append("\n");
        }
        return result.toString();
    }
}
