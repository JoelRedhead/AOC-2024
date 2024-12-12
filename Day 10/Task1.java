import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public int task1Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        List<String> map = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            map.add(data);
        }

        int count = 0;
        for (int i = 0; i < map.size(); i++) {
            String s = map.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') {
                    HashSet<String> visited = new HashSet<>();
                    visited.addAll(dfs(map, i, j, "0"));
                    count += visited.size();
                }
            }
        }
        return count;
    }

    public List<String> dfs(List<String> map, int i, int j, String current) {
        if (current.equals("0123456789")) {
            String res = i + ", " + j;
            List<String> list = new ArrayList<>();
            list.add(res);
            return list;
        }
        List<String> list = new ArrayList<>();
        if (i + 1 < map.size()) {
            if (map.get(i + 1).charAt(j) - current.charAt(current.length() - 1) == 1) {
                list.addAll(dfs(map, i + 1, j, current + map.get(i + 1).charAt(j)));
            }
        }
        if (i - 1 >= 0) {
            if (map.get(i - 1).charAt(j) - current.charAt(current.length() - 1) == 1) {
                list.addAll(dfs(map, i - 1, j, current + map.get(i - 1).charAt(j)));
            }
        }
        if (j + 1 < map.get(0).length()) {
            if (map.get(i).charAt(j + 1) - current.charAt(current.length() - 1) == 1) {
                list.addAll(dfs(map, i, j + 1, current + map.get(i).charAt(j + 1)));
            }
        }
        if (j - 1 >= 0) {
            if (map.get(i).charAt(j - 1) - current.charAt(current.length() - 1) == 1) {
                list.addAll(dfs(map, i, j - 1, current + map.get(i).charAt(j - 1)));
            }
        }
        return list;
    }
}
