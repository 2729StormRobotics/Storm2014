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
public class TurnToTarget extends Command {     
    
     private double _speed;     
     private double targetX;
     
    public TurnToTarget(double speed) {
        requires(Robot.driveTrain);
        _speed = speed;
      
    }

   
    protected void initialize() {
    }

   
    protected void execute() {
        targetX = VisionSystem.getXTarget();
        if (targetX<=0){
            Robot.driveTrain.tankDrive(_speed, -_speed );
        }
        else {
            Robot.driveTrain.tankDrive(-_speed, _speed);      
        }
    }

    
    protected boolean isFinished() {
    return targetX<=0.1 && targetX>=-0.1;
    }
    
    protected void end() {
    }

    
    protected void interrupted() {
    }
}
