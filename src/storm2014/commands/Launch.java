package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Garrett
 */

public class Launch extends CommandGroup {
    
    public Launch(){
        addSequential(new Disengage());
        addSequential(new WaitCommand(1000.0));
        addSequential(new Engage());     
    }
    
}