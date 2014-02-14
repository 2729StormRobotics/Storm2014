package storm2014.commands.control;

import edu.wpi.first.wpilibj.command.Command;

public abstract class RepeatWhile extends Command {
    Command _c;
    
    public RepeatWhile(Command c) {
        _c = c;
    }
    
    protected abstract boolean condition();

    protected void initialize() {}

    protected void execute() {
        if (!_c.isRunning() && !condition()) { 
            _c.start();
        }
    }

    protected boolean isFinished() {
        return !_c.isRunning() && condition();
    }

    protected void end() {
        _c.cancel();
    }
    protected void interrupted() {
        end();
    }
}
