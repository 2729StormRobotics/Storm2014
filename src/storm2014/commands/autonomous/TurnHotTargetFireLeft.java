package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.Conditional;
import storm2014.commands.DriveForward;
import storm2014.commands.TurnAndTurnBack;
import storm2014.subsystems.VisionSystem;

// Tested by Erik Bobo on Electra before 2/8/13
public class TurnHotTargetFireLeft extends CommandGroup{
    private static final double TURN_ANGLE = -60,
                                DISTANCE = 2000,
                                DRIVE_SPEED = 0.6;   
    public TurnHotTargetFireLeft() {
       addSequential(new Conditional(new TurnAndTurnBack(DRIVE_SPEED, DISTANCE, TURN_ANGLE), null) {
           protected boolean condition() {
               return !VisionSystem.foundHotTarget();
           }
       });
        addSequential(new DriveForward(DRIVE_SPEED, DISTANCE));
    }
  }