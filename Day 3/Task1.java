import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public int task1Ans() throws FileNotFoundException {
        int finalScore = 0;
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String currLine = myReader.nextLine();
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            Matcher matcher = pattern.matcher(currLine);
            List<String> matched = new ArrayList<>();
            while (matcher.find()) {
                matched.add(matcher.group());
            }
            System.out.println(matched);
            for (int i = 0; i < matched.size(); i++) {
                //System.out.println(matched.get(i));
                String[] numbers = matched.get(i).split("mul|,|\\(|\\)| ");
                //System.out.println(Arrays.toString(numbers));
                finalScore += Integer.parseInt(numbers[2]) * Integer.parseInt(numbers[3]);
            }

        }
        return finalScore;
    }
}
