package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class Tilt extends Command{
    
    private double _curAngle;
    private static final double MULTIPLIER = 0; //TODO find constant
    private double _setAngle;
    private double _rawOutput;
    
    public Tilt(){
        requires(Robot.tilter);
    }
    
    protected void initialize() {
        
    }
    
    protected void execute() {
        _curAngle = Robot.tilter.getVolts() * MULTIPLIER;
        _rawOutput = _setAngle * MULTIPLIER; // TODO convert angle to output
        Robot.tilter.setRawVal(_rawOutput);
    }
    
    protected boolean isFinished() {
        return Math.abs((_curAngle - _setAngle)/_setAngle) < .05;
    }
    
    protected void end() {
        
    }
    
    protected void interrupted() {
        
    }
}
