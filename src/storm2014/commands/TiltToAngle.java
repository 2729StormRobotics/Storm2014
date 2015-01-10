package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

//Not Tested

/**
 *
 * @author Erik
 */
public class TiltToAngle extends Command {
    private final double _target;
    private int          _direction;
    
    /**
     *
     * @param angle
     */
    public TiltToAngle(double angle) {
        requires(Robot.tilter);
        _target = angle;
    }
    
    /**
     *
     */
    protected void initialize() {
        double currAngle = Robot.tilter.getAngle();
        _direction = currAngle < _target ?  1 :
                                           -1;
    }
    
    /**
     *
     */
    protected void execute() {
        Robot.tilter.setRawVal(_direction);
    }
    
    /**
     *
     * @return
     */
    protected boolean isFinished() {
        return _direction*Robot.tilter.getAngle() >= _direction*_target;
    }
    
    /**
     *
     */
    protected void end() {
        Robot.tilter.setRawVal(0.0);
    }

    /**
     *
     */
    protected void interrupted() {
        end();
    }
}