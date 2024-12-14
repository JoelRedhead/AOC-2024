import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public long task2Ans() throws FileNotFoundException {
        File myObj = new File("input.txt");
        Scanner myReader = new Scanner(myObj);
        List<RobotInfo> allRobots = new ArrayList<RobotInfo>();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            Pattern pattern = Pattern.compile("-?\\d+");
            Matcher matcher = pattern.matcher(line);
            RobotInfo robotInfo = new RobotInfo();
            matcher.find();
            robotInfo.xPos = Integer.parseInt(matcher.group());
            matcher.find();
            robotInfo.yPos = Integer.parseInt(matcher.group());
            matcher.find();
            robotInfo.xVel = Integer.parseInt(matcher.group());
            matcher.find();
            robotInfo.yVel = Integer.parseInt(matcher.group());
            allRobots.add(robotInfo);
        }


        int lowest = 0;
        long lowestVar = Long.MAX_VALUE;
        for (int i = 0; i < 11000; i++) {
            long xVar = 0;
            long yVar = 0;
            long topLeft = 0;
            long topRight = 0;
            long bottomLeft = 0;
            long bottomRight = 0;

            for (RobotInfo robotInfo : allRobots) {
                xVar += (Math.floorMod(robotInfo.xPos + (i * robotInfo.xVel), 101) - 50) * (Math.floorMod(robotInfo.xPos + (i * robotInfo.xVel), 101) - 50);
                yVar += (Math.floorMod(robotInfo.yPos + (i * robotInfo.yVel), 103) - 51) * (Math.floorMod(robotInfo.yPos + (i * robotInfo.yVel), 103) - 51);
            }
            if (xVar * yVar < lowestVar) {
                lowestVar = xVar * yVar;
                lowest = i;
            }
        }
        return lowest;
    }
}


