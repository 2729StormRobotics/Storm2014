/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.Robot;
import storm2014.commands.RuntimeCommand;
import storm2014.commands.Turn;

/**
 *
 * @author Erik
 */
public class TurnHotTargetFire extends CommandGroup {
     private static final double DRIVE_SPEED = 0.6;
    public TurnHotTargetFire(double getAngle) {
        addSequential(new RuntimeCommand() {
            protected Command getCommand() {
                return new Turn(-Robot.driveTrain.getGyroAngle(), DRIVE_SPEED);
            }
        });
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
}
   
    