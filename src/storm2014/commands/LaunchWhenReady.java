package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchWhenReady extends CommandGroup {
    public LaunchWhenReady() {
        addSequential(new WaitForPrelaunch());
        addSequential(new Command() {
            Launch _launch = new Launch();
            protected void initialize() {
                _launch.start();
            }

            protected void execute() {}

            protected boolean isFinished() {
                return !_launch.isRunning();
            }

            protected void end() {
                if(_launch.isRunning()) {
                    _launch.cancel();
                }
            }

            protected void interrupted() {
                end();
            }
        });
    }
}
