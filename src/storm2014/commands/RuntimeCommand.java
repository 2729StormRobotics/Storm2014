package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;

public abstract class RuntimeCommand extends Command {
    private Command _command;
            
    protected abstract Command getCommand();

    protected void initialize() {
        (_command = getCommand()).start();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return _command == null || !_command.isRunning();
    }

    protected void end() {
        if(_command != null && _command.isRunning()) {
            _command.cancel();
        }
    }

    protected void interrupted() {
        end();
    }
}
