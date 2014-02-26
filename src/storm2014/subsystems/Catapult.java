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
import storm2014.commands.TensionWinch;
import storm2014.commands.control.Conditional;
import storm2014.utilities.Debouncer;
import storm2014.utilities.MagneticEncoder;
/**
 * Subsytem that communicates with the catapult components.
 */
public class Catapult extends Subsystem {
    public static final double WINCH_ENCODER_MAX = 895;
    public static double BASE_ANGLE;
    public static final int WINCH_READY = 0;
    public static final int WINCH_CLOSE = 1;
    public static final int WINCH_FAR   = 2;
    
    private final Talon           _winch        = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private final Encoder         _winchEncoder = new Encoder(RobotMap.PORT_ENCODER_WINCH_1,RobotMap.PORT_ENCODER_WINCH_2);
    private final Solenoid        _latch        = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
    private final Solenoid        _ratchet      = new Solenoid(RobotMap.PORT_SOLENOID_RATCHET);
    private final MagneticEncoder _magEnc       = new MagneticEncoder(RobotMap.PORT_SENSOR_MAG_ENCODER);
    private final DigitalInput    _winchZero    = new DigitalInput(RobotMap.PORT_SENSOR_WINCH_ZERO);
    
    private boolean preLaunchFinished;
    private static final double ANGLE_RATCHET_ENGAGED    = 170;
    private static final double ANGLE_RATCHET_DISENGAGED = 0;
    
    // dead zone @ 740
    public final double [] pullBackPresets = new double[]{50,720,890};
    public int presetIndex = 0;
    private boolean _isPawlEngaged = true;
    private boolean _firstRun = true;
    private boolean _catapultOut = false;
    
    public Catapult(){
        _winchEncoder.start();
        setRatchetLatched();
        setFinishedPreLaunch(false);
        setPresetIndex(WINCH_READY);
        LiveWindow.addSensor("Catapult", "Winch Encoder", _winchEncoder);
        LiveWindow.addActuator("Catapult", "Ratchet", _ratchet);
        LiveWindow.addActuator("Catapult", "Winch", _winch);
        LiveWindow.addActuator("Catapult", "Latch", _latch);
        LiveWindow.addActuator("Catapult", "Magnetic Encoder", _magEnc);
        LiveWindow.addActuator("Catapult", "Winch Zero", _winchZero);
    }
    
    protected void initDefaultCommand() {
       CommandGroup wait = new CommandGroup("Prep for Launch");
       wait.addSequential(new Conditional(new Command() {
           {
               setInterruptible(false);
           }
           private boolean _zeroFound = false;
           protected void initialize() {
               BASE_ANGLE = getPivotAngle()+2.5;
               System.out.println(BASE_ANGLE);
               _firstRun = false;
               _winchEncoder.reset();
               _zeroFound = false;
               setRatchetUnlatched();
           }

           protected void execute() {
               setWinchPower(0.5);
           }

           protected boolean isFinished() {
               if(isWinchZeroTriggered()) {
                   _zeroFound = true;
               }
               return _zeroFound || getWinchDistance() > 300;
           }

           protected void end() {
               setWinchPower(0);
               setRatchetLatched();
               if(_zeroFound) {
                   _winchEncoder.reset();
               }
           }

           protected void interrupted() {
               end();
           }
       }, null) {
           protected boolean condition() {
               return _firstRun;
           }
       });
       wait.addSequential(new PreLaunch());
       wait.addSequential(new TensionWinch());
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
            if(winchRawVal < 0) {
                winchRawVal = 0;
            }
//            if(!prevZero && winchRawVal > 0) {
//                resetWinchEncoder();
//            }
        }
        prevZero = zero;
        if(getWinchDistance() > WINCH_ENCODER_MAX && winchRawVal > 0) {
            winchRawVal = 0;
        }
        winchRawVal = -winchRawVal;
        if(_isPawlEngaged && Math.abs(winchRawVal) > 0.1) {
            setRatchetUnlatched();
            _isPawlEngaged = true;
        } else if(_isPawlEngaged) {
            setRatchetLatched();
        }
//        System.out.println(winchRawVal);
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
    
    public boolean isCatapultOut() {
        return _catapultOut;
    }
    public void setCatapultOut(boolean out) {
        _catapultOut = out;
    }
    
    public void setPresetIndex(int newIndex){
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
    
    public Command _getPreconfigureCommand() {
        return new Command() {
            protected void initialize() {
                _firstRun = true;
                System.out.println("Will Preconfigure");
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        };
    }
}
