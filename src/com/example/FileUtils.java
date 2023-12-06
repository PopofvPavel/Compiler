package com.example;

import java.io.*;
import java.util.List;

public class FileUtils {
    public static void writeStringToFile(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String FileToString(String inputFilename){
        StringBuilder resString = new StringBuilder(new String());
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename)))
            {
            String line;
            while ((line = reader.readLine()) != null) {
                resString.append(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка с файлом: ");
        }
        return resString.toString();
    }

    public static void writeListOfTokensToFile(List<Token> tokenList, List<String>  symbolList, String fileName) {
        StringBuilder sb = new StringBuilder();
        tokenList.forEach(item -> {
            sb.append(item);
            if (item.getName() == Lexeme.ID) {
                sb.append(" ").append(symbolList.get((Integer) item.getAttribute())); // опять Object
            }
            sb.append("\n");
        });
        writeStringToFile(sb.toString(), fileName);
    }

    public static void writeListOfTokensToFile(SymbolTable symbolTable,String fileName) {
        writeStringToFile(symbolTable.toString(),fileName);
    }

    public static void writeListOfSymbolsToFile(List<String>  symbolsList, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < symbolsList.size(); i++) {
            sb.append(i + 1).append(" - ").append(symbolsList.get(i)).append("\n");
        }
        writeStringToFile(sb.toString(), fileName);
    }

}
