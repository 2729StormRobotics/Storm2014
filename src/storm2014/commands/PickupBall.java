package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class PickupBall extends Command {
    
    protected void initialize() {
       
    }

    protected void execute() {
       Robot.intake.spinIn();
    }

    protected boolean isFinished() {
        return Robot.intake.isLoaded();
    }

    protected void end() {
       Robot.intake.stopMotor();
    }

    protected void interrupted() {
       end();
    }  
}
