package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.commands.control.WaitForButton;
import storm2014.subsystems.Catapult;

public class ResetCatapult extends CommandGroup {
    public ResetCatapult() {
        addSequential(new SetEngagedRatchet(false));
        addSequential(new WaitForButton());
        addSequential(new SetWinchEngaged(true));
        addSequential(new WaitForButton());
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
        addSequential(new WaitForButton());
        addSequential(new SetLatched(true));
        addSequential(new WaitForButton());
    }
}
