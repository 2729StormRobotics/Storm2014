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
    private final Debouncer _wait = new Debouncer(0.5);
    
    public SetArmPosition(int mode) {
        requires(Robot.intake);
        _mode = mode;
    }
    
    private void _nextMode() {
        _currMode = Math.min(_mode,Robot.intake.getMode()+1);
        Robot.intake.setMode(_mode);
        _wait.reset();
    }

    protected void initialize() {
        _nextMode();
    }

    protected void execute() {
        if(_wait.check(true)) {
            _nextMode();
        }
    }

    protected boolean isFinished() {
        return _currMode >= _mode;
    }

    protected void end() {}
    protected void interrupted() {}
}
