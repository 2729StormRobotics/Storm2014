/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author Erik
 */
public class AutonomousTracking extends Command {
    double _power;
    double _angle;
    double XPos;
    double YPos;
    double XAngle;
    double YAngle;
    public AutonomousTracking() {
         requires(Robot.driveTrain);
         VisionSystem.getXTarget();
         VisionSystem.getYTarget();
         VisionSystem.getXAngle();
         VisionSystem.getYAngle();
         
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.clearEncoder();
        Robot.driveTrain.clearGyro();
        XPos = VisionSystem.getXTarget();
        YPos = VisionSystem.getYTarget();
        
    }
                           
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        XAngle = VisionSystem.getXAngle();
        YAngle = VisionSystem.getYAngle();
        if (XAngle > 0){
            Robot.driveTrain.tankDrive(_power, -_power);
            Robot.driveTrain.tankDrive(_power, _power);
        }else if (XAngle < 0){
            Robot.driveTrain.tankDrive(-_power, _power);
            Robot.driveTrain.tankDrive(_power, _power);
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
