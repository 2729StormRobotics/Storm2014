package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.subsystems.LEDStrip;

//Not Tested
public class Launch extends CommandGroup {
    public Launch(){
        addSequential(new SetLEDMode(LEDStrip.ParticleCollisionMode));
        addSequential(new SetLatched(false));
        addSequential(new SetEngagedWinch(false));
        //Wait
        addSequential(new WaitCommand(5));
        addSequential(new SetEngagedRatchet(false));
    }
}