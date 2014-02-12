package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class Shift extends Command {

    protected void initialize() {
        Robot.driveTrain.setHighGear(!Robot.driveTrain.isHighgear());
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
