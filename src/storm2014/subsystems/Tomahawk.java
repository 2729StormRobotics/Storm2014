package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;



public class Tomahawk extends Subsystem {
    
    DigitalInput _ir = new DigitalInput(RobotMap.PORT_SENSOR_TOMAHAWK_IR);
    Victor _motor = new Victor(RobotMap.PORT_MOTOR_TOMAHAWK);
    
    protected void initDefaultCommand() {
        
    }
    public boolean isForward(){
        return _ir.get();
    }
    
    public void setMotorRaw(double output){
        _motor.pidWrite(output);
    }
}
