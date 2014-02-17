package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 *Changes the arm position by a given increment.
 */
//Not tested
public class ChangeArmPosition extends Command {
    private final int _increment;
    
    public ChangeArmPosition(int increment){
        requires(Robot.intake);
        _increment = increment;
    } 

    protected void initialize() {
       int nextMode = Robot.intake.getMode() + _increment;
       if(nextMode >= 3) {
           nextMode = 2;
       } else if(nextMode < 0) {
           nextMode = 0;
       }
       Robot.intake.setMode(nextMode);
    }

    protected void execute() {}

    protected boolean isFinished() {
       return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
