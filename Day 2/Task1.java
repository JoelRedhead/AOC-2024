import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {
    public int task1Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        int safe = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (!data.isEmpty()) {
                String[] splitData = data.split("\\s+");
                boolean inc;
                if (Integer.parseInt(splitData[0]) > Integer.parseInt(splitData[1])) {
                    inc = true;
                }
                else {
                    inc = false;
                }
                boolean currSafe = true;
                for (int i = 0; i < splitData.length - 1; i++) {
                    if (inc && Integer.parseInt(splitData[i]) <= Integer.parseInt(splitData[i+1]) || !inc && Integer.parseInt(splitData[i]) >= Integer.parseInt(splitData[i+1])) {
                        currSafe = false;
                        break;
                    }
                    if (Math.abs(Integer.parseInt(splitData[i]) - Integer.parseInt(splitData[i+1])) > 3) {
                        currSafe = false;
                        break;
                    }
                }
                if (currSafe) {safe++;}
            }
        }
        return safe;
    }
}
