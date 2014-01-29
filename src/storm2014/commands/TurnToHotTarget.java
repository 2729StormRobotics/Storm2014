/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
import storm2014.subsystems.VisionSystem;
/**
 *
 * @author Erik
 */
public class TurnToHotTarget extends Command {
    private double _speed;
    private double targetX;
    private boolean hotTarget;
    public TurnToHotTarget(double speed) {
        requires(Robot.driveTrain);
       _speed = speed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       if (hotTarget = VisionSystem.foundHotTarget()){
        targetX = VisionSystem.getTargetXAngle();
        if (targetX<=0){
            Robot.driveTrain.tankDrive(_speed, -_speed );
        }
        else {
            Robot.driveTrain.tankDrive(-_speed, _speed);      
        }
    }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    return targetX<=0.1 && targetX>=-0.1 && VisionSystem.foundHotTarget();   
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
