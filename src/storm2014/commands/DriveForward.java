package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class DriveForward extends Command {
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
        Robot.driveTrain.tankDrive(_speed, _speed);
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
