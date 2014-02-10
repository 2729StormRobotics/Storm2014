package storm2014.commands;

import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;

//Not Tested
public class TurnToTarget extends Command {
    private final double _speed;
    private final int _direction; // + clockwise, - counterclockwise
    private final double _minAngle;
    private double _targetX;

    public TurnToTarget(boolean isRight,double speed,double minAngle) {
        requires(Robot.driveTrain);
        _direction = isRight ? 1 : -1;
        _speed = speed;
        _minAngle = minAngle;
    }

    protected void initialize() {
        Robot.driveTrain.clearGyro();
        _targetX = VisionSystem.getTargetXAngle();
    }

    protected void execute() {
        double turnVal = _speed * _direction;
        Robot.driveTrain.tankDrive(turnVal, -turnVal);
    }

    protected boolean isFinished() {
        if(Math.abs(Robot.driveTrain.getGyroAngle()) < _minAngle) {
            return false;
        }
        double newTargetX = VisionSystem.getTargetXAngle();
        // + if still approaching ball,
        // - if we just passed it.
        double product = newTargetX * _targetX;
        _targetX = newTargetX;
        return product < 0;
    }

    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    protected void interrupted() {
        end();
    }
}
