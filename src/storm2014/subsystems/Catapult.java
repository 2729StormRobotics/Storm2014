package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.commands.control.DoNothing;
import storm2014.commands.PreLaunch;
import storm2014.utilities.MagneticEncoder;

public class Catapult extends Subsystem {
    //Full Power is -650 on the encoder
    public static final double BASE_ANGLE = 188;
    
    private final Talon           _winch        = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private final Encoder         _winchEncoder = new Encoder(RobotMap.PORT_ENCODER_WINCH_1,RobotMap.PORT_ENCODER_WINCH_2);
    private final Solenoid        _winchShift   = new Solenoid(RobotMap.PORT_SOLENOID_WINCH);
    private final Solenoid        _latch        = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
    private final Servo           _ratchet      = new Servo(RobotMap.PORT_SERVO);
    private final MagneticEncoder _magEnc       = new MagneticEncoder(RobotMap.PORT_SENSOR_MAG_ENCODER);
    private boolean preLaunchFinished;
    private static final double ANGLE_RATCHET_ENGAGED    = 170;
    private static final double ANGLE_RATCHET_DISENGAGED = 0;
    private boolean _rachetEngaged = false;
    
    public Catapult(){
        _winchEncoder.start();
        LiveWindow.addSensor("Catapult", "Winch Encoder", _winchEncoder);
        LiveWindow.addActuator("Catapult", "Ratchet", _ratchet);
        LiveWindow.addActuator("Catapult", "Winch", _winch);
        LiveWindow.addActuator("Catapult", "Winch shifter", _winchShift);
        LiveWindow.addActuator("Catapult", "Latch", _latch);
        LiveWindow.addActuator("Catapult", "Magnetic Encoder", _magEnc);
//        LiveWindow.addSensor("Catapult", "Winch one", _winchOne);
//        LiveWindow.addSensor("Catapult", "Winch two", _winchTwo);
    }
    
    protected void initDefaultCommand() {
       CommandGroup wait = new CommandGroup("wait");
       wait.addSequential(new PreLaunch());
       wait.addSequential(new DoNothing());
//        setDefaultCommand(wait);
    }
    
    public void setWinchPower(double winchRawVal){
        if(_rachetEngaged && winchRawVal <= 0) {
            winchRawVal = 0;
        }
        winchRawVal = -winchRawVal;
        _winch.set(winchRawVal);
    }
    
    public void resetWinchEncoder(){
        _winchEncoder.reset();
    }
    
    public double getWinchDistance(){
        return -_winchEncoder.get();
    }
    
    public void disengageWinch(){
        _winchShift.set(false);
    }
    
    public void engageWinch(){
        _winchShift.set(true);
    }
    
    public void latch() {
        _latch.set(false);
    }
    
    public void unlatch() {
        _latch.set(true);
    }
    
    public void setRatchetLatched(){
        _rachetEngaged = true;
        _ratchet.setAngle(ANGLE_RATCHET_ENGAGED);
    }
    
    public void setRatchetUnlatched(){
        _rachetEngaged = false;
        _ratchet.setAngle(ANGLE_RATCHET_DISENGAGED);
    }
    
    public double getPivotAngle() {
        return 360-_magEnc.getAngle();
    }
    
    public void setFinishedPreLaunch(boolean finished){
       preLaunchFinished = finished;
    }
    
    public boolean isFinishedPreLaunch(){
       return preLaunchFinished;
    }
    
}
