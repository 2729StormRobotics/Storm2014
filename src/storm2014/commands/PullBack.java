package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

//Not Tested
public class PullBack extends Command {
     double _power = 0.5;
     double _distance;
     
    public PullBack(double distance){
        requires(Robot.catapult);
        _distance = distance;
    }
    
    protected void initialize() {}

    protected void execute() {
        Robot.catapult.setWinchPower(_power);
    }

    protected boolean isFinished() {
        return (Math.abs(Robot.catapult.getWinchDistance())>=_distance);
    }

    protected void end() {
       Robot.catapult.setWinchPower(0.0);
    }

    protected void interrupted() {
        end();
    }
}
