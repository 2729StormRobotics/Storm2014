package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;

public class Intake extends Subsystem{
    
    private Talon _motor  = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    DigitalInput _ir = new DigitalInput(RobotMap.PORT_SENSOR_TOMAHAWK_IR);
    
    protected void initDefaultCommand() {
        
    }
  
    public void spinIn(){
        _motor.set(1);
    }
    
     public void spinOut(){
        _motor.set(-1);
    }
    
     public void stopMotor(){
        _motor.set(0);
    }
     
     public boolean isLoaded(){
         return _ir.get();
     }
    
}
