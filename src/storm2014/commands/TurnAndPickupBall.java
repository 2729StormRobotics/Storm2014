
package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.subsystems.VisionSystem;

public class TurnAndPickupBall extends CommandGroup {
    
    private static final double DRIVE_SPEED = 0.6;
    private double _distanceToBalll;
    
 
    public TurnAndPickupBall() {
        _distanceToBalll = VisionSystem.getBallDistance();
         addSequential(new TurnToBall(DRIVE_SPEED));
         addSequential(new DriveForward(DRIVE_SPEED, _distanceToBalll));
         addSequential(new PickupBall());
    }
}
   