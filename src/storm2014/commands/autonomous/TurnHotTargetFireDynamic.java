package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.Turn;
import storm2014.commands.TurnAndTurnBack;
import storm2014.subsystems.VisionSystem;

// Tested by Erik Bobo on Electra before 2/8/13
public class TurnHotTargetFireDynamic extends CommandGroup{
    private static final double 
                                DISTANCE = 2000,
                                DRIVE_SPEED = 0.6;   
    public TurnHotTargetFireDynamic() { 
        addSequential(new TurnHotTargetFire(DRIVE_SPEED));
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
      //addSequential(new Conditional(new TurnAndTurnBack(DRIVE_SPEED, DISTANCE, TURN_ANGLE), null) {
          //protected boolean condition() {
              //return !VisionSystem.foundHotTarget();
          //}
     //});
         
    }
  }