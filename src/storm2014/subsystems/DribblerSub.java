package storm2014.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.Dribbler;
import storm2014.utilities.HallEffectSpeedSensor;


public class DribblerSub extends Subsystem{
    
    private Talon _motor  = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT_ROLLER);
    private Encoder _winchEncoder =  new Encoder(RobotMap.PORT_ENCODER_RELOADENCODER_1,RobotMap.PORT_ENCODER_RELOADENCODER_2);
    Dribbler dribbler;
    
    public DribblerSub(){
        dribbler = new Dribbler(_motor,_speedSensor,_velocityRobot, 1/100);
    }
    
    protected void initDefaultCommand() {
    }
    
    
    
}
