package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

//Tested by Erik Bobo on Electra before 2/8/13
public class TurnAndTurnBack extends CommandGroup {
    
    public TurnAndTurnBack(double power, double distance, double angle) {
        addSequential(new Turn(angle, power));
        addSequential(new WaitCommand(5));
        addSequential(new Turn(-angle, power));
        
        addSequential(new DriveForward(-power, distance));
        addSequential(new DriveForward(power, distance));
        addSequential(new Turn(angle, power));
        addSequential(new WaitCommand(5));
        addSequential(new Turn(-angle, power));
    }
}