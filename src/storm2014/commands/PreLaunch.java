package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PreLaunch extends CommandGroup{
    
    public PreLaunch(){
        //wait until arm is in place
        addSequential(new Latch());
        addSequential(new Engage());
        addSequential(new PullBack(1, 100));
        
    }   
}
