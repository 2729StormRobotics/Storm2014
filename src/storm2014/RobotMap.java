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
    public static final int JOYTOMAHAWK_BUTTON      = 10; 
    public static final int JOYPICKUP_BUTTON        = 9; 
    public static final int JOYARMPOS_BUTTON        = 8;
    
    // public static final int JOYDEBUG_BUTTON_CONTROLSHOOT = 10;
    
    
    public static final int JOYDRIVE_AXIS_DRIVE_LEFT  = 2,
            JOYDRIVE_AXIS_DRIVE_RIGHT = 4;
    
    public static final int PORT_MOTOR_DRIVE_LEFT     = 1;
    public static final int PORT_MOTOR_DRIVE_RIGHT    = 10;
    public static final int PORT_MOTOR_SHOOTER        = 3;
    public static final int PORT_MOTOR_WINCH          = 2;
    public static final int PORT_MOTOR_ROLLER         = 4;
    public static final int PORT_MOTOR_TOMAHAWK       = 5;
    public static final int PORT_MOTOR_TILTER         = 6;
    
    public static final int PORT_ENCODER_LEFT_1            = 3;
    public static final int PORT_ENCODER_LEFT_2            = 4;
    public static final int PORT_ENCODER_RIGHT_1           = 2;
    public static final int PORT_ENCODER_RIGHT_2           = 1;
    public static final int PORT_ENCODER_PULLBACKENCODER_1 = 10;
    public static final int PORT_ENCODER_PULLBACKENCODER_2 = 9;
    public static final int PORT_SENSOR_HALL_EFFECT        = 5;
    public static final int PORT_SENSOR_HALL_EFFECT_ROLLER = 8;
    public static final int PORT_SENSOR_TOMAHAWK_IR        = 6;
    public static final int PORT_SENSOR_BALL_IR            = 7;
    public static final int PORT_SENSOR_STRINGPOT          = 11; 
    public static final int MODULE_SENSOR_ACCELEROMETER    = 1;
    public static final int PORT_SENSOR_POTENTIOMETER      = 12;
    
    public static final int PORT_SENSOR_GYRO          = 1;
    
//    public static final int Port_Compressor_SwitchChannel = 5;
//    public static final int Port_Compressor_RelayChannel  = 2;
    
//
    public static final int PORT_SOLENOID_DISENGAGE_CHANNEL      = 1;
    public static final int PORT_SOLENOID_ENGAGE_CHANNEL         = 2;
    public static final int PORT_SOLENOID_LATCHED_CHANNEL        = 7;
    public static final int PORT_SOLENOID_UNLATCHED_CHANNEL      = 8;
    public static final int PORT_SOLENOID_LEFTSHIFTDOWN_CHANNEL  = 3;
    public static final int PORT_SOLENOID_RIGHTSHIFTDOWN_CHANNEL = 4;
    public static final int PORT_SOLENOID_LEFTSHIFTUP_CHANNEL    = 5;
    public static final int PORT_SOLENOID_RIGHTSHIFTUP_CHANNEL   = 6;
    public static final int PORT_SOLENOID_INTAKE_TOP_CHANNEL  = 9;
    public static final int PORT_SOLENOID_INTAKE_BOT_CHANNEL  = 10;
//
//    public static final int Port_Solenoid1_Channel = 1;
//    public static final int Port_Solenoid2_Channel = 2;
    
    
    
}
