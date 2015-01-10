package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Erik
 */
public class WaitForFollowThrough extends Command {
    private double _prevDiff  = 0,
            _prevAngle = 0;
    
    /**
     *
     */
    protected void initialize() {
        _prevDiff = _prevAngle = 0;
    }
    
    /**
     *
     */
    protected void execute() {}
    
    /**
     *
     * @return
     */
    protected boolean isFinished() {
        double angle = Robot.catapult.getPivotAngle();
        double diff = angle - _prevAngle;
        _prevAngle = angle;
        if(diff > 180){
            diff -= sgn(diff) * 360;
                    }
        double product = diff * _prevDiff;
        if(diff != 0){
            _prevDiff = diff;
        }
        return product < 0;
    }
    
    /**
     *
     */
    protected void end() {}

    /**
     *
     */
    protected void interrupted() {}
    
    private static double sgn(double x){
        if(x > 0){
            return 1;
        } else if(x==0){
            return 0;
        } else{
            return -1;
        }
    }
}


