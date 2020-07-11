package com.info.opsramp;
import java.util.List;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class StringCalculator {
    public int calculate(String input) {
        List<String> negatives = new ArrayList<String>();
        // check the empty Strings
        if (input == null || input.isEmpty()) {
            return 0;
        }
        
        char customDelimiter = getCustomDelimiter(input.split("\n")[0]);

        String[] chuncks = (customDelimiter == ',') ? 
                                input.split(",|\n") : input.split("\n|" + customDelimiter);
        int total = 0;
        int skipFirstLines = (customDelimiter == ',') ? 0 : 2;
        for (String item : chuncks) {
            if (skipFirstLines > 0) {
                --skipFirstLines;
            } else {
                int num = Integer.parseInt(item);
                if (num < 0) {
                    negatives.add(item);
                }
                total += num;
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                "negatives not allowed " + String.join(",", negatives));
        }
        return total;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private char getCustomDelimiter(String line) {
        if (line == null || line.isEmpty()) {
            return ',';
        }
        if (isNumeric(line)) {
            return ',';
        }
        if (line.length() == 1) {
            return line.charAt(0);
        }
        return ',';
    }
}

