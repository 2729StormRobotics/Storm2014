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
    
    private Command _waitAndLetRoll(){
        CommandGroup _waitAndLetRoll = new CommandGroup("rolls ball out of way");
        _waitAndLetRoll.addSequential(new WaitForEncoder(4200 - 300));
        _waitAndLetRoll.addSequential(new SpinRoller(0));
        return _waitAndLetRoll;
    }
    
    public DriveAndShoot2Ball() {
        addSequential(new Shift(true));
        addSequential(new SetArmPosition(2));
        addParallel(new SpinRoller((float) -0.65));
        addSequential(new WaitCommand(0.3));
        addParallel(_waitAndLetRoll());
        addSequential(new DriveForward(1, 4200));
        addParallel(new PreFire());
        addSequential(new WaitCommand(1.0));
        addSequential(new Launch());
        addParallel(_waitAndRoll());
        addSequential(new Reset());
        addParallel(new PreFire());
        addSequential(new WaitCommand(0.5));
        addSequential(new SpinRoller(0));
        addSequential(new SetArmPosition(0));
        addSequential(new WaitCommand(1.0));
        addSequential(new SetArmPosition(2));
        addSequential(new WaitCommand(1.25));
        addSequential(new Launch());
        addSequential(new Reset());
    }
    
}
