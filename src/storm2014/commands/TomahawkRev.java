
package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;


public class TomahawkRev extends Command {
    
    private double _speed;
    private boolean _hasBeenForward = false;
    
    
    public TomahawkRev(double speed){
        _speed = speed;
    }
    
    protected void initialize() {
        Robot.tomahawk.setMotorRaw(_speed);
    }
    
    protected void execute() {
        
        if (!_hasBeenForward ){
            _hasBeenForward = Robot.tomahawk.isForward();        
        }
    }
    
    protected boolean isFinished() {
        return !Robot.tomahawk.isForward() && _hasBeenForward;
    }
    
    protected void end() {
        Robot.tomahawk.setMotorRaw(0);
    }
    
    protected void interrupted() {
        end();
    }
    
    
    
    
}
