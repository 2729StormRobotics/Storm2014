package storm2014.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.commands.PreLaunch;

public class Catapult extends Subsystem {
    private final Talon    _winch         = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private final Encoder  _winchEncoder  = new Encoder(RobotMap.PORT_ENCODER_WINCH_1,RobotMap.PORT_ENCODER_WINCH_2);
    private final Solenoid _winchShift    = new Solenoid(RobotMap.PORT_SOLENOID_WINCH);
    private final Solenoid _latch         = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
    private final Servo    _ratchetEngage = new Servo(RobotMap.PORT_SERVO);
    private static final double latchedAngle = 170;
    private static final double unlatchedAngle = 0;
    
    public Catapult(){
        _winchEncoder.start();
        LiveWindow.addSensor("Catapult", "Winch Encoder", _winchEncoder);
        LiveWindow.addActuator("Catapult", "Ratchet", _ratchetEngage);
        LiveWindow.addActuator("Catapult", "Winch", _winch);
        LiveWindow.addActuator("Catapult", "Winch shifter", _winchShift);
        LiveWindow.addActuator("Catapult", "Latch", _latch);
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
        _winchShift.set(false);
    }
    
    public void engageWinch(){
        _winchShift.set(true);
    }
    
    public void latch(){
        _latch.set(true);
    }
    
    public void unlatch(){
        _latch.set(false);
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
