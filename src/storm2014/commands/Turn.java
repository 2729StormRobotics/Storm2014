package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Turns the robot.
 */
// Tested by Joe Doyle on Electra before 2/8/13
public class Turn extends Command {

    double _angle;
    double _speed;

    public Turn(double angle, double power) {
        requires(Robot.driveTrain);
        _angle = angle;
        _speed = power;
    }

    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    protected void execute() {
        if (_angle > 0){
            Robot.driveTrain.tankDrive(_speed,-_speed);
        } else {
            Robot.driveTrain.tankDrive(-_speed,_speed);
        }
    }

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

    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    protected void interrupted() {
        end();
    }
}
