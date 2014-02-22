package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
/**
 * Gets the robot ready to fire.
*/
// Tested by Joe Doyle on 2014 Robot on 2/15/14
public class PreLaunch extends CommandGroup {
    public PreLaunch() {
        setInterruptible(false);
        addSequential(new SetEngagedRatchet(true));
    }
}
