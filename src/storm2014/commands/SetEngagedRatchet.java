package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class SetEngagedRatchet extends Command{
    
    private boolean _latched;
    
    public SetEngagedRatchet(boolean latched){
        requires(Robot.catapult);
        _latched = latched;
    }
    
    protected void initialize() {
        if(_latched){
            Robot.catapult.setServoLatched();
        } else {
            Robot.catapult.setServoUnatched();
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
