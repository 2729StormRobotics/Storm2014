package storm2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.commands.ChangeArmPosition;
import storm2014.commands.NextWinchPreset;
import storm2014.commands.Launch;
import storm2014.commands.PreLaunch;
import storm2014.commands.ResetCatapult;
import storm2014.commands.Shift;
import storm2014.commands.SpinRoller;
import storm2014.commands.control.Conditional;
import storm2014.commands.control.DoNothing;

/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {
    private final Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
                           shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
                           debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private final Button shiftHigh       = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_HIGH),
                         shiftLow        = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHIFT_LOW),
                         reverse         = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_REVERSE),
                         resetEncoder    = new JoystickButton(driveJoystick, 10),
            
                         spinIn    = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_IN),
                         spinOut   = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_OUT),
                         armsOut   = new InternalButton() {
                            public boolean get() {
                                return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_ARMS) < -0.1;
                            }
                         },
                         armsIn   = new InternalButton() {
                            public boolean get() {
                                return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_ARMS) > 0.1;
                            }
                         },
//                         prefire   = new JoystickButton(shootJoystick, 3),
                         resetCat  = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_RESET),
                         fire      = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_FIRE),
                         safety    = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SAFETY),
                         tension   = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TENSION),
            
                         preconfig = new JoystickButton(debugJoystick, RobotMap.JOYDEBUG_BUTTON_PRECONFIG);
    
    private final Button continueProcess = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_CONTINUE);
    
    private int _intake = 0;
    
    private Command _changeIntake(final int diff) {
        return new DoNothing() {
            protected void initialize() {
                _intake += diff;
                if(_intake >= 1) {
                    Robot.intake.spinIn();
                } else if(_intake <= -1) {
                    Robot.intake.spinOut();
                } else {
                    Robot.intake.stop();
                }
            }
            protected boolean isFinished() {
                return true;
            }
        };
    }
    
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
        resetEncoder.whenPressed(new DoNothing() {
            protected void initialize() {
                Robot.driveTrain.clearEncoders();
            }
            protected boolean isFinished() {
                return true;
            }
        });
        
        spinIn   .whenPressed (_changeIntake(1));
        spinIn   .whenReleased(_changeIntake(-1));
        spinOut  .whenPressed (_changeIntake(-1));
        spinOut  .whenReleased(_changeIntake(1));
        
        armsIn   .whenPressed (new ChangeArmPosition(-1));
        armsOut  .whenPressed (new ChangeArmPosition(1));
//        prefire  .whenPressed (new PreLaunch());
        resetCat .whenPressed (new ResetCatapult());
        fire     .whenPressed (new Conditional(new Launch(),null) {
            protected boolean condition() {
                return safety.get();
            }
        });
        tension  .whenPressed (new NextWinchPreset());
        
        preconfig.whenPressed(Robot.catapult._getPreconfigureCommand());
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

    public boolean isContinueButton() {
        return continueProcess.get();
    }
}
