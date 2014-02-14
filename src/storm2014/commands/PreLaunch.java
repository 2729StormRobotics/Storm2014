package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.subsystems.Catapult;
//import storm2014.subsystems.LEDStrip;

//Not Tested
public class PreLaunch extends CommandGroup {
    public PreLaunch() {
        // Wait for catapult to return
//        addSequential(new DoNothing() {
//            protected void execute() {
//                Robot.catapult.setWinchPower(-1);
//            }
//            protected boolean isFinished() {
//                return Robot.catapult.getPivotAngle() < Catapult.BASE_ANGLE;
//            }
//        });
//        addSequential(new SetLEDMode(LEDStrip.StormSpiritMode));
        Robot.catapult.setFinishedPreLaunch(true);
        addSequential(new SetLatched(true));
        addSequential(new SetEngagedRatchet(true));
        addSequential(new SetWinchEngaged(true));
//        addSequential(new PullBack(100));
    }
}
