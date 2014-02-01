package storm2014.commands;
import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;

public class TurnToBall extends Command {                           
     private double _speed;
     private double ballX;
     
    public TurnToBall(double speed) {
        requires(Robot.driveTrain);
       _speed = speed;
    }

   
    protected void initialize() { 
        ballX =  VisionSystem.getBallXAngle();
    }
   
    protected void execute() {
         ballX =  VisionSystem.getBallXAngle();
        if (ballX<=0){
            Robot.driveTrain.tankDrive(_speed, -_speed );
        }
        else {
            Robot.driveTrain.tankDrive(-_speed, _speed);      
        }
    }

    
    protected boolean isFinished() {
        return ballX<=0.1 && ballX >=-0.1;
    }
    
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    
    protected void interrupted() {
    }
}
