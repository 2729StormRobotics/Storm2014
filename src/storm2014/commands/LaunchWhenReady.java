package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Wait until pre launch is finished then launch.
*/
public class LaunchWhenReady extends Command {
    Command _launch;
     boolean _hasRun;
    public LaunchWhenReady(){
        _launch = new Launch() {
            protected boolean thisIsIntentional() {
                return true;
            }
        };
    }

    protected void initialize() {}

    protected void execute() {
         if (Robot.catapult.isFinishedPreLaunch()) {
            _launch.start();
            _hasRun =  true;
        }
    }

    protected boolean isFinished() {
         return _hasRun && !_launch.isRunning();
    }

    protected void end() {}
    protected void interrupted() {}
}
