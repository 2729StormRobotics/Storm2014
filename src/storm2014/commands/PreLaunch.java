package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.subsystems.Catapult;
//import storm2014.subsystems.LEDStrip;

//Not Tested
public class PreLaunch extends CommandGroup {
    public PreLaunch() {
        // Wait for catapult to return
        addSequential(new DoNothing() {
            protected boolean isFinished() {
                return Robot.catapult.getPivotAngle() < Catapult.BASE_ANGLE;
            }
        });
//        addSequential(new SetLEDMode(LEDStrip.StormSpiritMode));
        addSequential(new SetLatched(true));
        addSequential(new SetEngagedRatchet(true));
        addSequential(new SetWinchEngaged(true));
        addSequential(new PullBack(100));
    }
}
