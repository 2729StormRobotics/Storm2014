package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author garrett
 */
public class PostLaunch extends CommandGroup {
    
    public PostLaunch(){
        addSequential(new Latch());
        addSequential(new Engage());
    }
    
}
