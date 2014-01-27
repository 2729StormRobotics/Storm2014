
package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;


public class TomahawkRev extends Command {
    
    private boolean _hasBeenForward = false;
    
    protected void initialize() {
        _hasBeenForward = false;
        Robot.tomahawk.startMotor(); 
    }
    
    protected void execute() {
        if (!_hasBeenForward ){
            _hasBeenForward = Robot.tomahawk.isForward();        
        }
    }
    
    protected boolean isFinished() {
       return _hasBeenForward && !Robot.tomahawk.isForward();
    }
    
    protected void end() {
        Robot.tomahawk.stopMotor();
    }
    
    protected void interrupted() {
        end();
    }

}
