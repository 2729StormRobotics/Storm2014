package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.subsystems.VisionSystem;
/**
 *
 * @author Erik
 */
public class TurnToHotTargetAndFire extends CommandGroup{
    private static final double ANGLE       = 75,
                                TURN_SPEED  = 0.7,
                                DRIVE_SPEED = 0.6;   
    public TurnToHotTargetAndFire(double sideLength) {
       addSequential(new Conditional(new TurnLeftToHotTarget(ANGLE), new Engage()) {
           protected boolean condition() {
               return VisionSystem.foundHotTarget();
           }
       });
    
       addSequential(new Conditional(new TurnRightToHotTarget(ANGLE), new Engage()) {
           protected boolean condition() {
               return VisionSystem.foundHotTarget();
           }
       });
      //addSequential(new Engage());
      //addSequential(new Disengage());
       
       addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
  }