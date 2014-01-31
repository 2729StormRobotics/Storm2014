package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class Unlatch extends Command {
 
    public Unlatch(){
        requires(Robot.catapult);
    }
    
    protected void initialize() {
       
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
     return true;   
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}
