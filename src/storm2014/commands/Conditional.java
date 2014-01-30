package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author ginto
 */
public abstract class Conditional extends Command {
    private final Command _ifTrue,_ifFalse;
    private Command _running = null;

    public Conditional(Command ifTrue,Command ifFalse) {
        _ifTrue  = ifTrue;
        _ifFalse = ifFalse;
    }
    
    protected abstract boolean condition();

    protected void initialize() {
        if(condition()) {
            _running = _ifTrue;
        } else {
            _running = _ifFalse;
        }
        if(_running != null) {
            _running.start();
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return _running == null || !_running.isRunning();
    }

    protected void end() {
        if(_running != null && _running.isRunning()) {
            _running.cancel();
        }
    }

    protected void interrupted() {
        end();
    }
}
