package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class SetArmPosition extends Command {
    private final int _mode;
    
    public SetArmPosition(int mode) {
        requires(Robot.intake);
        _mode = mode;
    }

    protected void initialize() {
        Robot.intake.setMode(_mode);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
