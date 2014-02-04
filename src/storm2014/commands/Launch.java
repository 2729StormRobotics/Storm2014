package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.subsystems.LEDStrip;

/**
 *
 * @author Garrett/Matthew Rasmann
 */

public class Launch extends CommandGroup {
    
    public Launch(){
        addSequential(new SetLEDMode(LEDStrip.ParticleCollisionMode));
        addSequential(new SetLatched(false));
        //Wait
        addSequential(new WaitCommand(5));
        addSequential(new SetEngagedWinch(false));  
    }
    
}