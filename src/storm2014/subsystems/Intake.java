package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.HallEffectSpeedSensor;
import storm2014.utilities.TakeBackHalfPlusPlus;

public class Intake extends Subsystem{
    
    private Talon _motor  = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    private DigitalInput _ir = new DigitalInput(RobotMap.PORT_SENSOR_BALL_IR);
    private  TakeBackHalfPlusPlus takeBackHalfController;
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT_ROLLER);
    
    public Intake(){
        takeBackHalfController = new TakeBackHalfPlusPlus(_motor, _speedSensor, 1.0/100,1,0);
    }
    
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
