package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class SetLatched extends Command {
 
    public boolean _b;
    
    public SetLatched(boolean b){
        requires(Robot.catapult);
        _b = b;
    }
    
    protected void initialize() {
       if(_b){
           Robot.catapult.engage();
       } else{
           Robot.catapult.disengage();
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
