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
        targetX = VisionSystem.getTargetXAngle();
        if (targetX<0){
            Robot.driveTrain.tankDrive(-_speed, _speed );
        }
        else {
            Robot.driveTrain.tankDrive(_speed, -_speed);      
        }
    }

    
    protected boolean isFinished() {
    return targetX<=4 && targetX>=-4;
    }
    
    protected void end() {
        Robot.driveTrain.tankDrive(0.0,0.0);
    }

    
    protected void interrupted() {
        end();
    }
}
