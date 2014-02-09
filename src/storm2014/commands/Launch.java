package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import storm2014.subsystems.LEDStrip;

//Not Tested
public class Launch extends CommandGroup {
    public Launch(){
//        addSequential(new SetLEDMode(LEDStrip.ParticleCollisionMode));
        addSequential(new SetWinchEngaged(false));
        addSequential(new SetLatched(false));
        addSequential(new WaitForFollowThrough());
        addSequential(new SetEngagedRatchet(false));
    }
}