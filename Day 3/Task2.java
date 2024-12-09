import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {
        int finalScore = 0;
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        boolean canMatch = true;
        while (myReader.hasNextLine()) {
            String currLine = myReader.nextLine();
            Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\))|(don't\\(\\))|do\\(\\)");
            Matcher matcher = pattern.matcher(currLine);
            List<String> matched = new ArrayList<>();
            while (matcher.find()) {
                matched.add(matcher.group());
            }
            for (int i = 0; i < matched.size(); i++) {
                if (matched.get(i).equals("do()")) {
                    canMatch = true;
                }
                else if (matched.get(i).equals("don't()")) {
                    canMatch = false;
                }
                else {
                    String[] numbers = matched.get(i).split("mul|,|\\(|\\)| ");
                    if (canMatch) {
                        finalScore += Integer.parseInt(numbers[2]) * Integer.parseInt(numbers[3]);
                    }
                }
            }
        }
        return finalScore;
    }
}
