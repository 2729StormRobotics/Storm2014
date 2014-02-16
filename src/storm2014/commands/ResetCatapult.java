package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.commands.control.WaitForButton;
import storm2014.subsystems.Catapult;
import storm2014.utilities.Debouncer;

// Tested by Joe Doyle (mostly) on 2014 Robot on 2/15/14
public class ResetCatapult extends CommandGroup {
    public ResetCatapult() {
        addSequential(new SetEngagedRatchet(false));
        addSequential(new SetWinchEngaged(true));
//        addSequential(new WaitForButton());
        // Wait for catapult to return
        addSequential(new Command() {
            private Debouncer _debounce = new Debouncer(0.5);
            private boolean _useEncoder = false;
            {
                requires(Robot.catapult);
            }
            protected void initialize() {
                if(Robot.catapult.getPivotAngle() > Catapult.BASE_ANGLE) {
                    Robot.catapult.unlatch();
                    _useEncoder = false;
                } else {
                    _useEncoder = true;
                }
            }
            protected void execute() {
                Robot.catapult.setWinchPower(-0.5);
            }
            protected boolean isFinished() {
                return (!_useEncoder && _debounce.check(Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE)) ||
                       (_useEncoder  && Robot.catapult.getWinchDistance() < 0);
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
