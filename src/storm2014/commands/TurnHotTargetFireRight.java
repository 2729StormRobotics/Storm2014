/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.subsystems.VisionSystem;
/**
 *
 * @author Erik
 */
public class TurnHotTargetFireRight extends CommandGroup {
    private static final double TURN_ANGLE = 60,
                                DRIVE_SPEED = 0.6,
                                DISTANCE = 2000;
    public TurnHotTargetFireRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new Conditional(new TurnTurnBackAndDrive(DRIVE_SPEED, DISTANCE, TURN_ANGLE), new DriveForward(DRIVE_SPEED, DISTANCE)) {
           protected boolean condition() {
               return !VisionSystem.foundHotTarget();
           }
       });
    }
}
