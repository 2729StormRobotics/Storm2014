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

public class TurnBasedOnAnAngle extends Command {
    
    double _angle;
  
    public TurnBasedOnAnAngle(double angle) {
        requires(Robot.driveTrain);
        _angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrain.tankDrive(0.5,-0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.driveTrain.getGyroAngle()>_angle){
            return true;
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
