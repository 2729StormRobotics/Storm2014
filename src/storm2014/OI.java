package storm2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import storm2014.commands.PickupBall;

import storm2014.commands.SetLEDMode;
import storm2014.commands.SpinUp;
import storm2014.commands.TomahawkRev;


/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {
    private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
//                           shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
            debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private final Button slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW);
    
    private final Button spinUp = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SPINUP);
    
    private final Button tomahawk = new JoystickButton(driveJoystick,RobotMap.JOYTOMAHAWK_BUTTON);
    
    private final Button pickup   = new JoystickButton(driveJoystick, RobotMap.JOYPICKUP_BUTTON);
    
    public OI() {
        spinUp.whenPressed(new SpinUp(1500.0));
        tomahawk.whenPressed(new TomahawkRev());
        pickup.toggleWhenPressed(new PickupBall());
        
    }
    
    // When a joystick is in its zero position, it will not necessarily read
    // zero. This makes sure that everything within that range reads zero.
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
    }
    
    public double getLeftDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_LEFT),0.15)*(slowModeButton.get() ? 0.7 : 1);
    }
    
    public double getRightDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_RIGHT),0.15)*(slowModeButton.get() ? 0.7 : 1);
    }
}

