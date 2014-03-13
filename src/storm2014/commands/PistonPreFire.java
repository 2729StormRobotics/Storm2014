/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Tim
 */
public class PistonPreFire extends Command{

    protected void initialize() {
        if(Robot.catapult.isLatched()) Robot.catapult.fireCatapult();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return !Robot.catapult.isLatched();
    }

    protected void end() {
        if(Robot.catapult.isLatched()) Robot.catapult.resetCatapult();
    }

    protected void interrupted() {
        end();
    }
    
}
