package storm2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import storm2014.commands.SetEngagedRatchet;
import storm2014.commands.SpinRollerIn;
import storm2014.commands.SetLEDMode;


/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {
    private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
//                           shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
            debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private final Button slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW);
    
    private final Button spinIn   = new JoystickButton(driveJoystick, RobotMap.JOYPICKUP_BUTTON);
    
    private Button ratchetEngage = new JoystickButton(driveJoystick, RobotMap.JOYRATCHET_ENGAGE_BUTTON);
    private Button ratchetDisengage = new JoystickButton(driveJoystick, RobotMap.JOYRATCHET_DISENGAGE_BUTTON);
    
    public OI() {
        spinIn.toggleWhenPressed(new SpinRollerIn());
        ratchetEngage.whenPressed(new SetEngagedRatchet(true));
        ratchetDisengage.whenPressed(new SetEngagedRatchet(false));
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

