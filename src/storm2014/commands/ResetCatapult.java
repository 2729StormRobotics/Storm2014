package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
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
            private static final double WINCH_TARGET = -80;
            private final Debouncer _debounce = new Debouncer(0.8);
            private boolean _useEncoder = false;
            {
                requires(Robot.catapult);
            }
            protected void initialize() {
                Robot.catapult.setIndex(0);
                if(Robot.catapult.getPivotAngle() > Catapult.BASE_ANGLE) {
                    Robot.catapult.unlatch();
                    _useEncoder = false;
                } else {
                    _useEncoder = true;
                }
            }
            protected void execute() {
                Robot.catapult.setWinchPower(Robot.catapult.getWinchDistance() > WINCH_TARGET ? -0.5 : 0);
//                if(!_useEncoder && firstRun){
//                    firstRun = false;
//                    Robot.catapult.resetWinchEncoder();
//                }
            }
            protected boolean isFinished() {
                return (!_useEncoder && _debounce.check(Robot.catapult.getPivotAngle() > 100 && Robot.catapult.getPivotAngle() <= Catapult.BASE_ANGLE)) ||
                       (_useEncoder  && Robot.catapult.getWinchDistance() <= WINCH_TARGET);
            }

            protected void end() {
                Robot.catapult.setWinchPower(0);
            }

            protected void interrupted() {
                end();
            }
        },3);
        addSequential(new WaitCommand(0.25));
        addSequential(new SetLatched(true));
    }
}
