import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public long task1Ans() throws FileNotFoundException {
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


        long topLeft = 0;
        long topRight = 0;
        long bottomLeft = 0;
        long bottomRight = 0;

        for (RobotInfo robotInfo : allRobots) {
            robotInfo.xPos = Math.floorMod(robotInfo.xPos + (100 * robotInfo.xVel), 101);
            robotInfo.yPos = Math.floorMod(robotInfo.yPos + (100 * robotInfo.yVel), 103);
            if (robotInfo.xPos <= 49) {
                if (robotInfo.yPos <= 50) {
                    topLeft++;
                }
                else if (robotInfo.yPos > 51) {
                    bottomLeft++;
                }
            }
            else if (robotInfo.xPos > 50) {
                if (robotInfo.yPos <= 50) {
                    topRight++;
                }
                else if (robotInfo.yPos > 51) {
                    bottomRight++;
                }
            }
        }
        return topLeft * topRight * bottomLeft * bottomRight;
    }
}

class RobotInfo {
    int xPos;
    int yPos;
    int xVel;
    int yVel;
}
