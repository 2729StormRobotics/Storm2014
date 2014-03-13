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
import storm2014.commands.SetArmPosition;
import storm2014.commands.Shift;
import storm2014.commands.SpinRoller;
import storm2014.subsystems.Catapult;

/**
 *
 * @author Tim
 */
public class DriveAndShoot2Ball extends CommandGroup{
    
    private Command _driveShoot() {
        CommandGroup driveShoot = new CommandGroup("Drive & shoot for 2 ball");
        
        driveShoot.addSequential(new DriveForward(1, 1500));
        driveShoot.addSequential(new SetArmPosition(2));
        driveShoot.addSequential(new WaitCommand(1));
        driveShoot.addSequential(new PrintCommand("Firing"));
        driveShoot.addSequential(new PreFire());
        driveShoot.addSequential(new WaitCommand(1));
        driveShoot.addSequential(new Launch());
        
        return driveShoot;
    }
    
    public DriveAndShoot2Ball() {
        addSequential(new Shift(true));
        
        addSequential(_driveShoot());
        
        addSequential(new SpinRoller(1));
        addSequential(new SetArmPosition(2));
        addSequential(new DriveForward(-1, 1600));
        addSequential(new WaitCommand(1));
        addSequential(new SpinRoller(0));
        addSequential(new SetArmPosition(0));
        addSequential(new DriveForward(1, 100));
        addSequential(_driveShoot());
    }
    
}
