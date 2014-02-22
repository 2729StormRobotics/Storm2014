package storm2014.commands;

import storm2014.Robot;
import storm2014.commands.control.DoNothing;

public class WaitForPrelaunch extends DoNothing {
    protected boolean isFinished() {
        return Robot.catapult.isFinishedPreLaunch();
    }
}
