package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class Tilt extends Command {
    
    private double _angle;
    private static final double MULTIPLIER = 0; //TODO find constant
    private double _setAngle;
    
    public Tilt(){
        requires(Robot.tilter);
    }
    
    protected void initialize() {
        
    }
    
    protected void execute() {
        _angle = Robot.tilter.getVolts() * MULTIPLIER;
        
    }
    
    protected boolean isFinished() {
        
    }
    
    protected void end() {
        
    }
    
    protected void interrupted() {
        
    }
    
    
    
}
