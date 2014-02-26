package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
/**
 *Increments the winch index.
 */
import storm2014.Robot;
public class NextWinchPreset extends Command{
    
    protected void initialize() {
        Robot.catapult.setPresetIndex(Robot.catapult.getCurrentIndex() + 1);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
