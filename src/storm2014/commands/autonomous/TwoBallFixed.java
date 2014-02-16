package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.control.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.LaunchWhenReady;
import storm2014.subsystems.VisionSystem;

public class TwoBallFixed extends CommandGroup {
    
    private static final double TURN_ANGLE = 60,
                                DRIVE_SPEED = 0.6,
                                DISTANCE = 2000,
                                BALL_DISTANCE = 2000;
    
    private boolean _firstWasHot;
    
    public TwoBallFixed(boolean isRight) {
        addSequential(new Conditional(new TurnAndShoot(DRIVE_SPEED, (isRight?-1:1) * TURN_ANGLE), new LaunchWhenReady()) {
           protected boolean condition() {
               return !(_firstWasHot = VisionSystem.foundHotTarget());
           }
        });
        addSequential(new GrabBall(DRIVE_SPEED, BALL_DISTANCE));
        addSequential(new Conditional(new TurnAndShoot(DRIVE_SPEED, (isRight?-1:1) * TURN_ANGLE), new LaunchWhenReady()) {
           protected boolean condition() {
               return _firstWasHot;
           }
        });
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
    }
}
