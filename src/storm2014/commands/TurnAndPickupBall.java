
package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnAndPickupBall extends CommandGroup {
    private static final double DRIVE_SPEED = 0.6;
    private static final double DISTANCE_TO_BALL = 200;
    public TurnAndPickupBall() {
         addSequential(new TurnToBall(DRIVE_SPEED));
         addSequential(new DriveForward(DRIVE_SPEED, DISTANCE_TO_BALL ));
         addSequential(new PickupBall());
    }
}
   