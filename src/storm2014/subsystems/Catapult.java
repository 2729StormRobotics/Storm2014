package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.Robot;
import storm2014.RobotMap;
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
    private final MagneticEncoder _magEnc       = new MagneticEncoder(RobotMap.PORT_SENSOR_MAG_ENCODER);
    private final DigitalInput    _winchZero    = new DigitalInput(RobotMap.PORT_SENSOR_WINCH_ZERO);
    private final Solenoid _solCatapult = new Solenoid(RobotMap.PORT_SOLENOID_CATAPULT);
    
    private boolean _catapultOut = false;
    
    public Catapult(){
        _winchEncoder.start();
        LiveWindow.addSensor("Catapult", "Winch Encoder", _winchEncoder);
        LiveWindow.addActuator("Catapult", "Winch", _winch);
        LiveWindow.addActuator("Catapult", "Latch", _latch);
        LiveWindow.addActuator("Catapult", "Magnetic Encoder", _magEnc);
        LiveWindow.addActuator("Catapult", "Winch Zero", _winchZero);
    }
    
    protected void initDefaultCommand() {}
    
    public void latch() {
        _latch.set(false);
    }
    
    public void unlatch() {
        _latch.set(true);
    }
    
    public boolean isLatched(){
        return !_latch.get();
    }
    
    public double getPivotAngle() {
        return 360-_magEnc.getAngle();
    }
    
    public boolean isCatapultOut() {
        return _catapultOut;
    }
    public void setCatapultOut(boolean out) {
        _catapultOut = out;
    }
    
    public void fireCatapult(){
        if(Robot.intake.armSafe())
            _solCatapult.set(false);
    }
    public void resetCatapult(){
        if(Robot.intake.armSafe())
            _solCatapult.set(true);
    }
}
