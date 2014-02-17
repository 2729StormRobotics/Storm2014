package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/*
Latches/Unlatches the latch.
*/
//Not Tested
public class SetLatched extends Command {

    public final boolean _latched;

    public SetLatched(boolean engaged) {
        requires(Robot.catapult);
        _latched = engaged;
    }

    protected void initialize() {
        if (_latched) {
            Robot.catapult.latch();
        } else {
            Robot.catapult.unlatch();
        }
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
