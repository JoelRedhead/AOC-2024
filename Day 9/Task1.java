import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public long task1Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        String line = myReader.nextLine();

        List<Integer> idsList = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < line.charAt(i) - '0'; j++) {
                    idsList.add(i / 2);
                }
            }
            else {
                for (int j = 0; j < line.charAt(i) - '0'; j++) {
                    idsList.add(-1);
                }
            }
        }

        for (int i = idsList.size() - 1; i >= 0; i--) {
            if (idsList.get(i) != -1) {
                for (int j = 0; j < i; j++) {
                    if (idsList.get(j) == -1) {
                        idsList.set(j, idsList.get(i));
                        idsList.set(i, -1);
                        break;
                    }
                }
            }
        }
        long count = 0;
        for (int i = 0; i < idsList.size(); i++) {
            if (idsList.get(i) != -1) {
                count += idsList.get(i) * i;
            }
        }
        return count;
    }
}
