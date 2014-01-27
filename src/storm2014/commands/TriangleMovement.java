/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 * @author Storm
 */
public class TriangleMovement extends CommandGroup {
    private static final double ANGLE       = 75,
                                DRIVE_SPEED = 0.6,
                                TURN_SPEED  = 0.7;
    
    public TriangleMovement(double sideLength) {
        addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        
        addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        
        addSequential(new DriveForward(DRIVE_SPEED,sideLength));
        addSequential(new Turn(ANGLE, TURN_SPEED));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }

    protected void interrupted() {
        System.out.println("Interrupted");
        end(); 
    }

    protected void end() {
        System.out.println("Command ended");
    }
    
}
