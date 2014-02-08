package storm2014.commands.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.TurnAndTurnBack;
import storm2014.subsystems.VisionSystem;

public class TurnHotTargetFireRight extends CommandGroup {
    private static final double TURN_ANGLE = 60,
                                DRIVE_SPEED = 0.6,
                                DISTANCE = 2000;
    public TurnHotTargetFireRight() {
        addSequential(new Conditional(new TurnAndTurnBack(DRIVE_SPEED, DISTANCE, TURN_ANGLE), null) {
           protected boolean condition() {
               return !VisionSystem.foundHotTarget();
           }
        });
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
    }
}
