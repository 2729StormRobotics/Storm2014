package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.HallEffectSpeedSensor;
import storm2014.utilities.TakeBackHalfPlusPlus;

/**
 *
 * @author Garrett
 */

public class Intake extends Subsystem{
    
    private Talon _motor  = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    private DigitalInput _ir = new DigitalInput(RobotMap.PORT_SENSOR_BALL_IR);
    private  TakeBackHalfPlusPlus takeBackHalf;
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT_ROLLER);
    
    public Intake(){
        takeBackHalf = new TakeBackHalfPlusPlus(_motor, _speedSensor, 1.0/100,1,0);
    }
    
    protected void initDefaultCommand() {
        
    }
    
    public void spinIn(){
        takeBackHalf.setOutputRange(0, 1);
        takeBackHalf.setSetpoint(1000);
    }
    
    public void spinOut(){
        takeBackHalf.setOutputRange(-1, 0);
        takeBackHalf.setSetpoint(-1000);
    }
    
    public void stopMotor(){
        takeBackHalf.setOutputRange(-1, 1);
        takeBackHalf.disable();
    }
    //TODO make sure this gets added 
    public boolean isLoaded(){
        return _ir.get();
    }
    
    public double getRollerSpeed(){
        return _speedSensor.pidGet();
    }
    
    public void setRollerSpeed(double output){
        _motor.set(output);
    }
}
