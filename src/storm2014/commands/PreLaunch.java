package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PreLaunch extends CommandGroup{
    
    public PreLaunch(){
        //wait until arm is in place needs a sensor
        addSequential(new WaitCommand(3000));
        addSequential(new Latch());
        addSequential(new Engage());
        addSequential(new PullBack(1, 100));
        
    }   
}
