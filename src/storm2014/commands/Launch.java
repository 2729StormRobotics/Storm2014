package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.util.AllocationException;
import storm2014.Robot;
import storm2014.commands.control.WaitForButton;
import storm2014.subsystems.LEDStrip;
/**
 *Launches the catapult thru a series of steps.
 */
// Tested by Joe Doyle (mostly) on 2014 Robot on 2/15/14
public abstract class Launch extends CommandGroup {
    public Launch(){
        if(!thisIsIntentional()) {
            throw new AllocationException("Launch should only be run if you want to");
        }
        addSequential(new Command() {
            protected void initialize() {
                Robot.catapult.setFinishedPreLaunch(false);
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        addSequential(new SetLEDMode(LEDStrip.PileMode));
        addSequential(new SetArmPosition(2));
        addSequential(new WaitForButton());
        addSequential(new SetLatched(false));
        addSequential(new SetLEDMode(LEDStrip.RainbowDancePartyMode));
        addSequential(new WaitForFollowThrough(),1);
        addSequential(new SetLEDMode(LEDStrip.USAMode));
        addSequential(new WaitForButton());
        addSequential(new SetEngagedRatchet(false));
        addSequential(new WaitForButton());
        addSequential(new ResetCatapult());
        addSequential(new SetLEDMode()); //Back to whatever the default is
    }    
    
    protected abstract boolean thisIsIntentional();
}