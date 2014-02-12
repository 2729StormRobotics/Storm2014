package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Tested by Erik Bobo on Electra before 2/8/13
public class TurnAndShoot extends CommandGroup {
    public TurnAndShoot(double power, double angle) {
        addSequential(new Turn(angle, power));
       // addSequential(new Launch());
        addSequential(new Turn(-angle, power));
    }
}