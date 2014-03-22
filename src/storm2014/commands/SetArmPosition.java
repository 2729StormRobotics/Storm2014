package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
import storm2014.utilities.Debouncer;
/**
 * Sets the arm position to the desired mode.
 */
public class SetArmPosition extends Command {
    private final int _mode;
    private int _currMode;
    private int _dir;
    private final Debouncer _wait = new Debouncer(0.5);
    
    public SetArmPosition(int mode) {
        requires(Robot.intake);
        _mode = mode;
    }

    private double signum(double x) {
        return x > 0 ?  1 :
               x < 0 ? -1 :
                        0;
    }
    
    private void _nextMode() {
        int currMode = Robot.intake.getMode();
        if(_dir*(_mode-currMode) > 0) {
            _currMode = currMode + _dir;
            Robot.intake.setMode(_currMode);
            _wait.reset();
        }
    }

    protected void initialize() {
        _dir = (int)signum(_mode-Robot.intake.getMode());
        _nextMode();
    }

    protected void execute() {
        if(_wait.check(true)) {
            _nextMode();
        }
    }

    protected boolean isFinished() {
        return _dir*(_mode-Robot.intake.getMode()) <= 0 && _wait.check(true);
    }

    protected void end() {}
    protected void interrupted() {}
}
