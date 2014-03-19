/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.Robot;
import storm2014.commands.control.Conditional;

/**
 *
 * @author Tim
 */
public class Reset extends CommandGroup{
    public Reset(){
        setInterruptible(false);
        addSequential(new Conditional(new SetLatched(false), null) {
            
            protected boolean condition() {
                return !Robot.catapult.isPrefired();
            }
        });
        addSequential(new FireCatapult(false));
        addSequential(new WaitCommand(1.25));
        addSequential(new SetLatched(true));
        addSequential(new WaitCommand(0.25));
    }
}
