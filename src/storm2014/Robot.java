package storm2014;

import storm2014.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.commands.ForwardDriveByDistance;

/** 
 * This is the robot's "Main class" which is run by the VM.
 */
public class Robot extends IterativeRobot {
    // All subsystems are accessible by Robot.name
    public static OI         oi;
    public static DriveTrain driveTrain;
    
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
    private void sendSensorData() {
    }
    
    /** Called on robot boot. */
    public void robotInit() {
        driveTrain = new DriveTrain();
        // Initialize OI last so it doesn't try to access null subsystems
        oi         = new OI();

        // The names, and corresponding Commands of our autonomous modes
        autonomiceNames = new String[]{"TakeItBackNowYall"};
        autonomice = new Command[]{new ForwardDriveByDistance(0.6, 1000)};

        // Configure and send the SendableChooser, which allows autonomous modes
        // to be chosen via radio button on the SmartDashboard
        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);
        
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
