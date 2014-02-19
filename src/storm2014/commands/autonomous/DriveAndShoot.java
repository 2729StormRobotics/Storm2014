/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2014.commands.DriveForward;
import storm2014.commands.LaunchWhenReady;
import storm2014.commands.control.Conditional;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author Tim
 */
public class DriveAndShoot extends CommandGroup{
    
    public DriveAndShoot(){
        addSequential(new DriveForward(0.75, 1000));
        addSequential(new Conditional(new LaunchWhenReady(), new WaitAndShoot(5)) {
            
            protected boolean condition() {
                return VisionSystem.foundHotTarget();
            }
        });
    }
    
}
