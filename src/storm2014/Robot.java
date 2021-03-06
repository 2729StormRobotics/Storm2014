package storm2014;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;

import storm2014.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.commands.DriveForward;
import storm2014.commands.PreFire;
import storm2014.commands.SetArmPosition;
import storm2014.commands.autonomous.DriveAndShoot;
import storm2014.commands.autonomous.DriveAndShoot2Ball;
import storm2014.commands.autonomous.DriveAndShootNoWait;
import storm2014.commands.control.Conditional;
import storm2014.subsystems.Catapult;
import storm2014.subsystems.Intake;
import storm2014.subsystems.LEDRing;
import storm2014.subsystems.LEDStrip;
import storm2014.subsystems.StaticLEDStrip;
import storm2014.utilities.pipeline.FilterTask;
import storm2014.utilities.pipeline.ISource;
import storm2014.utilities.pipeline.LowPassFilter;

/**
 * This is the robot's "Main class" which is run by the VM. 
 */
//create allcellaromiter class, send data to dashboard in send sensor method in robot.java
public class Robot extends IterativeRobot {
    // All subsystems are accessible by Robot.name
    public static OI             oi;
    public static DriveTrain     driveTrain;
    public static LEDStrip       leds;
    public static Intake         intake;
    public static Catapult       catapult;
    public static LEDRing        ledring;
    public static StaticLEDStrip staticleds;
    Compressor compressor;
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
    private void sendSensorData() {
         SmartDashboard.putNumber("Gyro", driveTrain.getGyroAngle());
         SmartDashboard.putNumber("Left Distance", driveTrain.getLeftDistance());
         SmartDashboard.putNumber("Right Distance", driveTrain.getRightDistance());
//         SmartDashboard.putNumber("Left speed", driveTrain.getLeftSpeedEnc());
//         SmartDashboard.putNumber("Right speed", driveTrain.getRightSpeedEnc());
         SmartDashboard.putString("Gear", driveTrain.isHighgear() ? "High gear" : "Low gear");
         SmartDashboard.putBoolean("Latch Engaged", catapult.isLatched());
         SmartDashboard.putString("Arm mode", intake.getModeName());
         SmartDashboard.putBoolean("Compressed", compressor.getPressureSwitchValue());
         SmartDashboard.putBoolean("Arms down", intake.armSafe());
    }
    
    /** Called on robot boot. */
    public void robotInit() {
        catapult   = new Catapult();
        driveTrain = new DriveTrain();
        leds       = new LEDStrip();
        intake     = new Intake();
        ledring    = new LEDRing();
        staticleds = new StaticLEDStrip();
        compressor = new Compressor(RobotMap.PORT_SWITCH_COMPRESSO, RobotMap.PORT_RELAY_COMPRESSOR);
        compressor.start();
        
        // Initialize OI last so it doesn't try to access null subsystems
        oi         = new OI();
        
        //SmartDashboard.putBoolean("Wait longer", true);
        SmartDashboard.putData("Arms out", new SetArmPosition(2));
        SmartDashboard.putData("Arms in", new SetArmPosition(0));
//        SmartDashboard.putData("Prefire", new PreFire());
        CommandGroup armsMoveWait = new CommandGroup();
        armsMoveWait.addSequential(new PrintCommand("Arms up"));
        armsMoveWait.addSequential(new SetArmPosition(0, false));
        armsMoveWait.addSequential(new PrintCommand("Arms are up"));
        armsMoveWait.addSequential(new WaitCommand(0.5));
        armsMoveWait.addSequential(new PrintCommand("Arms down"));
        armsMoveWait.addSequential(new SetArmPosition(2, false));
        armsMoveWait.addSequential(new PrintCommand("Arms are down"));
        SmartDashboard.putData("Arms move wait", armsMoveWait);
        CommandGroup armsMoveNoWait = new CommandGroup();
        armsMoveNoWait.addSequential(new SetArmPosition(0, false));
        armsMoveNoWait.addSequential(new SetArmPosition(2, false));
        SmartDashboard.putData("Arms move no wait", armsMoveNoWait);
        SmartDashboard.putData("Arms in quick", new SetArmPosition(0,false));
        
        // The names, and corresponding Commands of our autonomous modes
        autonomiceNames = new String[]{"Drive Forward","1 Ball Hot","1 Ball Blind","2 Ball"};
        autonomice = new Command[]{new DriveForward(0.8, 5250),new DriveAndShoot(),new DriveAndShootNoWait(),new DriveAndShoot2Ball()};
        
        // Configure and send the SendableChooser, which allows autonomous modes
        // to be chosen via radio button on the SmartDashboard
        System.out.println(autonomice.length + " autonomice");
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);
        SmartDashboard.putData(Scheduler.getInstance());
        
        /*SmartDashboard.putData("Test conditional", new Conditional(new WaitCommand(0.5), new WaitCommand(3.0)) {
            protected boolean condition() {
               return SmartDashboard.getBoolean("Wait longer", false);
            }
        });*/
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
        leds.initTable(NetworkTable.getTable("SmartDashboard"));
        ledring.initTable(NetworkTable.getTable("SmartDashboard"));
        staticleds.initTable(NetworkTable.getTable("SmartDashboard"));
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
        leds.setMode(LEDStrip.USAMode);
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
        leds.setMode(LEDStrip.TeleopMode);
        System.out.println("Init teleop");
    }
    
    /**
     * Called during teleop whenever a new driver station packet arrives (about
     * every 1/50 of a second).q
     */
    public void teleopPeriodic() {
        // Runs commands & stuff
        Scheduler.getInstance().run();
        
        DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
        if (color == DriverStation.Alliance.kBlue){
            SmartDashboard.putBoolean("Blue Alliance?", true);
            
            staticleds.setRed((short) 0);
            staticleds.setGreen((short) 0);
            staticleds.setBlue((short) 255);
        } else if (color == DriverStation.Alliance.kRed){
            SmartDashboard.putBoolean("Blue Alliance?", false);
            
            staticleds.setRed((short) 255);
            staticleds.setGreen((short) 0);
            staticleds.setBlue((short) 0);
        } else {
            SmartDashboard.putBoolean("Blue Alliance?", false);
            
            staticleds.setRed((short) 255);
            staticleds.setGreen((short) 0);
            staticleds.setBlue((short) 255);
        }
    }
    
    /** Called at the start of test mode */
    public void testInit() {
        SmartDashboard.putBoolean("Enabled", false);
        leds.setMode(LEDStrip.StormSpiritMode);
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
        
        intake.setRollerRaw(0);
        
        SmartDashboard.putBoolean("Enabled", false);
        
        if(autonomouse != null) {
            autonomouse.cancel();
        }
        if(teleop != null) {
            teleop.cancel();
        }
        
        leds.setMode(LEDStrip.DisabledMode);
        catapult.UNSAFEFireCatapult(false);
    }
    
    double pulseCount = 0;
    /**
     * Called during disabled whenever a new driver station packet arrives
     * (about every 1/50 of a second). We only have it overridden so we don't
     * get "Override me!" messages.
     */
    public void disabledPeriodic() {
        sendSensorData();
        
        DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
        if (color == DriverStation.Alliance.kBlue){           
            staticleds.setRed((short) 0);
            staticleds.setGreen((short) 0);
            staticleds.setBlue((short) (255 * Math.sin(pulseCount)));
        } else if (color == DriverStation.Alliance.kRed){
            staticleds.setRed((short) (255 * Math.sin(pulseCount)));
            staticleds.setGreen((short) 0);
            staticleds.setBlue((short) 0);
        } else {    
            staticleds.setRed((short) (255 * Math.sin(pulseCount)));
            staticleds.setGreen((short) 0);
            staticleds.setBlue((short) (255 * Math.sin(pulseCount)));
        }
        
        pulseCount += Math.PI / 200; //Should take 8 seconds to pulse on and off
    }
}
