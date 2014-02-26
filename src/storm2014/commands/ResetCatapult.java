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
        setInterruptible(false);
        addSequential(new SetEngagedRatchet(false));
        // Wait for catapult to return
        addSequential(new Command() {
            private static final double WINCH_TARGET = -80;
            private static final double STOPPED_SPEED = 50;
            private static final double DT = 1.0/50;
            private final Debouncer _debounce = new Debouncer(0.8);
            private boolean _unlatch = false;
            private double _prevAngle;
            
            {
                requires(Robot.catapult);
            }
            protected void initialize() {
                _prevAngle = Robot.catapult.getPivotAngle();
                _unlatch = (Robot.catapult.getCurrentIndex() == 0 ||
                            Robot.catapult.isCatapultOut()        ||
                            Robot.catapult.getPivotAngle() > Catapult.BASE_ANGLE + 5);
                Robot.catapult.setIndex(0);
                if(_unlatch) {
                    _debounce.reset();
                    Robot.catapult.unlatch();
                }
            }
            protected void execute() {
                Robot.catapult.setWinchPower(Robot.catapult.getWinchDistance() > WINCH_TARGET ? -0.5 : 0);
            }
            protected boolean isFinished() {
                if(!_unlatch) {
                    return Robot.catapult.getWinchDistance() <= WINCH_TARGET;
                } else {
                    double angle = Robot.catapult.getPivotAngle();
                    double speed = (angle-_prevAngle)/DT;
                    _prevAngle = angle;
                    return _debounce.check(Math.abs(speed) < STOPPED_SPEED);
                }
            }

            protected void end() {
                Robot.catapult.setCatapultOut(false);
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
