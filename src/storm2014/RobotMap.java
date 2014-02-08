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
    public static final int PORT_MOTOR_DRIVE_LEFT     = 1;
    public static final int PORT_MOTOR_DRIVE_RIGHT    = 2;
    public static final int PORT_MOTOR_SHOOTER        = 3;
    public static final int PORT_MOTOR_ROLLER         = 4;
    public static final int PORT_MOTOR_TILTER         = 5;
    public static final int PORT_SERVO                = 6;
    public static final int PORT_MOTOR_WINCH          = 7;
    
    
    // Digital I/O Ports
    public static final int PORT_ENCODER_RIGHT_1           = 1;
    public static final int PORT_ENCODER_RIGHT_2           = 2;
    public static final int PORT_ENCODER_LEFT_1            = 3;
    public static final int PORT_ENCODER_LEFT_2            = 4;
    public static final int PORT_SENSOR_HALL_EFFECT        = 5;
    public static final int PORT_SENSOR_TOMAHAWK_IR        = 6;
    public static final int PORT_SENSOR_BALL_IR            = 7;
    public static final int PORT_SENSOR_HALL_EFFECT_ROLLER = 8;
    public static final int PORT_ENCODER_PULLBACKENCODER_2 = 9;
    public static final int PORT_ENCODER_PULLBACKENCODER_1 = 10;
    public static final int PORT_LIMIT_TILTER_TOP          = 11;
    public static final int PORT_LED_RING                  = 12;
    
    // Analog ports
    public static final int PORT_SENSOR_GYRO          = 1;
    public static final int PORT_SENSOR_STRINGPOT     = 2;
    public static final int PORT_SENSOR_POTENTIOMETER = 3;
    
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
