/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author Erik
 */
public class VisionSystemCommand extends CommandGroup {
     private static final double 
                                 DRIVE_SPEED = 0.6,
                                 TURN_SPEED  = 0.7; 
     private double _sideLength;                            
     private double _power;                            
    public VisionSystemCommand(double sideLength, double power) {
       _sideLength = sideLength;
       _power = power;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() { 
       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
         double X = VisionSystem.getBallXAngle();
        if (X<=0){
            Robot.driveTrain.tankDrive(_power, -_power );
            Robot.driveTrain.tankDrive(_power, _power);
        }
        else {
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
