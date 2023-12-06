package com.example;

import java.util.ArrayList;
import java.util.List;

public class LexicAnalyzer {

    private final List<Token> tokens;

    private final List<String> symbols;

    public List<Token> getTokens() {
        return tokens;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    LexicAnalyzer() {
        tokens = new ArrayList<>();
        symbols = new ArrayList<>();
    }


    public boolean isInIdentifierList(String id) {
        for (String symbol : symbols) {
            if (symbol.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<String> splitStringIntoParts(String dataString) {
        List<String> result = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : dataString.toCharArray()) {
            if (isDelimiter(c)) {
                if (currentToken.length() > 0) {
                    result.add(currentToken.toString());
                    currentToken.setLength(0); // Очищаем StringBuilder
                }
                result.add(String.valueOf(c));
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            result.add(currentToken.toString());
        }

        return result;
    }

    private boolean isDelimiter(char c) {
        return c == '(' || c == ')' || c == '*' || c == '/' || c == '-' || c == '+' || c == ' ' || c == '\n';
    }

    public void analyze(String inputLine) {

        int posCounter = 0;
        List<String> dataList = splitStringIntoParts(inputLine);
        List<String> filteredList = new ArrayList<>();

        for (String stringPart : dataList) {
            if (!stringPart.equals(" ") && !stringPart.equals("\n")) {
                filteredList.add(stringPart);
            }
        }

        for (String stringPart : filteredList) {
            if (tryParseDelimiter(stringPart)) {
                tokens.add(new Token(parseDelimiter(stringPart)));
            } else if (stringPart.matches("^[0-9.]+")) {
                try {
                    constantHandler(stringPart);
                } catch (NumberFormatException e) {
                    System.out.println("Лексическая ошибка! Неправильно задана константа "
                            + stringPart + " на позиции " + (posCounter + 1));
                }
            } else {
                identifierHandler(stringPart, posCounter);
            }
            posCounter += stringPart.length();
        }
    }

    private void constantHandler(String string) throws NumberFormatException {
        try {
            tokens.add(new Token(Lexeme.INT_CONST, Integer.parseInt(string)));
        } catch (NumberFormatException e) {
            tokens.add(new Token(Lexeme.FLOAT_CONST, Double.parseDouble(string)));
        }
    }

    private void identifierHandler(String string, int posCounter) {
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (!(Character.isAlphabetic(currentChar) || currentChar == '_' || Character.isDigit(currentChar))) {
                System.out.println("Лексическая ошибка! Недопустимый символ "
                        + currentChar + " на позиции " + (posCounter + i + 1));
                return;
            }
        }

        if (!Character.isDigit(string.charAt(0))) {
            if (!isInIdentifierList(string)) {
                symbols.add(string);
            }
            tokens.add(new Token(Lexeme.ID, symbols.indexOf(string)));
        } else {
            System.out.println("Лексическая ошибка! Идентификатор " + string
                    + " не может начинаться с цифры на позиции " + (posCounter + 1));
        }
    }


    private Lexeme parseDelimiter(String operator) {
        return switch (operator) {
            case "(" -> Lexeme.LEFT_BRACKET;
            case ")" -> Lexeme.RIGHT_BRACKET;
            case "*" -> Lexeme.MULTIPLICATION_SIGN;
            case "/" -> Lexeme.DIVISION_SIGN;
            case "-" -> Lexeme.MINUS;
            case "+" -> Lexeme.PlUS;
            default -> null;
        };
    }

    private boolean tryParseDelimiter(String operator) {
        return parseDelimiter(operator) != null;
    }

}
