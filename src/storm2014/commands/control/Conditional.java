package storm2014.commands.control;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PublicCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.WaitCommand;
import java.util.Enumeration;
/**
 * Used by command groups for if statements.
 */
public abstract class Conditional extends Command {
    private final PublicCommand _ifTrue,_ifFalse;
    private PublicCommand _running = null;
    private boolean _firstRun = true;

    public Conditional(final Command ifTrue,final Command ifFalse) {
        super("Condition?" + (ifTrue  == null ? "" : ifTrue .getName()) +
                       ":" + (ifFalse == null ? "" : ifFalse.getName()));
        // Wrap the Commands to expose protected methods
        if(ifTrue != null) {
            _ifTrue  = new PublicCommand(ifTrue);
            for(Enumeration e = _ifTrue.getRequirements();e.hasMoreElements();) {
                requires((Subsystem) e.nextElement());
            }
        } else {
            _ifTrue = null;
        }
        if(ifFalse != null) {
            _ifFalse  = new PublicCommand(ifFalse);
            for(Enumeration e = _ifFalse.getRequirements();e.hasMoreElements();) {
                requires((Subsystem) e.nextElement());
            }
        } else {
            _ifFalse = null;
        }
    }
    
    protected abstract boolean condition();

    protected void initialize() {
        if(condition()) {
            _running = _ifTrue;
        } else {
            _running = _ifFalse;
        }
        if(_running != null) {
            if(_running.getCommand() instanceof WaitCommand) {
                _running.getCommand().start();
            } else {
                _running._initialize();
                _running.initialize();
            }
        }
        _firstRun = true;
    }

    protected void execute() {
        if(_running != null && !(_running.getCommand() instanceof WaitCommand)) {
            _running._execute();
            _running.execute();
        }
    }

    protected boolean isFinished() {
        boolean firstRun = _firstRun;
        _firstRun = false;
        if(_running == null) {
            return true;
        }
        if(_running.getCommand() instanceof WaitCommand) {
            return !firstRun && !_running.getCommand().isRunning();
        } else {
            return _running.isFinished();
        }
    }

    protected void end() {
        if(_running != null) {
            if(!(_running.getCommand() instanceof WaitCommand)) {
                _running._end();
                _running.end();
            } else {
                _running.cancel();
            }
        }
    }

    protected void interrupted() {
        if(_running != null) {
            if(!(_running.getCommand() instanceof WaitCommand)) {
                _running._interrupted();
                _running.interrupted();
            } else {
                _running.cancel();
            }
        }
    }
}
