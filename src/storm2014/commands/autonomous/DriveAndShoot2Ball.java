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
import edu.wpi.first.wpilibj.command.WaitForChildren;
import storm2014.Robot;
import storm2014.commands.DriveForward;
import storm2014.commands.FireCatapult;
import storm2014.commands.Launch;
import storm2014.commands.PreFire;
import storm2014.commands.Reset;
import storm2014.commands.SetArmPosition;
import storm2014.commands.SetLatched;
import storm2014.commands.Shift;
import storm2014.commands.SpinRoller;
import storm2014.commands.WaitForEncoder;
import storm2014.subsystems.Catapult;

/**
 *
 * @author Tim
 */
public class DriveAndShoot2Ball extends CommandGroup{
    
    private Command _waitAndIntake() {
        CommandGroup _waitAndIntake = new CommandGroup("Waits then rolls in the spinner");
        _waitAndIntake.addSequential(new WaitCommand(1.25));
        _waitAndIntake.addSequential(new SpinRoller(-1));
        return _waitAndIntake;
    }
    
    private Command _waitAndLetRoll(){
        CommandGroup _waitAndMoveArms = new CommandGroup();
        _waitAndMoveArms.addSequential(new WaitForEncoder(4200 - 1000));
        _waitAndMoveArms.addSequential(new SetArmPosition(0, false));
        _waitAndMoveArms.addSequential(new WaitCommand(0.25)); //if the ball arrives too quickly, remove this line
        _waitAndMoveArms.addSequential(new SetArmPosition(2, false));
        
        CommandGroup _waitAndPrefire = new CommandGroup();
        _waitAndPrefire.addSequential(new WaitCommand(0.75));
        _waitAndPrefire.addSequential(new Command() {
            
            //INCREDIBLY UNSAFE
            
            {
                requires(Robot.catapult);
            }
            
            protected void initialize() {
                Robot.catapult.UNSAFEFireCatapult(true);
            }

            protected void execute() {}
            
            protected boolean isFinished() {
                return true;
            }

            protected void end() {}
            protected void interrupted() {}
        });
        _waitAndPrefire.addSequential(new WaitCommand(1.25));
        
        CommandGroup _waitAndLetRoll = new CommandGroup("rolls ball out of way");
        _waitAndLetRoll.addParallel(_waitAndPrefire);
        _waitAndLetRoll.addParallel(_waitAndMoveArms);
        _waitAndLetRoll.addSequential(new WaitForEncoder(4200 - 300));
        _waitAndLetRoll.addSequential(new SpinRoller(0));
        _waitAndLetRoll.addSequential(new WaitForChildren());
        return _waitAndLetRoll;
    }
    
    public DriveAndShoot2Ball() {
        addSequential(new Shift(true));
        addSequential(new SetLatched(true));
        addSequential(new SetArmPosition(2,false));
        addParallel(new SpinRoller((float) -0.6));
        addSequential(new WaitCommand(0.3));
        addParallel(_waitAndLetRoll());
        addSequential(new DriveForward(1, 4200));
        addSequential(new WaitCommand(1.0));
        addSequential(new WaitForChildren());
//        addSequential(new );
        addSequential(new Launch());
        addParallel(_waitAndIntake());
        addSequential(new Reset());
        addParallel(new PreFire());
        addSequential(new WaitCommand(0.5));
        addSequential(new SpinRoller(0));
        addSequential(new SetArmPosition(0, false));
        addSequential(new WaitCommand(0.5));
        addSequential(new SetArmPosition(2, false));
        addSequential(new WaitCommand(1.0));
        addSequential(new Launch());
        addSequential(new Reset());
    }
    
}
