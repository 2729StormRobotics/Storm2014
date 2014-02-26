package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class SetWinchPreset extends Command {
    private final int _index;

    public SetWinchPreset(int index) {
        _index = index;
    }

    protected void initialize() {
        Robot.catapult.setPresetIndex(_index);
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
    protected void interrupted() {}
}
