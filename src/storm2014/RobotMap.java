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
    public static final int JOYDRIVE_BUTTON_AUTOALIGN = 6;
    public static final int JOYSHOOT_BUTTON_SPIN_IN = 9;
    
    public static final int JOYDRIVE_AXIS_DRIVE_LEFT  = 2;
    public static final int JOYDRIVE_AXIS_DRIVE_RIGHT = 4;
    
    // PWM Ports
    public static final int PORT_MOTOR_DRIVE_LEFT     = 2;
    public static final int PORT_MOTOR_DRIVE_RIGHT    = 1;
    public static final int PORT_MOTOR_ROLLER         = 3;
    public static final int PORT_MOTOR_TILTER         = 4;
    public static final int PORT_SERVO                = 5;
    public static final int PORT_MOTOR_WINCH          = 6;
    
    // Digital I/O Ports
    public static final int PORT_ENCODER_RIGHT_1  =  1;
    public static final int PORT_ENCODER_RIGHT_2  =  2;
    public static final int PORT_ENCODER_LEFT_1   =  3;
    public static final int PORT_ENCODER_LEFT_2   =  4;
    public static final int PORT_SENSOR_BALL_IR   =  5;
    public static final int PORT_ENCODER_WINCH_1  =  7;
    public static final int PORT_ENCODER_WINCH_2  =  6;
    public static final int PORT_LED_RING         =  8;
    public static final int PORT_STATIC_LED_GREEN =  9;
    public static final int PORT_STATIC_LED_RED   = 10;
    public static final int PORT_STATIC_LED_BLUE  = 11;
    public static final int PORT_SWITCH_COMPRESSO = 12;
    
    //Relays
    public static final int PORT_RELAY_COMPRESSOR = 1;
    
    // Analog ports
    public static final int PORT_SENSOR_GYRO        = 1;
    public static final int PORT_SENSOR_STRINGPOT   = 2;
    public static final int PORT_SENSOR_MAG_ENCODER = 3;
    
    // I2C on the Digital Sidecar
    public static final int MODULE_SENSOR_ACCELEROMETER = 1;
        
    // Solenoid Ports
    public static final int PORT_SOLENOID_WINCH             = 1;
    public static final int PORT_SOLENOID_SHIFT_HIGH        = 2;
    public static final int PORT_SOLENOID_SHIFT_LOW         = 3;
    public static final int PORT_SOLENOID_LATCH             = 4;
    public static final int PORT_SOLENOID_INTAKE_TOP_IN     = 5;
    public static final int PORT_SOLENOID_INTAKE_TOP_OUT    = 6;
    public static final int PORT_SOLENOID_INTAKE_BOTTOM_IN  = 7;
    public static final int PORT_SOLENOID_INTAKE_BOTTOM_OUT = 8;
}
