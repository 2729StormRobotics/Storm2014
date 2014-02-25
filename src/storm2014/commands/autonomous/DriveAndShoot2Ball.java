/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.commands.DriveForward;
import storm2014.commands.LaunchWhenReady;
import storm2014.commands.SetArmPosition;
import storm2014.commands.SpinRoller;

/**
 *
 * @author Tim
 */
public class DriveAndShoot2Ball extends CommandGroup{
    
    public DriveAndShoot2Ball(){
        addSequential(new DriveForward(0.75, 4700));
        addSequential(new SetArmPosition(2));
        addSequential(new WaitCommand(0.4));
        addSequential(new LaunchWhenReady());
        addSequential(new SetArmPosition(0));
        addSequential(new WaitCommand(0.4));
        addSequential(new SpinRoller(1));
        addSequential(new SetArmPosition(1));
        addSequential(new WaitCommand(0.4));
        addSequential(new SetArmPosition(2));
        addSequential(new WaitCommand(0.4));
        addSequential(new LaunchWhenReady());
        
    }
    
}
