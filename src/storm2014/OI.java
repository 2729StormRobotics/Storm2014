package storm2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.commands.ChangeArmPosition;
import storm2014.commands.Launch;
import storm2014.commands.PreLaunch;
import storm2014.commands.ResetCatapult;
import storm2014.commands.Shift;
import storm2014.commands.SpinRoller;

/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {
    private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
                           shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
                           debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private final Button shiftHigh = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_HIGH),
                         shiftLow  = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_LOW),
                         reverse   = new JoystickButton(driveJoystick, 6),
            
                         spinIn    = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_IN),
                         spinOut   = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_OUT),
                         armsOut   = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_ARMS_OUT),
                         armsIn    = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_ARMS_IN),
                         prefire   = new JoystickButton(shootJoystick, 8),
                         resetCat  = new JoystickButton(shootJoystick, 3),
                         fire      = new JoystickButton(shootJoystick, 6);
    
    public OI() {
        shiftHigh.whenPressed (new Shift(true));
        shiftLow .whenPressed (new Shift(false));
        reverse  .whileHeld   (new Command() {
            protected void initialize() {
                Robot.driveTrain.setReverseDrive(true);
            }

            protected void execute() {}
            protected boolean isFinished() {
                return false;
            }
            protected void end() {
                Robot.driveTrain.setReverseDrive(false);
            }
            protected void interrupted() {
                end();
            }
        });
        
        spinIn   .whenPressed (new SpinRoller(1));
        spinIn   .whenReleased(new SpinRoller(0));
        spinOut  .whenPressed (new SpinRoller(-1));
        spinOut  .whenReleased(new SpinRoller(0));
        
        armsIn   .whenPressed (new ChangeArmPosition(-1));
        armsOut  .whenPressed (new ChangeArmPosition(1));
        prefire  .whenPressed (new PreLaunch());
        resetCat .whenPressed (new ResetCatapult());
        fire     .whenPressed (new Launch() {
            protected boolean thisIsIntentional() {
                return true;
            }
        });
    }
    
    // When a joystick is in its zero position, it will not necessarily read
    // zero. This makes sure that everything within that range reads zero.
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
    }
    
    public double getLeftDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_LEFT),0.15);
    }
    
    public double getRightDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_RIGHT),0.15);
    }
    
    
    public double getTension() {
        return _zeroDeadzone(-shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_TENSION),0.15);
    }

    public double getTilt() {
        return _zeroDeadzone(-shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_TILT),0.15);
    }
}

