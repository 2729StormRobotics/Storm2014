package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.subsystems.LEDStrip;
/**
 *Launches the catapult thru a series of steps.
 */
// Tested by Joe Doyle (mostly) on 2014 Robot on 2/15/14
public class Launch extends CommandGroup {
    public Launch(){
        setInterruptible(false);
        addSequential(new SetLEDMode(LEDStrip.RainbowDancePartyMode));
        addSequential(new Command() {
            protected void initialize() {
                Robot.catapult.setFinishedPreLaunch(false);
                Robot.catapult.setCatapultOut(true);
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        addSequential(new SetArmPosition(2));
        addSequential(new SetLatched(false));
        addSequential(new WaitForFollowThrough(),3);
        addSequential(new SetEngagedRatchet(false));
        addSequential(new ResetCatapult());
        addSequential(new SetLEDMode(LEDStrip.DefaultMode)); //Back to whatever the default is
    }
}