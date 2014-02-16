package storm2014.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.commands.TiltByJoystick;
import storm2014.utilities.LimitSwitchedMotor;
import storm2014.utilities.StringPotToAngle;
import storm2014.utilities.StringPot;

//TODO waiting for joes array value list.
public class Tilter extends Subsystem {
    private static final double STRINGPOT_MAX_SAFE = 2.85,
                                STRINGPOT_MIN_SAFE = 1.295;
    
    private final StringPot _stringpot = new StringPot(RobotMap.PORT_SENSOR_STRINGPOT);
    private final Talon _tiltMotor = new Talon(RobotMap.PORT_MOTOR_TILTER);
    private final boolean _topLimitOnState = true;
//    private final DigitalInput _topLimitSwitch = new DigitalInput(RobotMap.PORT_LIMIT_TILTER_TOP);
    private final Trigger _topLimitTrigger = new Trigger() {
                              public boolean get() {
                                  return isTopLimitTriggered();
                              }
                          },
                          _bottomLimitTrigger = new Trigger() {
                              public boolean get() {
                                  return isBottomLimitTriggered();
                              }
                          };
    private final LimitSwitchedMotor _limitedMotor = new LimitSwitchedMotor(
                                                            _tiltMotor,
                                                            _bottomLimitTrigger, true,
                                                            _topLimitTrigger, _topLimitOnState);

    public Tilter() {
        LiveWindow.addSensor("Tilter","String Pot",_stringpot);
        LiveWindow.addSensor("Tilter","Motor",_tiltMotor);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new TiltByJoystick());
    }

    public void setRawVal(double speed) {
        _limitedMotor.set(speed);
    }

    public boolean isTopLimitTriggered() {
        return _stringpot.get() > STRINGPOT_MAX_SAFE;
    }
    public boolean isBottomLimitTriggered() {
        return _stringpot.get() < STRINGPOT_MIN_SAFE;
    }

    public double getAngle() {
        return StringPotToAngle.spotToAngle(_stringpot.get());
    }
    public double getStringPotRaw() {
        return _stringpot.get();
    }
}
