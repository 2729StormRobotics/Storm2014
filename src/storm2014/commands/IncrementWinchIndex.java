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


public class IncrementWinchIndex extends Command{
    
    protected void initialize() {
        Robot.catapult.setIndex((Robot.catapult.getCurrentIndex() + 1)%Robot.catapult.getNumPresets());
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
    
}
