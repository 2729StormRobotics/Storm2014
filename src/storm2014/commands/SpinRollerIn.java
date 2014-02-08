package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

//Not Tested
public class SpinRollerIn extends Command {
    public SpinRollerIn(){
        requires(Robot.intake);
    }
    
    protected void initialize() {}
    
    protected void execute() {
        Robot.intake.spinIn();
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
        Robot.intake.stop();
    }
    
    protected void interrupted() {
        end();
    }
}

