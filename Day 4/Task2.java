import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        ArrayList<String> input = new ArrayList<>();
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            input.add(myReader.nextLine());
        }
        int ans = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'M') {
                    if (isPresent(i - 1, j - 1, input, 'A') && isPresent(i - 2, j - 2, input, 'S')) {
                        if (isPresent(i - 2, j, input, 'M') && isPresent(i, j - 2, input, 'S')) {
                            ans++;
                        }
                        if (isPresent(i - 2, j, input, 'S') && isPresent(i, j - 2, input, 'M')) {
                            ans++;
                        }
                    }
                    if (isPresent(i + 1, j + 1, input, 'A') && isPresent(i + 2, j + 2, input, 'S')) {
                        if (isPresent(i + 2, j, input, 'M') && isPresent(i, j + 2, input, 'S')) {
                            ans++;
                        }
                        if (isPresent(i + 2, j, input, 'S') && isPresent(i, j + 2, input, 'M')) {
                            ans++;
                        }
                    }
                    if (isPresent(i + 1, j - 1, input, 'A') && isPresent(i + 2, j - 2, input, 'S')) {
                        if (isPresent(i + 2, j, input, 'M') && isPresent(i, j - 2, input, 'S')) {
                            ans++;
                        }
                        if (isPresent(i + 2, j, input, 'S') && isPresent(i, j - 2, input, 'M')) {
                            ans++;
                        }
                    }

                    if (isPresent(i - 1, j + 1, input, 'A') && isPresent(i - 2, j + 2, input, 'S')) {
                        if (isPresent(i - 2, j, input, 'M') && isPresent(i, j + 2, input, 'S')) {
                            ans++;
                        }
                        if (isPresent(i - 2, j, input, 'S') && isPresent(i, j + 2, input, 'M')) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans / 2;
    }

    public boolean isPresent(int row, int column, ArrayList<String> input, char toCheck) {
        if (row < 0 || column < 0 || row >= input.size() || column >= input.get(0).length()) {
            return false;
        }
        else if (input.get(row).charAt(column) == toCheck) {
            return true;
        }
        else {
            return false;
        }
    }
}
