package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Shifts gears.
 */
public class Shift extends Command {
    private final boolean _high;
    
    public Shift(boolean high) {
        _high = high;
    }
    
    protected void initialize() {
        Robot.driveTrain.setHighGear(_high);
    }

    protected void execute() {}
    protected boolean isFinished() {
        return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
