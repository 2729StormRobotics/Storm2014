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
        _speed = speed; //sets _speed = to wanted speed
    }

    protected void initialize() {
      Robot.shooter.getPIDController().setSetpoint(_speed); //sets wanted speed to wanted speed
    }

    protected void execute() {
      if(currentSpeed/_speed < THRESHOLD_PID ){ //if the motor is less then the threshold gun the motor 
        Robot.shooter.getPIDController().disable(); 
      } else {
          Robot.shooter.getPIDController().enable(); //else change to more precise
      }
      
    }

    //if % error is less then 1% return 
    protected boolean isFinished() {
        if(Robot.shooter.onTarget()){ // if the percent error is less then a certain amount end spin up.
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