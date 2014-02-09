package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.ChangeArmPosition;
import storm2014.commands.DriveForward;
import storm2014.commands.SpinRoller;

public class GrabBall extends CommandGroup {
    public GrabBall(double speed,double distance) {
        addSequential(new SetArmPosition(2));
        addParallel(new SpinRoller(1));
        addSequential(new DriveForward(-speed, distance));
        addParallel(new SpinRoller(0));
        addSequential(new DriveForward(speed, distance));
    }
}
