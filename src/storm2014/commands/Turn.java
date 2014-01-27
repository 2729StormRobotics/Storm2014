/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

/**
 *
 * @author Storm
 */

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class Turn extends Command {

    double _angle;
    double _power;

    public Turn(double angle, double power) {
        requires(Robot.driveTrain);
        _angle = angle;
        _power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (_angle > 0){
            Robot.driveTrain.tankDrive(_power,-_power);
        } else {
            Robot.driveTrain.tankDrive(-_power,_power);
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
