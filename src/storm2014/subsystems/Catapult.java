package storm2014.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.commands.PreLaunch;

public class Catapult extends Subsystem {
    private final Talon    _winch         = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private final Encoder  _winchEncoder  = new Encoder(RobotMap.PORT_ENCODER_PULLBACKENCODER_1,RobotMap.PORT_ENCODER_PULLBACKENCODER_2);
    private final Solenoid _disengage     = new Solenoid(RobotMap.PORT_SOLENOID_DISENGAGE);
    private final Solenoid _engage        = new Solenoid(RobotMap.PORT_SOLENOID_ENGAGE);
    private final Solenoid _latched       = new Solenoid(RobotMap.PORT_SOLENOID_LATCHED);
    private final Solenoid _unlatched     = new Solenoid(RobotMap.PORT_SOLENOID_UNLATCHED);
    private final Servo    _ratchetEngage = new Servo(RobotMap.PORT_SERVO);
    private static final double latchedAngle = 170;
    private static final double unlatchedAngle = 0;
    
    public Catapult(){
        _winchEncoder.start();
        LiveWindow.addSensor("Catapult", "Winch Encoder", _winchEncoder);
        LiveWindow.addActuator("Catapult", "Ratchet", _ratchetEngage);
        LiveWindow.addActuator("Catapult", "Winch", _winch);
        LiveWindow.addActuator("Catapult", "Disengage Sole", _disengage);
        LiveWindow.addActuator("Catapult","Engage Sole", _engage);
        LiveWindow.addActuator("Catapult", "Disengage Sole", _latched);
        LiveWindow.addActuator("Catapult","Engage Sole", _unlatched);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new PreLaunch());
    }
    
    public void setWinchPower(double winchRawVal){
        _winch.set(Math.abs(winchRawVal));
    }
    
    public void resetWinchEncoder(){
        _winchEncoder.reset();
    }
    
    public double getWinchDistance(){
        return _winchEncoder.get();
    }
    
    public void disengageWinch(){
        _disengage.set(true);
        _engage.set(false);
    }
    
    public void engageWinch(){
        _engage.set(true);
        _disengage.set(false);
    }
    
    public void latch(){
        _latched.set(true);
        _unlatched.set(false);
    }
    
    public void unlatch(){
        _unlatched.set(true);
        _latched.set(false);
    }
    
    public void setRatchetLatched(){
        _ratchetEngage.setAngle(latchedAngle);
    }
    
    public void setRatchetUnlatched(){
        _ratchetEngage.setAngle(unlatchedAngle);
    }
    
    //Needs to calculate the angle from the potentiometer voltage.  Maybe use a utility?
    public double getAngle(){
        return 0.0;
    }
}
