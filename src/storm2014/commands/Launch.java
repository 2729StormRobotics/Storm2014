/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Tim
 */
public class Launch extends CommandGroup{
    public Launch(){
        setInterruptible(false);
        addSequential(new SetLatched(false));
        addSequential(new WaitCommand(0.5));
        addSequential(new FireCatapult(true));
    }
}
