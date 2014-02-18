package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.subsystems.Catapult;
import storm2014.utilities.Debouncer;
/**
 * Resets the catapult to the correct position.
 */
// Tested by Joe Doyle (mostly) on 2014 Robot on 2/15/14
public class ResetCatapult extends CommandGroup {
//    private int timesRun = 0;
    public ResetCatapult() {
        addSequential(new SetEngagedRatchet(false));
        // Wait for catapult to return
        addSequential(new Command() {
            private final Debouncer _debounce = new Debouncer(0.5);
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
//                if(!_useEncoder && firstRun){
//                    firstRun = false;
//                    Robot.catapult.resetWinchEncoder();
//                }
            }
            protected boolean isFinished() {
                return (!_useEncoder && _debounce.check(Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE)) ||
                       (_useEncoder  && Robot.catapult.getWinchDistance() <= 0);
            }

            protected void end() {
                Robot.catapult.setWinchPower(0);
            }

            protected void interrupted() {
                end();
            }
        });
        addSequential(new SetLatched(true));
    }
}
