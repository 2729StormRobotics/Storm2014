package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.Launch;
import storm2014.commands.TurnAndShootDynamic;
import storm2014.subsystems.VisionSystem;

public class TwoBallDynamic extends CommandGroup {
    private static final double DRIVE_SPEED = 0.6,
                                DISTANCE = 2000,
                                BALL_DISTANCE = 2000;
    private boolean _firstWasHot;
    public TwoBallDynamic(boolean isRight) {
        addSequential(new Conditional(new TurnAndShootDynamic(isRight,DRIVE_SPEED), new Launch()) {
           protected boolean condition() {
               return !(_firstWasHot = VisionSystem.foundHotTarget());
           }
        });
        addSequential(new GrabBall(DRIVE_SPEED, BALL_DISTANCE));
        addSequential(new Conditional(new TurnAndShootDynamic(isRight,DRIVE_SPEED), new Launch()) {
           protected boolean condition() {
               return _firstWasHot;
           }
        });
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
    }
}
