package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.control.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.Launch;
import storm2014.commands.LaunchWhenReady;
import storm2014.subsystems.VisionSystem;

public class OneBallDynamic extends CommandGroup {
    private static final double DISTANCE = 2000,
                                DRIVE_SPEED = 0.5;   
    public OneBallDynamic(boolean isRight) { 
        addSequential(new Conditional(new TurnAndShootDynamic(isRight,DRIVE_SPEED),new LaunchWhenReady()) {
            protected boolean condition() {
                return !VisionSystem.foundHotTarget();
            }
        });
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
    }
  }