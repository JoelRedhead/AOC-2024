import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Task2 {
    public long task2Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        long sumValid = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] parts = line.split(" ");
            Long total = Long.parseLong(parts[0].substring(0, parts[0].length() - 1));
            Stack<Long> numbers = new Stack<>();
            for (int i = parts.length - 1; i >= 1; i--) {
                numbers.add(Long.parseLong(parts[i]));
            }
            if (possible(total, numbers)) {
                sumValid += total;
            }
        }
        return sumValid;
    }

    public boolean possible(Long total, Stack<Long> numbers) {
        if (numbers.size() == 1) {
            if (numbers.peek().equals(total)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            Stack<Long> addStack = (Stack<Long>)numbers.clone();
            Long temp = addStack.pop();
            Long temp2 = addStack.pop();
            addStack.push(temp + temp2);

            Stack<Long> multiplyStack = (Stack<Long>)numbers.clone();
            Long temp3 = multiplyStack.pop();
            Long temp4 = multiplyStack.pop();
            multiplyStack.push(temp3 * temp4);

            Stack<Long> concatonateStack = (Stack<Long>)numbers.clone();
            Long temp5 = concatonateStack.pop();
            Long temp6 = concatonateStack.pop();
            concatonateStack.push(Long.parseLong(Long.toString(temp5) + Long.toString(temp6)));

            return possible(total, addStack) || possible(total, multiplyStack) || possible(total, concatonateStack);
        }
    }
}
