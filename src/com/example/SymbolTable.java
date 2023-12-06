package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SymbolTable {

    List<Symbol> table = new ArrayList<>();

    public SymbolTable(List<String> rows) {
        int index = 1;
        for (String row : rows) {
            table.add(new Symbol(index++, Collections.singletonList(row)));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Symbol symbol : table) {
            result.append(symbol.toString());
        }
        return result.toString();
    }
}
