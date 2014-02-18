package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.Robot;
import storm2014.RobotMap;
import storm2014.commands.PreLaunch;
import storm2014.utilities.Debouncer;
import storm2014.utilities.MagneticEncoder;
/**
 * Subsytem that communicates with the catapult components.
 */
public class Catapult extends Subsystem {
    
    //Full Power is -650 on the encoder
    public static final double WINCH_ENCODER_MAX = 600;
    public static final double BASE_ANGLE = 238;
    
    private final Talon           _winch        = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private final Encoder         _winchEncoder = new Encoder(RobotMap.PORT_ENCODER_WINCH_1,RobotMap.PORT_ENCODER_WINCH_2);
    private final Solenoid        _latch        = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
    private final Solenoid        _ratchet      = new Solenoid(RobotMap.PORT_SOLENOID_RATCHET);
    private final MagneticEncoder _magEnc       = new MagneticEncoder(RobotMap.PORT_SENSOR_MAG_ENCODER);
    private final DigitalInput    _winchZero    = new DigitalInput(RobotMap.PORT_SENSOR_WINCH_ZERO);
//    private final DigitalInput    _pawlSwitch   = new DigitalInput(RobotMap.PORT_SENSOR_SWITCH_PAWL);
    
    private boolean preLaunchFinished;
    private static final double ANGLE_RATCHET_ENGAGED    = 170;
    private static final double ANGLE_RATCHET_DISENGAGED = 0;
//    private final Debouncer _ratchetDisengaged = new Debouncer(0.25);
    
    public final double [] pullBackPresets = new double[]{100, 283, 467, 610}; //presets are based on negation already in code
    public int presetIndex = 0;
    private boolean _isPawlEngaged = true;
    
    
    public Catapult(){
        _winchEncoder.start();
        setRatchetLatched();
        LiveWindow.addSensor("Catapult", "Winch Encoder", _winchEncoder);
        LiveWindow.addActuator("Catapult", "Ratchet", _ratchet);
        LiveWindow.addActuator("Catapult", "Winch", _winch);
        LiveWindow.addActuator("Catapult", "Latch", _latch);
        LiveWindow.addActuator("Catapult", "Magnetic Encoder", _magEnc);
        LiveWindow.addActuator("Catapult", "Winch Zero", _winchZero);
    }
    
    protected void initDefaultCommand() {
       CommandGroup wait = new CommandGroup("wait");
       wait.addSequential(new PreLaunch());
//       wait.addSequential(new TensionWinch());
       wait.addSequential(new Command("Winch control") {
            {
                requires(Robot.catapult);
            }
            protected void initialize() {
                Robot.catapult.setIndex(0);
            }

            protected void execute() {
                Robot.catapult.setWinchPower(Robot.oi.getTension());
            }

            protected boolean isFinished() {
                return false;
            }
            protected void end() {
                Robot.catapult.setWinchPower(0);
            }
            protected void interrupted() {
                end();
            }
        });
        setDefaultCommand(wait);
    }
    
    public boolean isWinchZeroTriggered() {
        return !_winchZero.get();
    }
    
    public boolean isRatchetEngaged() {
        return _isPawlEngaged;//!_ratchet.get();//!_ratchetDisengaged.check(_pawlSwitch.get());
    }
    
    private boolean prevZero = false;
    
    public void setWinchPower(double winchRawVal){
        if(isRatchetEngaged() && winchRawVal <= 0) {
            winchRawVal = 0;
        }
        boolean zero = isWinchZeroTriggered();
        if(zero) {
            winchRawVal = Math.max(0,winchRawVal);
            if(!prevZero) {
                resetWinchEncoder();
            }
        }
        prevZero = zero;
//        if(getWinchDistance() > WINCH_ENCODER_MAX && winchRawVal > 0) {
//            winchRawVal = 0;
//        }
        winchRawVal = -winchRawVal;
        if(_isPawlEngaged && Math.abs(winchRawVal) > 0.1) {
            setRatchetUnlatched();
            _isPawlEngaged = true;
        } else if(_isPawlEngaged) {
            setRatchetLatched();
        }
        _winch.set(winchRawVal);
    }
    
    public void resetWinchEncoder(){
        _winchEncoder.reset();
    }
    
    public double getWinchDistance(){
        return -_winchEncoder.get();
    }
    
    public void latch() {
        _latch.set(false);
    }
    
    public void unlatch() {
        _latch.set(true);
    }
    
    public void setRatchetLatched(){
        _winch.set(0);
        _ratchet.set(false);
        _isPawlEngaged = true;
    }
    
    public void setRatchetUnlatched(){
        _winch.set(0);
        isRatchetEngaged();
        _ratchet.set(true);
        _isPawlEngaged = false;
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
    
    public void setIndex(int newIndex){
        presetIndex = Math.max(0, Math.min(newIndex,pullBackPresets.length-1));
    }

    public int getCurrentIndex(){
        return presetIndex;
    }
    
    public double getCurrentPreset(){
        return pullBackPresets[presetIndex];
    }
    public int getNumPresets() {
        return pullBackPresets.length;
    }
}
