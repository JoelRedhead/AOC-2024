import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task1 {
    public int task1Ans() throws FileNotFoundException {
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();

        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (!data.isEmpty()) {
                String[] splitData = data.split("\\s+");
                left.add(Integer.parseInt(splitData[0]));
                right.add(Integer.parseInt(splitData[1]));
            }
        }
        int result = 0;
        while (!left.isEmpty()) {
            result += Math.abs(left.poll() - right.poll());
        }
        return result;
    }
}
