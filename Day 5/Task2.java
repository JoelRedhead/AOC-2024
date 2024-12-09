import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task2 {
    public int task2Ans() throws FileNotFoundException {
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
            } else {
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
            } else {
                HashSet<Integer> temp = valuesAfter.get(before);
                temp.add(after);
                valuesAfter.put(before, temp);
            }
        }
        List<String[]> invalid = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            boolean okay = true;
            String[] update = tasks.get(i).split(",");
            for (int j = 0; j < update.length; j++) {
                if (valuesAfter.containsKey(Integer.parseInt(update[j]))) {
                    HashSet<Integer> currentAfter = valuesAfter.get(Integer.parseInt(update[j]));
                    for (int k = 0; k < j; k++) {
                        if (currentAfter.contains(Integer.parseInt(update[k]))) {
                            okay = false;
                        }
                    }
                }
            }
            if (!okay) {
                invalid.add(tasks.get(i).split(","));
            }
        }

        int ans = 0;
        for (int i = 0; i < invalid.size(); i++) {
            boolean swapped = true;
            while (swapped) {
                swapped = false;
                String[] update = invalid.get(i);
                for (int j = 0; j < update.length; j++) {
                    if (valuesAfter.containsKey(Integer.parseInt(update[j]))) {
                        HashSet<Integer> currentAfter = valuesAfter.get(Integer.parseInt(update[j]));
                        for (int k = 0; k < j; k++) {
                            if (currentAfter.contains(Integer.parseInt(update[k]))) {
                                String temp = update[k];
                                update[k] = update[j];
                                update[j] = temp;
                                swapped = true;
                            }
                        }
                    }
                }
                invalid.set(i, update);
            }
            ans+= Integer.parseInt(invalid.get(i)[invalid.get(i).length / 2]);

        }

        return ans;
    }
}
