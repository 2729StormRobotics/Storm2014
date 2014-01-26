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
public class FindAndGetBallCommand extends CommandGroup {
    private static final double DRIVE_SPEED = 0.6;
                                
    public FindAndGetBallCommand(double distance) {
         addSequential(new FindBall(DRIVE_SPEED));
         addSequential(new FindTarget(DRIVE_SPEED));
         
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}
   