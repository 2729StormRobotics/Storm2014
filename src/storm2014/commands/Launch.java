package storm2014.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class Launch extends CommandGroup {
    
    public Launch(){
        addSequential(new Disengage());
        addSequential(new Engage());     
    }
    
}