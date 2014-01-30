package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 * @author Erik
 */
public class TurnToHotTargetAndFire extends CommandGroup{
    private static final double ANGLE       = 75,
                                TURN_SPEED  = 0.7;
    
    public TurnToHotTargetAndFire() {
//       addSequential(new Conditional(new TurnLeftToHotTarget(ANGLE), new Engage()));
//       addSequential(new Conditional(new TurnRightToHotTarget(ANGLE), new Engage()));
       addSequential(new Engage());
       addSequential(new Disengage());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
  }
