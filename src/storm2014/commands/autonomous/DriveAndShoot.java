/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.commands.DriveForward;
import storm2014.commands.FireCatapult;
import storm2014.commands.Launch;
import storm2014.commands.PreFire;
import storm2014.commands.Reset;
import storm2014.commands.SetArmPosition;
import storm2014.commands.Shift;
import storm2014.commands.control.Conditional;
import storm2014.commands.control.DoNothing;
import storm2014.subsystems.Catapult;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author Tim
 */
public class DriveAndShoot extends CommandGroup{
    
    private boolean foundHotTarget;
    
    private Command _waitAndDetect(){
        CommandGroup _waitAndDetect = new CommandGroup("Waits for hot goal to switch at start of match, then scans for hot goal");
        _waitAndDetect.addSequential(new WaitCommand(0.25));
        _waitAndDetect.addParallel(new Command() {
            protected void initialize() {
                foundHotTarget = VisionSystem.foundHotTarget();
            }
            protected void execute() {
                foundHotTarget = foundHotTarget || VisionSystem.foundHotTarget();
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {}
        },1.75);
        return _waitAndDetect;
    }   
    
    public DriveAndShoot(){
        addParallel(_waitAndDetect());
        addSequential(new Shift(true));
        addSequential(new WaitCommand(0.25));
        addSequential(new DriveForward(1, 2800));
        addSequential(new Conditional(new WaitCommand(.01), new WaitCommand(3)) { //may lower wait time on the waitcommand
            protected boolean condition() {
                return foundHotTarget;
            }
        });
        addSequential(new SetArmPosition(1));
        addParallel(new PreFire());
        addSequential(new SetArmPosition(2));
        addSequential(new WaitCommand(1));
        addSequential(new Launch());
        addSequential(new Reset());
    }
    
}
