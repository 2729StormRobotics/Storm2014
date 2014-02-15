package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.subsystems.Catapult;
import storm2014.utilities.Debouncer;

// Tested by Joe Doyle (mostly) on 2014 Robot on 2/15/14
public class ResetCatapult extends CommandGroup {
    public ResetCatapult() {
        addSequential(new SetEngagedRatchet(false));
        addSequential(new SetWinchEngaged(true));
        // Wait for catapult to return
        addSequential(new Command() {
            private Debouncer _debounce = new Debouncer(0.5);
            protected void initialize() {
                if(Robot.catapult.getPivotAngle() > Catapult.BASE_ANGLE) {
                    Robot.catapult.unlatch();
                }
            }
            protected void execute() {
                Robot.catapult.setWinchPower(-0.5);
            }
            protected boolean isFinished() {
                return _debounce.check(Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE) ||
                       Robot.catapult.getWinchDistance() < -20;
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
    }
}
