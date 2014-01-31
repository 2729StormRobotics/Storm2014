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
        //wait until ball is in catapult. will be ir sensor.
        addSequential(new SetLEDMode(LEDStrip.PewMode));
        addSequential(new WaitCommand(1000));
        addSequential(new Unlatch());
        //Wait
        addSequential(new WaitCommand(1000));
        addSequential(new Disengage());  
    }
    
}