package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.DriveForward;
import storm2014.commands.Turn;

// Tested by Erik Bobo on Electra before 2/8/13
public class AutonomousDance extends CommandGroup {
     private static final double ANGLE       = 30,
                                DRIVE_SPEED = 0.6,
                                TURN_SPEED  = 0.7;
    public AutonomousDance(double sideLength) {
        addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        
        addSequential(new DriveForward(-DRIVE_SPEED,sideLength));
        
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        
        addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        addSequential(new Turn(-ANGLE, TURN_SPEED));
        
        addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        addSequential(new DriveForward(-DRIVE_SPEED,sideLength));
    }
}
