package storm2014.commands;

import storm2014.Robot;
import storm2014.commands.control.Conditional;

public class LaunchIfReady extends Conditional {
    public LaunchIfReady(){
        super(new Launch() {
            protected boolean thisIsIntentional() {
                return true;
            }
        }, null);
    }

    protected boolean condition() {
        return Robot.catapult.isFinishedPreLaunch();
    }
}
