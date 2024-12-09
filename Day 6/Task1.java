import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public int task1Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        List<List<Integer>> map = new ArrayList<>();
        Scanner myReader = new Scanner(myObj);
        int starti = -1;
        int startj = -1;
        int iPos = 0;
        int jPos = 0;
        int iCount = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '.') {
                    currRow.add(0);
                }
                else if (line.charAt(j) == '#') {
                    currRow.add(1);
                }
                else {
                    currRow.add(0);
                    iPos = iCount;
                    jPos = j;
                }
            }
            map.add(currRow);
            iCount++;
        }
        int squareCount = 0;

        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int currDirection = 0;
        while (iPos >= 0 && iPos < map.size() && jPos >= 0 && jPos < map.get(0).size()) {
            //System.out.println(map.get(iPos).get(jPos));
            if (map.get(iPos).get(jPos) == 0) {
                squareCount++;
            }
            if (iPos + directions[currDirection][0] < 0 || iPos + directions[currDirection][0] >= map.size() || jPos + directions[currDirection][1] < 0 || jPos + directions[currDirection][1] >= map.get(0).size()) {
                break;
            }
            if (map.get(iPos + directions[currDirection][0]).get(jPos + directions[currDirection][1]) == 1) {
                map.get(iPos).set(jPos, 3);
                currDirection = (currDirection + 1) % 4;
            }
            else {
                map.get(iPos).set(jPos, 3);
                iPos += directions[currDirection][0];
                jPos += directions[currDirection][1];
            }

        }
        return squareCount;
    }
}
