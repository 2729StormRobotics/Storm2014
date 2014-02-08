package storm2014;

/**
 * Holds all of our hardware/joystick mappings, so they're pretty easy to configure.
 */
public class RobotMap {
    public static final int PORT_JOYSTICK_DRIVE = 1,
                            PORT_JOYSTICK_SHOOT = 2,
                            PORT_JOYSTICK_DEBUG = 3;
    
    public static final int JOYDRIVE_BUTTON_SLOW    = 5;
    public static final int JOYDRIVE_BUTTON_SPINUP  = 3;
    public static final int JOYSHOOT_BUTTON_SPIN_IN = 9;
    
    public static final int JOYDRIVE_AXIS_DRIVE_LEFT  = 2;
    public static final int JOYDRIVE_AXIS_DRIVE_RIGHT = 4;
    
    // PWM Ports
    public static final int PORT_MOTOR_DRIVE_LEFT     = 1;
    public static final int PORT_MOTOR_DRIVE_RIGHT    = 2;
    public static final int PORT_MOTOR_SHOOTER        = 3;
    public static final int PORT_MOTOR_ROLLER         = 4;
    public static final int PORT_MOTOR_TOMAHAWK       = 5;
    public static final int PORT_MOTOR_TILTER         = 6;
    public static final int PORT_SERVO                = 7;
    public static final int PORT_MOTOR_WINCH          = 10;
    
    // Digital I/O Ports
    public static final int PORT_ENCODER_RIGHT_1           = 1;
    public static final int PORT_ENCODER_RIGHT_2           = 2;
    public static final int PORT_ENCODER_LEFT_1            = 3;
    public static final int PORT_ENCODER_LEFT_2            = 4;
    public static final int PORT_SENSOR_BALL_IR            = 7;
    public static final int PORT_SENSOR_HALL_EFFECT_ROLLER = 8;
    public static final int PORT_ENCODER_PULLBACKENCODER_2 = 9;
    public static final int PORT_ENCODER_PULLBACKENCODER_1 = 10;
    
    // Analog ports
    public static final int PORT_SENSOR_GYRO          = 1;
    public static final int PORT_SENSOR_STRINGPOT     = 2;
    public static final int PORT_SENSOR_POTENTIOMETER = 3;
    
    // I2C on the Digital Sidecar
    public static final int MODULE_SENSOR_ACCELEROMETER = 1;
        
    // Solenoid Ports
    public static final int PORT_SOLENOID_DISENGAGE      = 1;
    public static final int PORT_SOLENOID_ENGAGE         = 2;
    public static final int PORT_SOLENOID_LEFT_SHIFTER   = 3;
    public static final int PORT_SOLENOID_RIGHT_SHIFTER  = 4;
    public static final int PORT_SOLENOID_LATCHED        = 5;
    public static final int PORT_SOLENOID_UNLATCHED      = 6;
    public static final int PORT_SOLENOID_INTAKE_TOP     = 7;
    public static final int PORT_SOLENOID_INTAKE_BOTTOM  = 8;
}
