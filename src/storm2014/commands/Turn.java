package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

// Tested by Joe Doyle on Electra before 2/8/13
public class Turn extends Command {

    double _angle;
    double _speed;

    public Turn(double angle, double power) {
        requires(Robot.driveTrain);
        _angle = angle;
        _speed = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (_angle > 0){
            Robot.driveTrain.tankDrive(_speed,-_speed);
        } else {
            Robot.driveTrain.tankDrive(-_speed,_speed);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(_angle>0){
            if(Robot.driveTrain.getGyroAngle()>_angle){
                return true;
            }
        } else {
             if(Robot.driveTrain.getGyroAngle()<_angle){
                return true;
             }
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
