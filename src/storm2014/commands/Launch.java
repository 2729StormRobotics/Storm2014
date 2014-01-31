package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Garrett
 */

public class Launch extends CommandGroup {
    
    public Launch(){
        //wait until ball is in catapult.
        addSequential(new WaitCommand(1000));
        addSequential(new Disengage());  
        addSequential(new Unlatch());
    }
    
}