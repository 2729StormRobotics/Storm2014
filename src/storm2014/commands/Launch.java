package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.util.AllocationException;
import storm2014.Robot;
import storm2014.commands.control.WaitForButton;
import storm2014.subsystems.LEDStrip;
//import storm2014.subsystems.LEDStrip;

//Not Tested
public abstract class Launch extends CommandGroup {
    // Tension More or less and then backtrack
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
        addSequential(new WaitForButton(Robot.oi.continueProcess));
        addSequential(new SetArmPosition(2));
        addSequential(new WaitForButton(Robot.oi.continueProcess));
        addSequential(new SetWinchEngaged(false));
        addSequential(new WaitForButton(Robot.oi.continueProcess));
        addSequential(new SetLatched(false));
        addSequential(new SetLEDMode(LEDStrip.RainbowDancePartyMode));
        addSequential(new WaitForButton(Robot.oi.continueProcess));
        addSequential(new WaitForFollowThrough(),1);
        addSequential(new WaitForButton(Robot.oi.continueProcess));
        addSequential(new SetWinchEngaged(true));
        addSequential(new WaitForButton(Robot.oi.continueProcess));
        addSequential(new SetEngagedRatchet(false));
        //addSequential(new PullBack(100));
        addSequential(new SetLEDMode()); //Back to whatever the default is
        addSequential(new WaitForButton(Robot.oi.continueProcess));
    }    
    
    protected abstract boolean thisIsIntentional();
}