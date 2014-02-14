
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
import storm2014.commands.control.Conditional;

/**
 *
 * @author Matthew
 */
public class LaunchIfReady extends Conditional {
    public LaunchIfReady(Command launch){
        super(launch, null);
    }

    protected boolean condition() {
        return Robot.catapult.isFinishedPreLaunch();
    }
}
