import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public long task1Ans () throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        List<Long[]> buttonA  = new ArrayList<>();
        List<Long[]> buttonB  = new ArrayList<>();
        List<Long[]> prize  = new ArrayList<>();

        int section = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.isBlank()) {
                section = 0;
                continue;
            }
            else {
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);
                Long[] values = new Long[2];
                matcher.find();
                values[0] = Long.parseLong(matcher.group());
                matcher.find();
                values[1] = Long.parseLong(matcher.group());
                if (section == 0) {
                    buttonA.add(values);
                }
                else if (section == 1) {
                    buttonB.add(values);
                }
                else if (section == 2) {
                    values[0] += 10000000000000L;
                    values[1] += 10000000000000L;
                    prize.add(values);
                }
                section++;
            }
        }
        long count = 0;
        for (int i = 0; i < buttonA.size(); i++) {
            long a = buttonA.get(i)[0];
            long b = buttonB.get(i)[0];
            long c = buttonA.get(i)[1];
            long d = buttonB.get(i)[1];
            long x = prize.get(i)[0];
            long y = prize.get(i)[1];
            if (a * d - b * c != 0) {
                double det = a * d - b * c;
                double outputA = (d * x - b * y) / det;
                double outputB = (a * y - c * x) / det;
                if ((long) outputA >= outputA - 0.0005 && (long) outputA <= outputA + 0.0005 && (long) outputB >= outputB - 0.0005 && (long) outputB <= outputB + 0.0005) {
                    count += 3 * (long) outputA + (long) outputB;
                }
            }
        }
        return count;
    }
}
