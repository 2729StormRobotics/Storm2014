/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 *
 * @author Erik
 */
public class Dance extends Command {
    double _angle;
    double _power;
    double _distance;
    public Dance(double power, double distance, double angle) {
        requires(Robot.driveTrain);
        _angle = angle;
        _power = power;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    Dance(double DRIVE_SPEED, double sideLength) {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.clearEncoder();
        Robot.driveTrain.clearGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (_angle>0){
        Robot.driveTrain.tankDrive(_power,-_power);
        }else{
          Robot.driveTrain.tankDrive(-_power,_power);  
        }
        Robot.driveTrain.tankDrive(_power, _power);
            if (_distance>100){
                _power = 0;
             if (Robot.driveTrain.tankDrive.getLeftDistance() > 1000 &&
                Robot.driveTrain.tankDrive.getRightDistance() > 1000){
                   if (_angle>140){
                       Robot.driveTrain.tankDrive(_power, _distance);
            }}
        }else{
          Robot.driveTrain.tankDrive(-_power,_power);  
          
        }
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
