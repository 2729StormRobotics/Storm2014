package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.subsystems.LEDStrip;

public class PreLaunch extends CommandGroup{
    
    /**
     *
     * @author Garrett
     */
    
    public PreLaunch(){

        //wait until arm is in place needs a sensor
        addSequential(new WaitCommand(10));
        addSequential(new SetLEDMode(LEDStrip.StormSpiritMode));
        addSequential(new SetLatched(true));
        addSequential(new SetEngagedWinch(true));
        addSequential(new SetEngagedRatchet(true));
        addSequential(new PullBack(100));
        
    }
}
