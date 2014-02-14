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
        // Wait for catapult to return
        addSequential(new Command() {
            protected void initialize() {}
            protected void execute() {
                Robot.catapult.setWinchPower(-0.5);
            }
            protected boolean isFinished() {
                return Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE;
            }

            protected void end() {
                Robot.catapult.setWinchPower(0);
                Robot.catapult.resetWinchEncoder();
            }

            protected void interrupted() {
                end();
            }
        });
        addSequential(new SetLatched(true));
        addSequential(new SetEngagedRatchet(true));
        addSequential(new SetWinchEngaged(true));
        addSequential(new Command() {
            protected void initialize() {
                Robot.catapult.setFinishedPreLaunch(true);
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
//        addSequential(new PullBack(100));
    }
}
