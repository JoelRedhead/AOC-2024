import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public long task2Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        String line = myReader.nextLine();

        List<idToLength> idsList = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) - '0' > 0) {
                if (i % 2 == 0) {
                    idsList.add(new idToLength(i / 2, line.charAt(i) - '0'));
                }
                else {
                    idsList.add(new idToLength(-1, line.charAt(i) - '0'));
                }
            }
        }

        HashSet<Long> set = new HashSet<>();
        for (int i = idsList.size() - 1; i >= 0; i--) {
            idToLength currI = idsList.get(i);
            if (set.contains(currI.id)) {
                continue;
            }
            if (currI.id != -1) {
                for (int j = 0; j < i; j++) {
                    idToLength currJ = idsList.get(j);
                    if (currJ.id == -1 && currJ.length >= currI.length) {
                        long diff = currJ.length - currI.length;
                        set.add(currI.id);
                        idToLength temp = new idToLength(-1, currI.length);
                        idsList.set(j, currI);
                        idsList.set(i, temp);
                        if (diff > 0) {
                            idsList.add(j + 1, new idToLength(-1, diff));
                            i++;
                        }
                        break;
                    }
                }
            }

        }
        long count = 0;
        int pos = 0;
        for (int i = 0; i < idsList.size(); i++) {
            if (idsList.get(i).id != -1) {
                for (int j = 0; j < idsList.get(i).length; j++) {
                    count += idsList.get(i).id * pos;
                    pos++;
                }
            }
            else {
                pos += idsList.get(i).length;
            }
        }
        return count;
    }
}

class idToLength {
    public long id;
    public long length;
    public idToLength(long id, long length) {
        this.id = id;
        this.length = length;
    }
}