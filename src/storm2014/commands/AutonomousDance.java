/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Erik
 */
public class AutonomousDance extends CommandGroup {
     private static final double ANGLE       = 75,
                                DRIVE_SPEED = 0.6,
                                TURN_SPEED  = 0.7;
    public AutonomousDance(double sideLength) {
        addSequential(new ForwardDriveByDistance(DRIVE_SPEED,sideLength));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time

}
