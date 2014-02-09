package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class WaitForFollowThrough extends Command {
    private double _prevDiff  = 0,
                   _prevAngle = 0;

    protected void initialize() {
        _prevDiff = _prevAngle = 0;
    }
    
    protected void execute() {}

    protected boolean isFinished() {
        double angle = Robot.catapult.getPivotAngle();
        double diff = angle-_prevAngle;
        double product = diff*_prevDiff;
        if(diff != 0) {
            _prevDiff = diff;
        }
        return product < 0;
    }

    protected void end() {}
    protected void interrupted() {}
}
