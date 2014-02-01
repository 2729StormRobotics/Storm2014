
package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.subsystems.VisionSystem;

public class TurnAndPickupBall extends CommandGroup {
    
    private static final double DRIVE_SPEED = 0.6;

    public TurnAndPickupBall() {
         addSequential(new TurnToBall(DRIVE_SPEED));
          
         addSequential(new DriveForward(DRIVE_SPEED, VisionSystem.getBallDistance()));
         addSequential(new SpinRollerIn());
    }
}
   