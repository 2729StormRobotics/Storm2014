package storm2014;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.ADXL345_I2C;

import storm2014.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.commands.AutonomousDance;
import storm2014.commands.ForwardDriveByDistance;
import storm2014.commands.SpinUp;
import storm2014.commands.TomahawkRev;
import storm2014.commands.TriangleMovement;
import storm2014.subsystems.LEDStrip;
import storm2014.subsystems.Shooter;
import storm2014.subsystems.Tomahawk;

/** 
 * This is the robot's "Main class" which is run by the VM.
 */
//create allcellaromiter class, send data to dashboard in send sensor method in robot.java
public class Robot extends IterativeRobot {
    // All subsystems are accessible by Robot.name
    public static OI         oi;
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static LEDStrip leds;
    public static Tomahawk tomahawk;
    
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
//    Compressor compressor;
//    Solenoid solenoid1, solenoid2;
//    DigitalInput digiInput;
    
    ADXL345_I2C accelerometer;
    
    private void sendSensorData() {
        SmartDashboard.putNumber("Wheel Speed RPM", shooter.getSpeedRPM());
//        SmartDashboard.putBoolean("Shooter enabled", shooter.getPIDController().isEnable());
        SmartDashboard.putBoolean("bangbang enabled?", shooter.isBangBangControllerEnabled());
        SmartDashboard.putBoolean("takeback enabled?", shooter.isTakeBackEnabled());
        SmartDashboard.putNumber("Shooter val", shooter.getMotorRawVal());
        SmartDashboard.putNumber("accelerometer", accelerometer.getAcceleration(ADXL345_I2C.Axes.kX));
        SmartDashboard.putNumber("accelerometer", accelerometer.getAcceleration(ADXL345_I2C.Axes.kY));
        SmartDashboard.putNumber("accelerometer", accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ));

//        System.out.println("hi");
    }
    
    /** Called on robot boot. */
    public void robotInit() {

        
        driveTrain = new DriveTrain();
        shooter    = new Shooter();
        tomahawk   = new Tomahawk();
        leds       = new LEDStrip();
        // Initialize OI last so it doesn't try to access null subsystems
        oi         = new OI();
        
        
       
//       compressor = new Compressor(RobotMap.Port_Compressor_SwitchChannel,RobotMap.Port_Compressor_RelayChannel);
//        solenoid1 = new Solenoid(RobotMap.Port_Solenoid1_Channel);
//        solenoid2 = new Solenoid(RobotMap.Port_Solenoid2_Channel);
//        digiInput = new DigitalInput(RobotMap.Port_DigitalInput_Channel);
//        LiveWindow.addActuator("Pneumatics", "compressor", compressor);
//        LiveWindow.addActuator("Pneumatics","solenoid1", solenoid1);
//        LiveWindow.addActuator("Pneumatics","solenoid2", solenoid2);
//        LiveWindow.addSensor("Pneumatics","digiInput", digiInput);

        // The names, and corresponding Commands of our autonomous modes
        autonomiceNames = new String[]{"TakeItBackNowYall","Triangle Movement","AutonomousDance"};
        autonomice = new Command[]{new ForwardDriveByDistance(0.6, 1000),new TriangleMovement(1500), new AutonomousDance(1000.0)};
        
        accelerometer = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k2G);
        
        // Configure and send the SendableChooser, which allows autonomous modes
        // to be chosen via radio button on the SmartDashboard
        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);
        SmartDashboard.putData(Scheduler.getInstance());
        
        // Send sensor info to the SmartDashboard periodically
        new Command("Sensor feedback") {
            protected void initialize() {}
            protected void execute() {
                sendSensorData();
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {
                end();
            }
        }.start();
//        System.out.println("Thingy");
//        SmartDashboard.putData("Shooter PID 2",shooter.getPIDController());
          SmartDashboard.putData(new SpinUp(1500));
          SmartDashboard.putData(new TomahawkRev());
          leds.initTable(NetworkTable.getTable("SmartDashboard"));
    }

    /** Called at the start of autonomous mode. */
    public void autonomousInit() {
        SmartDashboard.putBoolean("Enabled", true);
        if (teleop != null) {
            teleop.cancel();
        }
        autonomouse = (Command) chooser.getSelected();
        if (autonomouse != null) {
            autonomouse.start();
        }
        leds.setMode(LEDStrip.MarqueeMode);
    }

    /**
     * Called during autonomous whenever a new driver station packet arrives
     * (about every 1/50 of a second).
     */
    public void autonomousPeriodic() {
        // Runs commands & stuff.
        Scheduler.getInstance().run();
    }

    /** Called at the start of teleop mode. */
    public void teleopInit() {
        SmartDashboard.putBoolean("Enabled", true);
        if (autonomouse != null) {
            autonomouse.cancel();
        }
        if (teleop != null) {
            teleop.start();
        }
        leds.setMode(LEDStrip.ColorCycleMode);
    }

    /**
     * Called during teleop whenever a new driver station packet arrives (about
     * every 1/50 of a second).
     */
    public void teleopPeriodic() {
        // Runs commands & stuff
        Scheduler.getInstance().run();
    }

    /** Called at the start of test mode */
    public void testInit() {
        SmartDashboard.putBoolean("Enabled", false);
    }

    /**
     * Called during test whenever a new driver station packet arrives (about
     * every 1/50 of a second).
     */
    public void testPeriodic() {
        // Updates sensors & actuators on the LiveWindow
        LiveWindow.run();
    }

    /** Called after any of the other modes ends. */
    public void disabledInit() {
        SmartDashboard.putBoolean("Enabled", false);
        if(autonomouse != null) {
            autonomouse.cancel();
        }
        if(teleop != null) {
            teleop.cancel();
        }
        leds.setMode(LEDStrip.DisabledMode);
    }
    /**
     * Called during disabled whenever a new driver station packet arrives
     * (about every 1/50 of a second). We only have it overridden so we don't
     * get "Override me!" messages.
     */
    public void disabledPeriodic() {
        sendSensorData();
    }
}
