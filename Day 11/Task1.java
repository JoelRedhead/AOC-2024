import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public long task1Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        String line = myReader.nextLine();
        String[] numbersStr = line.split(" ");
        long result = 0;
        HashMap<String, Long> map = new HashMap<>();
        for (int i = 0; i < numbersStr.length; i++) {
            result += count(75, Long.parseLong(numbersStr[i]), map); // task 2 is basically the same as task 1 but this is changed to 25 in task 1
        }
        return result;
    }

    public long count(int blinks, Long number, HashMap<String, Long> map) {
        String val = number + ", " + blinks;
        if (map.containsKey(val)) {
            return map.get(val);
        }
        if (blinks == 0) {
            return 1;
        }

        if (number == 0) {
            map.put(val, count(blinks - 1, 1L, map));
            return map.get(val);
        }
        else if (String.valueOf(number).length() % 2 == 0) {
            String currentNumber = String.valueOf(number);
            Long firstHalf = Long.parseLong(currentNumber.substring(0, currentNumber.length() / 2));
            Long secondHalf = Long.parseLong(currentNumber.substring(currentNumber.length() / 2));
            map.put(val, count(blinks - 1, firstHalf, map) + count(blinks - 1, secondHalf, map));
            return map.get(val);
        }
        else {
            map.put(val, count(blinks - 1, number * 2024, map));
            return map.get(val);
        }
    }
}
