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
import storm2014.utilities.Debouncer;
import storm2014.utilities.MagneticEncoder;

public class Catapult extends Subsystem {
    //Full Power is -650 on the encoder
    public static final double WINCH_ENCODER_MAX = 600;
    public static final double BASE_ANGLE = 230;
    
    private final Talon           _winch        = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private final Encoder         _winchEncoder = new Encoder(RobotMap.PORT_ENCODER_WINCH_1,RobotMap.PORT_ENCODER_WINCH_2);
    private final Solenoid        _winchShift   = new Solenoid(RobotMap.PORT_SOLENOID_WINCH);
    private final Solenoid        _latch        = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
    private final Servo           _ratchet      = new Servo(RobotMap.PORT_SERVO);
    private final MagneticEncoder _magEnc       = new MagneticEncoder(RobotMap.PORT_SENSOR_MAG_ENCODER);
    private final DigitalInput    _pawlSwitch   = new DigitalInput(RobotMap.PORT_SENSOR_SWITCH_PAWL);
    
    private boolean preLaunchFinished;
    private static final double ANGLE_RATCHET_ENGAGED    = 170;
    private static final double ANGLE_RATCHET_DISENGAGED = 0;
    private final Debouncer _ratchetDisengaged = new Debouncer(0.25);
    
    public Catapult(){
        _winchEncoder.start();
        setRatchetUnlatched();
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
       setDefaultCommand(wait);
    }
    
    public boolean isRatchetEngaged() {
        return !_ratchetDisengaged.check(_pawlSwitch.get());
    }
    
    public void setWinchPower(double winchRawVal){
        if(isRatchetEngaged() && winchRawVal <= 0) {
            winchRawVal = 0;
        }
//        if(getWinchDistance() > WINCH_ENCODER_MAX && winchRawVal > 0) {
//            winchRawVal = 0;
//        }
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
        _winch.set(0);
        _ratchet.setAngle(ANGLE_RATCHET_ENGAGED);
    }
    
    public void setRatchetUnlatched(){
        _winch.set(0);
        isRatchetEngaged();
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
