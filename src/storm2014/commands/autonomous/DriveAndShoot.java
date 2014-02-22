/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.commands.DriveForward;
import storm2014.commands.LaunchWhenReady;
import storm2014.commands.SetArmPosition;
import storm2014.commands.control.Conditional;
import storm2014.commands.control.DoNothing;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author Tim
 */
public class DriveAndShoot extends CommandGroup{
    
    private boolean foundHotTarget;
    
    public DriveAndShoot(){
        addSequential(new Command() {
            protected void initialize() {
                foundHotTarget = VisionSystem.foundHotTarget();
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        addSequential(new DriveForward(0.75, 4700));
        addSequential(new SetArmPosition(2));
        addSequential(new Conditional(null, new WaitCommand(5)) { //may lower wait time on the waitcommand
            protected boolean condition() {
                return foundHotTarget;
            }
        });
        addSequential(new LaunchWhenReady());
    
    }
    
}
