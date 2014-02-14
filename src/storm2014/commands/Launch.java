package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.util.AllocationException;
import storm2014.Robot;
//import storm2014.subsystems.LEDStrip;

//Not Tested
public abstract class Launch extends CommandGroup {
    // Tension More or less and then backtrack
    public Launch(){
        if(!thisIsIntentional()) {
            throw new AllocationException("Launch should only be run if you want to");
        }
        addSequential(new SetArmPosition(2));
        addSequential(new SetWinchEngaged(false));
        addSequential(new SetLatched(false));
        addSequential(new WaitForFollowThrough());
        addSequential(new SetEngagedRatchet(false));
        Robot.catapult.setFinishedPreLaunch(false);
        //addSequential(new PullBack(100));
    }    
    
    protected abstract boolean thisIsIntentional();
}