import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        HashMap<Character, List<List<Integer>>> map = new HashMap<>();
        int iPos = 0;
        List<List<HashSet<Character>>> newMap = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            List<HashSet<Character>> newMapRow = new ArrayList<>();
            for (int j = 0; j < line.length(); j++) {
                newMapRow.add(new HashSet<>());
                if (line.charAt(j) != '.') {
                    if (!map.containsKey(line.charAt(j))) {
                        List<List<Integer>> lists = new ArrayList<>();
                        List<Integer> pos = new ArrayList<Integer>();
                        pos.add(iPos);
                        pos.add(j);
                        lists.add(pos);
                        map.put(line.charAt(j), lists);
                    }
                    else {
                        List<List<Integer>> lists = map.get(line.charAt(j));
                        List<Integer> pos = new ArrayList<>();
                        pos.add(iPos);
                        pos.add(j);
                        lists.add(pos);
                        map.put(line.charAt(j), lists);
                    }
                }
            }
            newMap.add(newMapRow);
            iPos++;
        }

        System.out.println(map);
        for (Character currentChar : map.keySet()) {
            List<List<Integer>> lists = map.get(currentChar);
            if (lists.size() == 1) {
                continue;
            }
            for (int i = 1; i < lists.size(); i++) {
                for (int j = 0; j < i; j++) {
                    int iDiff = lists.get(i).get(0) - lists.get(j).get(0);
                    int jDiff = lists.get(i).get(1) - lists.get(j).get(1);
                    int multi = 0;
                    while (lists.get(i).get(0) + (multi * iDiff) >= 0 && lists.get(i).get(0) + (multi * iDiff) < newMap.size() && lists.get(i).get(1) + (multi * jDiff) >= 0 && lists.get(i).get(1) + (multi * jDiff) < newMap.get(0).size()) {
                        HashSet<Character> newiPos = newMap.get(lists.get(i).get(0) + (multi * iDiff)).get(lists.get(i).get(1) + (multi * jDiff));
                        newiPos.add(currentChar);
                        newMap.get(lists.get(i).get(0) + (multi * iDiff)).set(lists.get(i).get(1) + (multi * jDiff), newiPos);
                        multi++;
                    }
                    multi = 0;
                    while (lists.get(j).get(0) - (multi * iDiff) >= 0 && lists.get(j).get(0) - (multi * iDiff) < newMap.size() && lists.get(j).get(1) - (multi * jDiff) >= 0 && lists.get(j).get(1) - (multi * jDiff) < newMap.get(0).size()) {
                        HashSet<Character> newjPos = newMap.get(lists.get(j).get(0) - (multi * iDiff)).get(lists.get(j).get(1) - (multi * jDiff));
                        newjPos.add(currentChar);
                        newMap.get(lists.get(j).get(0) - (multi * iDiff)).set(lists.get(j).get(1) - (multi * jDiff), newjPos);
                        multi++;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < newMap.size(); i++) {
            for (int j = 0; j < newMap.get(i).size(); j++) {
                HashSet<Character> curr = newMap.get(i).get(j);
                if (curr.size() > 0) {
                    count ++;
                }

            }
        }
        return count;
    }
}
