package storm2014.commands;

import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;

public class TargetAngle extends Command {
     double _angle;
    double _speed;
    double _getAngle;
    public TargetAngle(double angle, double power, double getAngle) {
        requires(Robot.driveTrain);
        _angle = angle;
        _speed = power;
        _getAngle = getAngle;
    }

    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    protected void execute() {
         if (_angle > 0){
            Robot.driveTrain.tankDrive(_speed,-_speed);
            _angle = VisionSystem.getTargetXAngle();
            _getAngle = Robot.driveTrain.getGyroAngle();
        } else {
            Robot.driveTrain.tankDrive(-_speed,_speed);
            _angle = VisionSystem.getTargetXAngle();
            _getAngle = Robot.driveTrain.getGyroAngle();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
