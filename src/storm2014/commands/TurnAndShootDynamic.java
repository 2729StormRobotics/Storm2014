package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;

public class TurnAndShootDynamic extends CommandGroup {
    public TurnAndShootDynamic(boolean isRight,final double power) {
        addSequential(new TurnToTarget(isRight,power,15));
        addSequential(new Launch() {

            protected boolean thisIsIntentional() {
                return true;
            }
        });
        addSequential(new RuntimeCommand() {
            protected Command getCommand() {
                return new Turn(-Robot.driveTrain.getGyroAngle(), power);
            }
        });
    }
}
