package storm2014.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.commands.TankDrive;

/** Subsystem including the drive motors, encoders, and gyro. */

public class DriveTrain extends Subsystem {
    
    private final Talon _left  = new Talon(RobotMap.PORT_MOTOR_DRIVE_LEFT),
                        _right = new Talon(RobotMap.PORT_MOTOR_DRIVE_RIGHT);
    
    private final RobotDrive _drive = new RobotDrive(_left,_right);
    
    private final Encoder _leftEncoder =  new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
    private final Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
     
    private final Gyro _gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
    private double _gyroOffset = 0;
    
    private final DoubleSolenoid _shifter  = new DoubleSolenoid(RobotMap.PORT_SOLENOID_SHIFT_HIGH,RobotMap.PORT_SOLENOID_SHIFT_LOW);
    private boolean _isHighGear = false;
    
    private boolean _reverseDrive = false;
    
    public DriveTrain() {
        _leftEncoder.start();
        _rightEncoder.start();
	
        LiveWindow.addSensor  ("Drive Train", "Gyro", _gyro);
        LiveWindow.addSensor  ("Drive Train", "Left Encoder", _leftEncoder);
        LiveWindow.addSensor  ("Drive Train", "Right Encoder", _rightEncoder);
        LiveWindow.addActuator("Drive Train", "Shifter", _shifter);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
    
    /** Drives in tank mode (each motor gets 1 = forward,-1 = back). */
    public void tankDrive(double leftValue, double rightValue) {
        if(!_reverseDrive) {
            _drive.tankDrive(-leftValue, -rightValue);
        } else {
            _drive.tankDrive(rightValue, leftValue);
        }
    }

    /** Reads the left encoder (+ = forward,- = back). */
     public double getLeftDistance() {
        return _leftEncoder.get();
    }

     /** Reads the right encoder (+ = forward,- = back). */
    public double getRightDistance() {
        return -_rightEncoder.get();
    }
    
    /** Resets both encoders. */
    public void clearEncoders(){
        _leftEncoder.reset();
        _rightEncoder.reset();
    }
    
    /** Resets the gyro. May take a while */
    public void resetGyro(){
        _gyro.reset();
        clearGyro();
    }
    /** Makes gyro readings relative to current */
    public void clearGyro(){
        _gyroOffset = _gyro.getAngle();
    }
    
    /**
     * Reads the Robot's (relative) heading from gyro (+ = clockwise,
     * - = counterclockwise).
     */
    public double getGyroAngle(){
        return _gyro.getAngle()-_gyroOffset;
    }
    
    /** Reads the [-1,1] value set for the left wheels. */
    public double getLeftSpeed(){
        return _left.get();
    }
    
    /** Reads the [-1,1] value set for the right wheels. */
    public double getRightSpeed(){
        return _right.get();
    }
    public void setHighGear(boolean enabled) {
        _isHighGear = enabled;
        _shifter.set(enabled ? DoubleSolenoid.Value.kForward :
                               DoubleSolenoid.Value.kReverse);
    }
    public boolean isHighgear() {
        return _isHighGear;
    }
    
    public void setReverseDrive(boolean enabled) {
        _reverseDrive = enabled;
    }
    public boolean getReverseDrive() {
        return _reverseDrive;
    }
}
