package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;


public class SpinUp extends Command{
    private static final double THRESHOLD_PID = .95;
    private static final double THRESHOLD_ERR = .02;
    private double _speed;
    private double currentSpeed;
    
    public SpinUp(double speed){
        requires(Robot.shooter);
        _speed = speed;
    }

    protected void initialize() {
      Robot.shooter.getPIDController().setSetpoint(_speed);
    }

    protected void execute() {
      if(currentSpeed/_speed < THRESHOLD_PID ){
        Robot.shooter.getPIDController().disable();
      } else {
          Robot.shooter.getPIDController().enable();      
      }
      
    }

    //if % error is less then 1% return 
    protected boolean isFinished() {
        if(Robot.shooter.onTarget()){
           return true;
        }
        else return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}