package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
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
    private Solenoid _solBot = new Solenoid(RobotMap.PORT_SOLENOID_INTAKE_BOTTOM);
    private Solenoid _solTop = new Solenoid(RobotMap.PORT_SOLENOID_INTAKE_TOP);
    private int _mode;
    
    public Intake(int mode){
        takeBackHalf = new TakeBackHalfPlusPlus(_motor, _speedSensor, 1.0/100,1,0);
        _mode = mode;
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
    
    public double getRollerSpeed(){
        return _speedSensor.pidGet();
    }
    
    public void setRollerSpeed(double output){
        _motor.set(output);
    }
    
    public boolean isInIntake(){
        return _ir.get();
    }

    public void setMode(int mode){
        switch (mode){
            case 0: _solBot.set(false);
                    _solTop.set(false);
                    break;
            case 1: _solBot.set(true);
                    _solTop.set(false);
                    break;
            case 2: _solBot.set(true);
                    _solTop.set(true);
                    break;
            default: _solBot.set(false);
                     _solTop.set(false);
        }   
    }
    
    public int getMode(){
        return _mode;
    }
    
    public String getModeName(){
        switch (_mode){
            case 0: return "Low";        
            case 1: return "Middle";
            case 2: return "High";
        }
        
        return null;
    }
    
    
}
