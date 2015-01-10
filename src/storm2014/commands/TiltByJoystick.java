package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Erik
 */
public class TiltByJoystick extends Command {

    /**
     *
     */
    public TiltByJoystick() {
        requires(Robot.tilter);
    }
    
    /**
     *
     */
    protected void initialize() {}

    /**
     *
     */
    protected void execute() {
        Robot.tilter.setRawVal(Robot.oi.getTilt());
    }

    /**
     *
     * @return
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     *
     */
    protected void end() {}

    /**
     *
     */
    protected void interrupted() {}
}
