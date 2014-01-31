package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.subsystems.VisionSystem;
/**
 *
 * @author Erik
 */
public class TurnHotTargetFireLeft extends CommandGroup{
    private static final double TURN_ANGLE = 60,
                                TURN_SPEED  = 0.7,
                                DRIVE_SPEED = 0.6;   
    public TurnHotTargetFireLeft(double sideLength) {
       addSequential(new Conditional(new Turn(TURN_ANGLE, TURN_SPEED), new Engage()) {
           protected boolean condition() {
               return !VisionSystem.foundHotTarget();
           }
       });
    
       addSequential(new Conditional(new Turn(TURN_ANGLE, TURN_SPEED), new Engage()) {
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