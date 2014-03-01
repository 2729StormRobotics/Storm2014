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
import storm2014.commands.LaunchWhenReady;
import storm2014.commands.SetArmPosition;
import storm2014.commands.SetWinchPreset;
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
        addSequential(new SetWinchPreset(Catapult.WINCH_CLOSE));
        addSequential(new Shift(true));
        addSequential(new DriveForward(1, 4700));
        addSequential(new SetArmPosition(2));
        addSequential(new WaitCommand(1));
        addSequential(new PrintCommand("Firing"));
        addSequential(new LaunchWhenReady());
    }
    
}
