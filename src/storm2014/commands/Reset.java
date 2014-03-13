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
public class Reset extends CommandGroup{
    public Reset(){
        addSequential(new FireCatapult(false));
        addSequential(new WaitCommand(.4));
        addSequential(new SetLatched(true));
    }
}
