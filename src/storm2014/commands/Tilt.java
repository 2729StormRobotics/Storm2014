package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Matthew Rassmann
 */
public class Tilt extends Command{
    
    private double _curAngle;
    private static final double MULTIPLIER = 0; //TODO find constant
    private double _setAngle;
    private double _rawOutput;
    
    public Tilt(double angle){
        _setAngle = angle;
        requires(Robot.tilter);
    }
    
    protected void initialize() {
        
    }
    
    protected void execute() {
        _curAngle = Robot.tilter.getAngle();
        
        Robot.tilter.setRawVal(0.2);
    }
    
    protected boolean isFinished() {
        return Math.abs((_curAngle - _setAngle)/_setAngle) < .05;
    }
    
    protected void end() {
        Robot.tilter.setRawVal(0.0);
    }
    
    protected void interrupted() {
        
    }
}
