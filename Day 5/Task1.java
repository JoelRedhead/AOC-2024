import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    public int task1Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> tasks = new ArrayList<>();
        Scanner myReader = new Scanner(myObj);
        HashMap<Integer, HashSet<Integer>> valuesAfter = new HashMap<>();
        boolean separated = false;
        while (myReader.hasNextLine()) {
            if (rules.size() > 0 && rules.get(rules.size() - 1).isBlank()) {
                separated = true;
                rules.remove(rules.size() - 1);
            }
            if (!separated) {
                rules.add(myReader.nextLine());
            }
            else {
                tasks.add(myReader.nextLine());
            }
        }
        for (int i = 0; i < rules.size(); i++) {
            String[] rule = rules.get(i).split("\\|");
            int before = Integer.parseInt(rule[0]);
            int after = Integer.parseInt(rule[1]);
            if (!valuesAfter.containsKey(before)) {
                HashSet<Integer> temp = new HashSet<>();
                temp.add(after);
                valuesAfter.put(before, temp);
            }
            else {
                HashSet<Integer> temp = valuesAfter.get(before);
                temp.add(after);
                valuesAfter.put(before, temp);
            }
        }
        int ans = 0;
        for (int i = 0; i < tasks.size(); i++) {
            boolean okay = true;
            String[] update = tasks.get(i).split(",");
            for (int k = 0; k < update.length; k++) {
                if (valuesAfter.containsKey(Integer.parseInt(update[k]))) {
                    HashSet<Integer> currentAfter = valuesAfter.get(Integer.parseInt(update[k]));
                    for (int j = 0; j < k; j++) {
                        if (currentAfter.contains(Integer.parseInt(update[j]))) {
                            okay = false;
                        }
                    }
                }
            }
            if (okay) {
                ans+= Integer.parseInt(update[update.length / 2]);
            }
        }
        return ans;
    }
}
