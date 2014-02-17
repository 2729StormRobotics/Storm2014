package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Engages/Disengages the ratchet.
 */
//Tested by Garrett/Matt on Magic Box 2/6/14
public class SetEngagedRatchet extends Command {
    private final boolean _latched;

    public SetEngagedRatchet(boolean latched) {
        requires(Robot.catapult);
        _latched = latched;
    }

    protected void initialize() {
        if (_latched) {
            Robot.catapult.setRatchetLatched();
        } else {
            Robot.catapult.setRatchetUnlatched();
        }
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
