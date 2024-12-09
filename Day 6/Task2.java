import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {
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
        int amount = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                List<List<Integer>> newMap = map.stream()
                        .map(innerList -> new ArrayList<>(innerList))
                        .collect(Collectors.toList());

                if (i == iPos && j == jPos) {
                    continue;
                }
                newMap.get(i).set(j, 1);
                if (uniqueSquares(newMap, iPos, jPos) == -1) {
                    amount++;
                }
            }
        }
        return amount;
    }

    public int uniqueSquares (List<List<Integer>> map, int iPos, int jPos) {
        List<List<HashSet<Integer>>> directionedMap = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            List<HashSet<Integer>> currRow = new ArrayList<>();
            for (int j = 0; j < map.get(i).size(); j++) {
                HashSet<Integer> set = new HashSet<>();
                set.add(map.get(i).get(j));
                currRow.add(set);
            }
            directionedMap.add(currRow);
        }
        //System.out.println(directionedMap);
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int currDirection = 0;
        while (iPos >= 0 && iPos < directionedMap.size() && jPos >= 0 && jPos < directionedMap.get(0).size()) {
            if (iPos + directions[currDirection][0] < 0 || iPos + directions[currDirection][0] >= directionedMap.size() || jPos + directions[currDirection][1] < 0 || jPos + directions[currDirection][1] >= directionedMap.get(0).size()) {
                return 0;
            }
            if (directionedMap.get(iPos).get(jPos).contains(2) && currDirection == 0) {
                return -1;
            }
            else if (directionedMap.get(iPos).get(jPos).contains(3) && currDirection == 1) {
                return -1;
            }
            else if (directionedMap.get(iPos).get(jPos).contains(4) && currDirection == 2) {
                return -1;
            }
            else if (directionedMap.get(iPos).get(jPos).contains(5) && currDirection == 3) {
                return -1;
            }

            if (currDirection == 0) {
                HashSet<Integer> temp = directionedMap.get(iPos).get(jPos);
                temp.add(2);
                directionedMap.get(iPos).set(jPos, temp);
            }
            else if (currDirection == 1) {
                HashSet<Integer>  temp = directionedMap.get(iPos).get(jPos);
                temp.add(3);
                directionedMap.get(iPos).set(jPos, temp);
            }
            else if (currDirection == 2) {
                HashSet<Integer>  temp = directionedMap.get(iPos).get(jPos);
                temp.add(4);
                directionedMap.get(iPos).set(jPos, temp);
            }
            else if (currDirection == 3) {
                HashSet<Integer>  temp = directionedMap.get(iPos).get(jPos);
                temp.add(5);
                directionedMap.get(iPos).set(jPos, temp);
            }
            if (directionedMap.get(iPos + directions[currDirection][0]).get(jPos + directions[currDirection][1]).contains(1)) {
                currDirection = (currDirection + 1) % 4;
            }
            else {
                iPos += directions[currDirection][0];
                jPos += directions[currDirection][1];
            }
        }
        return 0;
    }
}

