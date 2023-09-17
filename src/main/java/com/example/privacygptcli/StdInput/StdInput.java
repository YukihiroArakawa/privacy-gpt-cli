package com.example.privacygptcli.StdInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StdInput {
    private String line;

    public StdInput(Scanner scanner) {
        this.line = scanner.nextLine();
    }

    public String getLine() {
        return line;
    }

    public boolean hasExitStatus() {
        return line.equalsIgnoreCase(InputStatus.EXIT.getCode());
    }

    public boolean hasGoStatus() {
        return line.equalsIgnoreCase(InputStatus.GO.getCode());
    }

    public boolean hasProhibitedWord() {
        if (line.isEmpty()) return false;

        List<String> prohibitedWords = new ArrayList<>();
        prohibitedWords.add("xxx corp");

        return prohibitedWords.stream().anyMatch(line::contains);
    }
}
