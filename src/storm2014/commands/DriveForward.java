package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.Robot;
/**
 *Drives the robot forward by a certain distance and speed
 */
public class DriveForward extends Command {
    private static final double P_GAIN = 0;
    static {
        SmartDashboard.putNumber("Encoder feedback gain", P_GAIN);
    }
    final double _speed;
    final double _distance;

    public DriveForward(double speed, double distance) {
        requires(Robot.driveTrain);
        _speed = speed;
        _distance = distance;
    }

    protected void initialize() {
        Robot.driveTrain.clearEncoders();
    }

    protected void execute() {
        double gain = SmartDashboard.getNumber("Encoder feedback gain",P_GAIN);
        double err = Robot.driveTrain.getRightDistance()-Robot.driveTrain.getLeftDistance();
        double diff = err*gain;
        double left  = _speed + diff/2,
               right = _speed - diff/2;
        if(right < -1) {
            left -= (right+1);
            right = -1;
        } else if(right > 1) {
            left -= (right-1);
            right = 1;
        }
        if(left < -1) {
            right -= (left+1);
            left = -1;
        } else if(left > 1) {
            right -= (left-1);
            left = 1;
        }
        Robot.driveTrain.tankDrive(Math.max(-1, Math.min(1, left )),
                                   Math.max(-1, Math.min(1, right)));
    }

    protected boolean isFinished() {
        double left  = Robot.driveTrain.getLeftDistance(),
               right = Robot.driveTrain.getRightDistance();
        double distance = Math.abs(left) > Math.abs(right) ? left :
                                                             right;
        return (_speed >= 0 && distance >= _distance)
            || (_speed < 0  && distance <= -_distance);
    }

    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    protected void interrupted() {
        end();
    }
}
