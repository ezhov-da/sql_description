package ru.progtools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author deonisius
 */
public class FileFullReader {

    private final String pathToFile;

    public FileFullReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String read() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(pathToFile), "UTF-8");) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

    }
}
