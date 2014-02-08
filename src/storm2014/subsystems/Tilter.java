package storm2014.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.commands.ConvertToAngle;
import storm2014.commands.Tilt;
import storm2014.utilities.LimitSwitchedMotor;
import storm2014.utilities.StringPot;

/**
 *
 * @author garrett
 */
//TODO waiting for joes array value list.

public class Tilter extends Subsystem{
    
    private StringPot _stringPot = new StringPot(RobotMap.PORT_SENSOR_STRINGPOT);
    private Talon _talon = new Talon(RobotMap.PORT_MOTOR_TILTER);
    private ConvertToAngle _convert = new ConvertToAngle();
    private final boolean _topLimitOnState = true;
    private final DigitalInput _topLimitSwitch = new DigitalInput(RobotMap.PORT_LIMIT_TILTER_TOP);
    private Trigger _topLimitTrigger = new Trigger(){
                                           public boolean get(){
                                               return isTopLimitTriggered();
                                           }
                                        },
            _bottomLimitTrigger = null;
     private LimitSwitchedMotor _limitedMotor = new LimitSwitchedMotor(_talon,
                                                                      _bottomLimitTrigger, true,
                                                                      _topLimitTrigger,    _topLimitOnState);
    
    protected void initDefaultCommand() {
        setDefaultCommand(new Tilt(0.0));
    }
    
    public void setRawVal(double speed){
        _limitedMotor.set(speed);
    }
    
    public double getAngle(){
        return  _convert.getAngle(_stringPot.get());

    }
    
    public boolean isTopLimitTriggered() {
        return _stringPot.get() > StringPot.VAL_MAX_SAFE || _topLimitSwitch.get() == _topLimitOnState;
    }
    
}
