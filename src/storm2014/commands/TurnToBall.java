package storm2014.commands;

import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;
/**
 * Turns robot to ball.
 */
//Not Tested
public class TurnToBall extends Command {                           
     private final double _speed;
     private double _ballX;
     private int    _direction; // + clockwise, - counterclockwise
     
    public TurnToBall(double speed) {
        requires(Robot.driveTrain);
       _speed = speed;
    }
   
    protected void initialize() { 
        _ballX =  VisionSystem.getBallXAngle();
        _direction = _ballX >= 0 ? 1 : -1;
    }
   
    protected void execute() {
         double turnVal = _speed*_direction;
         Robot.driveTrain.tankDrive(turnVal,-turnVal);
    }
    
    protected boolean isFinished() {
        double newBallX = VisionSystem.getBallXAngle();
        double product = newBallX*_ballX; // + if still approaching ball,
                                          // - if we just passed it.
        return product < 0;
    }
    
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }
    protected void interrupted() {
        end();
    }
}
