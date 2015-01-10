package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.LaunchWhenReady;
import storm2014.commands.Turn;

//Tested by Erik Bobo on Electra before 2/8/13
public class TurnAndShoot extends CommandGroup {
    public TurnAndShoot(double power, double angle) {
        addSequential(new Turn(angle, power));
        addSequential(new LaunchWhenReady());
        addSequential(new Turn(-angle, power));
    }
}