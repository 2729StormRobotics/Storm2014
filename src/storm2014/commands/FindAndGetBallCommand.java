/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;
import storm2014.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 * @author Erik
 */
public class FindAndGetBallCommand extends CommandGroup {
    private static final double ANGLE       = 30,
                                DRIVE_SPEED = 0.6,
                                TURN_SPEED  = 0.7;
    public FindAndGetBallCommand(double power, double sideLength, double distanceToBall) {
         addSequential(new FindBall(ANGLE, TURN_SPEED));
         addSequential(new ForwardDriveByDistance(DRIVE_SPEED,sideLength));
         addSequential(new FindTarget(ANGLE, TURN_SPEED));
         
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}
   