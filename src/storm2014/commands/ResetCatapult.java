package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.subsystems.Catapult;

public class ResetCatapult extends CommandGroup {
    public ResetCatapult() {
        addSequential(new SetEngagedRatchet(false));
        addSequential(new SetWinchEngaged(true));
        // Wait for catapult to return
        addSequential(new Command() {
            protected void initialize() {
                if(Robot.catapult.getPivotAngle() > Catapult.BASE_ANGLE) {
                    Robot.catapult.unlatch();
                }
            }
            protected void execute() {
                Robot.catapult.setWinchPower(-0.5);
            }
            protected boolean isFinished() {
                return Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE ||
                       Robot.catapult.getWinchDistance() < 0;
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
