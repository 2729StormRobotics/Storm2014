package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.commands.LaunchWhenReady;
import storm2014.commands.RuntimeCommand;
import storm2014.commands.Turn;
import storm2014.commands.TurnToTarget;

public class TurnAndShootDynamic extends CommandGroup {
    public TurnAndShootDynamic(boolean isRight,final double power) {
        addSequential(new TurnToTarget(isRight,power,15));
        addSequential(new LaunchWhenReady());
        addSequential(new RuntimeCommand() {
            protected Command getCommand() {
                return new Turn(-Robot.driveTrain.getGyroAngle(), power);
            }
        });
    }
}
