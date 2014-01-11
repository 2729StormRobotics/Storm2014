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
    
    public TriangleMovement(double sideLength) {
        addSequential(new ForwardDriveByDistance(0.5,sideLength));
        addSequential(new TurnBasedOnAnAngle(60, 0.5));
         
        addSequential(new ForwardDriveByDistance(0.5,sideLength));
        addSequential(new TurnBasedOnAnAngle(60, 0.5));
        
        addSequential(new ForwardDriveByDistance(0.5,sideLength));
        addSequential(new TurnBasedOnAnAngle(60, 0.5));
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
        end(); //To change body of generated methods, choose Tools | Templates.
    }

    protected void end() {
        System.out.println("Command ended");
    }
    
}
