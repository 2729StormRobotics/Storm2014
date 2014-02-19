/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.commands.LaunchWhenReady;

/**
 *
 * @author Tim
 */
public class WaitAndShoot extends CommandGroup{
    
    public WaitAndShoot(double timeToWait){
        addSequential(new WaitCommand(timeToWait));
        addSequential(new LaunchWhenReady());
    }
    
}
