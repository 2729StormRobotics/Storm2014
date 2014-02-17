package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Tilts the tilter thru using the joystick.
 */
public class TiltByJoystick extends Command {
    public TiltByJoystick() {
        requires(Robot.tilter);
    }
    
    protected void initialize() {}

    protected void execute() {
        Robot.tilter.setRawVal(Robot.oi.getTilt());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
