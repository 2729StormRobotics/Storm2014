package storm2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import storm2014.commands.AutoAlign;
import storm2014.commands.ChangeArmPosition;
import storm2014.commands.SetLEDMode;
import storm2014.commands.Shift;
import storm2014.commands.SpinRoller;

/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {
    private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
//                           shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
            debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private final Button slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW);
    
    private final Button spinIn   = new JoystickButton(driveJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_IN),
                         armsOut  = new JoystickButton(driveJoystick, 8),
                         armsIn   = new JoystickButton(driveJoystick, 6),
                         shift    = new JoystickButton(driveJoystick, 5);
    
    private Button autoAlign = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_AUTOALIGN);
    
    public OI() {
        autoAlign.whenPressed(new AutoAlign());
        spinIn.whenPressed(new SpinRoller(1));
        spinIn.whenReleased(new SpinRoller(0));
        armsIn.whenPressed(new ChangeArmPosition(-1));
        armsOut.whenPressed(new ChangeArmPosition(1));
        shift.whenPressed(new Shift());
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

