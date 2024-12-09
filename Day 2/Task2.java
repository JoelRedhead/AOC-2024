import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        int safe = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (!data.isEmpty()) {
                String[] splitData = data.split("\\s+");
                if (isSafe(splitData) == 1) {
                    safe++;
                }
                else {
                    for (int i = 0; i < splitData.length; i++) {
                        String[] tempArr = new String[splitData.length - 1];
                        String[] arr1 = Arrays.copyOfRange(splitData, 0, i);
                        String[] arr2 = Arrays.copyOfRange(splitData, i + 1, splitData.length);
                        System.arraycopy(arr1, 0, tempArr, 0, arr1.length);
                        System.arraycopy(arr2, 0, tempArr, arr1.length, arr2.length);
                        if (isSafe(tempArr) == 1) {
                            safe++;
                            break;
                        }
                    }
                }

            }
        }
        return safe;
    }

    public int isSafe(String[] currLine) {
        int safe = 0;
        boolean inc;
        if (Integer.parseInt(currLine[0]) > Integer.parseInt(currLine[1])) {
            inc = true;
        }
        else {
            inc = false;
        }
        boolean currSafe = true;
        for (int i = 0; i < currLine.length - 1; i++) {
            if (inc && Integer.parseInt(currLine[i]) <= Integer.parseInt(currLine[i+1]) || !inc && Integer.parseInt(currLine[i]) >= Integer.parseInt(currLine[i+1])) {
                currSafe = false;
                break;
            }
            if (Math.abs(Integer.parseInt(currLine[i]) - Integer.parseInt(currLine[i+1])) > 3) {
                currSafe = false;
                break;
            }
        }
        if (currSafe) {return 1;}
        return 0;
    }
}
