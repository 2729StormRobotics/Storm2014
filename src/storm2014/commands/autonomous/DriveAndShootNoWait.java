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
import storm2014.commands.Launch;
import storm2014.commands.PreFire;
import storm2014.commands.Reset;
import storm2014.commands.SetArmPosition;
import storm2014.commands.Shift;
import storm2014.commands.control.Conditional;
import storm2014.subsystems.Catapult;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author Tim
 */
public class DriveAndShootNoWait extends CommandGroup{
    
    public DriveAndShootNoWait(){
        addSequential(new Shift(true));
        addParallel(new PreFire());
        addSequential(new DriveForward(1, 4450));
        addSequential(new SetArmPosition(2));
        addSequential(new PrintCommand("Firing"));
        addSequential(new PrintCommand("Waiting"));
        addSequential(new WaitCommand(0.5));
        addSequential(new PrintCommand("Before Launch"));
        addSequential(new Launch());
        addSequential(new PrintCommand("After Launch"));
        addSequential(new Reset());
    }
    
}
