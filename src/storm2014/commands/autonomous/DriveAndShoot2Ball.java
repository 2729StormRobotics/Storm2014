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
import storm2014.Robot;
import storm2014.commands.DriveForward;
import storm2014.commands.Launch;
import storm2014.commands.PreFire;
import storm2014.commands.Reset;
import storm2014.commands.SetArmPosition;
import storm2014.commands.Shift;
import storm2014.commands.SpinRoller;
import storm2014.commands.WaitForEncoder;
import storm2014.subsystems.Catapult;

/**
 *
 * @author Tim
 */
public class DriveAndShoot2Ball extends CommandGroup{
    
    private Command _waitAndRoll() {
        CommandGroup _waitAndRoll = new CommandGroup("Waits then rolls in the spinner");
        _waitAndRoll.addSequential(new WaitCommand(1.25));
        _waitAndRoll.addSequential(new SpinRoller(-1));
        return _waitAndRoll;
    }
    
    public DriveAndShoot2Ball() {
        addSequential(new Shift(true));
        
        addSequential(new SetArmPosition(2));
        addParallel(new SpinRoller((float) -0.4));
        addSequential(new WaitCommand(0.3));
        addSequential(new Shift(false));
        addParallel(new PreFire());
        addSequential(new DriveForward(1, 4000));
        addSequential(new SpinRoller(0));
        addSequential(new Launch());
        addParallel(_waitAndRoll());
        addSequential(new Reset());
        addParallel(new PreFire());
        addSequential(new WaitCommand(1.5));
        addSequential(new SpinRoller(0));
        addSequential(new WaitCommand(0.5));
        addSequential(new Launch());
        addSequential(new Reset());
    }
    
}
