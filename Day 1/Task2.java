import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {

        HashMap<Integer, Integer> rightFreqs = new HashMap<>();
        List<Integer> left = new ArrayList<>();
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (!data.isEmpty()) {
                String[] splitData = data.split("\\s+");
                left.add(Integer.parseInt(splitData[0]));
                if (rightFreqs.containsKey(Integer.parseInt(splitData[1]))) {
                    rightFreqs.put(Integer.parseInt(splitData[1]), rightFreqs.get(Integer.parseInt(splitData[1])) + 1);
                }
                else {
                    rightFreqs.put(Integer.parseInt(splitData[1]), 1);
                }
            }
        }
        int result = 0;
        for (Integer val : left) {
            if (rightFreqs.containsKey(val)) {
                result += val * rightFreqs.get(val);
            }
        }
        return result;
    }
}
