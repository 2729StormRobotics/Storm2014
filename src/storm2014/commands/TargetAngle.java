/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;
/**
 *
 * @author Erik
 */
public class TargetAngle extends Command {
     double _angle;
    double _speed;
    double _getAngle;
    public TargetAngle(double angle, double power, double getAngle) {
        requires(Robot.driveTrain);
        _angle = angle;
        _speed = power;
        _getAngle = getAngle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    // Called repeatedly when this Command is scheduled to run
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
