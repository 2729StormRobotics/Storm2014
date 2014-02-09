package storm2014;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;

import storm2014.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.commands.DriveForward;
import storm2014.commands.PullBack;
import storm2014.commands.SetEngagedRatchet;
import storm2014.commands.SetWinchEngaged;
import storm2014.commands.SetLatched;
import storm2014.commands.SpinRoller;
import storm2014.commands.autonomous.OneBallDynamic;
import storm2014.subsystems.Catapult;
import storm2014.subsystems.Intake;
import storm2014.subsystems.LEDRing;
import storm2014.subsystems.LEDStrip;
import storm2014.subsystems.StaticLEDStrip;
import storm2014.subsystems.Tilter;

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
    public static Tilter         tilter;
    public static LEDRing        ledring;
    public static StaticLEDStrip staticleds;
    
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
    private void sendSensorData() {
         SmartDashboard.putNumber("String Pot Voltage",1.0/tilter.getStringPotRaw());
    }
    
    /** Called on robot boot. */
    public void robotInit() {
        catapult   = new Catapult();
        driveTrain = new DriveTrain();
        leds       = new LEDStrip();
        intake     = new Intake(0);
        tilter     = new Tilter();
        ledring    = new LEDRing();
        staticleds = new StaticLEDStrip();
        // Initialize OI last so it doesn't try to access null subsystems
        oi         = new OI();
        
        // The names, and corresponding Commands of our autonomous modes
        autonomiceNames = new String[]{"Drive Forward","TurnHotTargetFireLeft","TurnHotTargetFireRight"};
        autonomice = new Command[]{new DriveForward(0.6, 1000),new OneBallDynamic(false), new OneBallDynamic(true)};
        
        // Configure and send the SendableChooser, which allows autonomous modes
        // to be chosen via radio button on the SmartDashboard
        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);
        SmartDashboard.putData(Scheduler.getInstance());
        
        SmartDashboard.putData("Pull Back",new PullBack(100));
        SmartDashboard.putData("Arm Mode",new PullBack(1));
        SmartDashboard.putData("Engage Ratchet",new SetEngagedRatchet(true));
        SmartDashboard.putData("Disengage Ratchet",new SetEngagedRatchet(false));
        SmartDashboard.putData("Engage Winch",new SetWinchEngaged(true));
        SmartDashboard.putData("Disengage Winch",new SetWinchEngaged(false));
        SmartDashboard.putData("Latch", new SetLatched(true));
        SmartDashboard.putData("Unlatch", new SetLatched(false));
        SmartDashboard.putData("Spin Roller In", new SpinRoller(1));
        
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
        
        DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
        if (color == DriverStation.Alliance.kBlue){
            SmartDashboard.putBoolean("Blue Alliance?", true);
        }
        else{
            SmartDashboard.putBoolean("Blue Alliance?", false);
        }
        
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
