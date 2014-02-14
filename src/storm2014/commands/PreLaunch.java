package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.Robot;
import storm2014.subsystems.Catapult;
//import storm2014.subsystems.LEDStrip;

//Not Tested
public class PreLaunch extends CommandGroup {
    public PreLaunch() {
        addSequential(new SetLatched(false));
        addSequential(new SetEngagedRatchet(false));
        addSequential(new SetWinchEngaged(true));
        addSequential(new WaitCommand(2));
        // Wait for catapult to return
        addSequential(new Command() {
            protected void initialize() {}
            protected void execute() {
                Robot.catapult.setWinchPower(-1);
            }
            protected boolean isFinished() {
                return Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE;
            }

            protected void end() {
                Robot.catapult.setWinchPower(0);
            }

            protected void interrupted() {
                end();
            }
        });
        addSequential(new SetLatched(true));
        addSequential(new SetEngagedRatchet(true));
        addSequential(new SetWinchEngaged(true));
        Robot.catapult.setFinishedPreLaunch(true);
//        addSequential(new PullBack(100));
    }
}
