
package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;


public class TomahawkRev extends Command {
    
    private int _revs = 0;
    private boolean _initial;
    private double _speed;
    
    
    public TomahawkRev(double speed){
        _speed = speed;
    }
    
    protected void initialize() {
        _initial = Robot.tomahawk.getStatus();
        Robot.tomahawk.setMotorRaw(_speed);
    }
    
    protected void execute() {
        
        if(_initial){
            if(Robot.tomahawk.getStatus() == true){
                _revs = _revs + 1;
            }
            
        } else{
            if(Robot.tomahawk.getStatus() == false){
                _revs = _revs + 1;
            }
        }
    }
    
    
    protected boolean isFinished() {
        if(_revs == 2){
            return true;
        } else return false;
    }
    
    protected void end() {
        Robot.tomahawk.setMotorRaw(0);
    }
    
    protected void interrupted() {
        end();
    }
    
    
    
    
}
