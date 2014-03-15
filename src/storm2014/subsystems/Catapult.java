package storm2014.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.Robot;
import storm2014.RobotMap;
/**
 * Subsytem that communicates with the catapult components.
 */
public class Catapult extends Subsystem {
    public static final double WINCH_ENCODER_MAX = 895;
    public static double BASE_ANGLE;
    public static final int WINCH_READY = 0;
    public static final int WINCH_CLOSE = 1;
    public static final int WINCH_FAR   = 2;
    
    private final Solenoid        _latch        = new Solenoid(RobotMap.PORT_SOLENOID_LATCH);
    private final Solenoid _solCatapult = new Solenoid(RobotMap.PORT_SOLENOID_CATAPULT);
    
    public Catapult(){
        LiveWindow.addActuator("Catapult", "Latch", _latch);
        _solCatapult.set(true);
    }
    
    protected void initDefaultCommand() {}
    
    public void latch() {
        if(_solCatapult.get())
            _latch.set(false);
    }
    
    public void unlatch() {
        _latch.set(true);
    }
    
    public boolean isLatched(){
        return !_latch.get();
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
