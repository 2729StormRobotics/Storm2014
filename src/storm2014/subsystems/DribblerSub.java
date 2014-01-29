package storm2014.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.DribbleSpeedCalculator;
import storm2014.utilities.HallEffectSpeedSensor;

public class DribblerSub extends Subsystem{
    
    private Talon _motor  = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT_ROLLER);
    private Encoder _velocityRobot = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
    DribbleSpeedCalculator dribbler;
    
    public DribblerSub(){
        dribbler = new DribbleSpeedCalculator(_motor,_speedSensor,_velocityRobot, 1/100);
    }
    
    protected void initDefaultCommand() {
    }
    
    public double getSpeed(){
        return _speedSensor.pidGet();
    }
    
    public double getMotorRaw(){
        return _motor.get();
    }
    
    public void disable(){
        dribbler.disable();
    }
    
    public void enable(){
        dribbler.enable();
    }
}
