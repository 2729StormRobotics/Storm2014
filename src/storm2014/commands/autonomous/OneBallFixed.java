package storm2014.commands.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.Launch;
import storm2014.commands.LaunchWhenReady;
import storm2014.subsystems.VisionSystem;

public class OneBallFixed extends CommandGroup {
    private static final double TURN_ANGLE = 60,
                                DRIVE_SPEED = 0.6,
                                DISTANCE = 2000;
    public OneBallFixed(boolean isRight) {
        addSequential(new Conditional(new TurnAndShoot(DRIVE_SPEED, (isRight?-1:1) * TURN_ANGLE), new LaunchWhenReady()) {
           protected boolean condition() {
               return !VisionSystem.foundHotTarget();
           }
        });
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
    }
}
