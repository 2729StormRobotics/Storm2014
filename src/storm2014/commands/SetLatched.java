package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class SetLatched extends Command {
 
    public boolean _latched;
    
    public SetLatched(boolean engaged){
        requires(Robot.catapult);
        _latched = engaged;
    }
    
    protected void initialize() {
       if(_latched){
           Robot.catapult.engageWinch();
       } else{
           Robot.catapult.disengageWinch();
       }
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
