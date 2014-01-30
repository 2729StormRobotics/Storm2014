package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;

/**
 *
 * @author Garrett
 */

public class Tomahawk extends Subsystem {
    
    DigitalInput _ir = new DigitalInput(RobotMap.PORT_SENSOR_TOMAHAWK_IR);
    Victor _motor = new Victor(RobotMap.PORT_MOTOR_TOMAHAWK);
    
   public Tomahawk(){
       LiveWindow.addActuator("Tomahawk","Motor", _motor);
       LiveWindow.addSensor("Tomahawk", "Sensor", _ir);
   }
    
    protected void initDefaultCommand() {
        
    }
    public boolean isForward(){
        return _ir.get();
    }
    
    
    public void startMotor(){
        _motor.set(1);
    }
    
    public void stopMotor(){
        _motor.set(0);
    }
    
}
