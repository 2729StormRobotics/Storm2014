package storm2014.commands;

import storm2014.Robot;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.subsystems.VisionSystem;

//Not Tested
public class TurnToTarget extends Command {
     private final double _speed;  
     private double _targetX;
     private int    _direction; // + clockwise, - counterclockwise
     
    public TurnToTarget(double speed) {
        requires(Robot.driveTrain);
       _speed = speed;
    }
   
    protected void initialize() { 
        _targetX =  VisionSystem.getTargetXAngle();
        _direction = _targetX >= 0 ? 1 : -1;
    }
   
    protected void execute() {
         double turnVal = _speed*_direction;
         Robot.driveTrain.tankDrive(turnVal,-turnVal);
    }
    
    protected boolean isFinished() {
        double newTargetX = VisionSystem.getTargetXAngle();
        double product = newTargetX*_targetX; // + if still approaching ball,
                                          // - if we just passed it.
        _targetX = newTargetX;
        return product < 0;
    }
    
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }
    protected void interrupted() {
        end();
    }
}
