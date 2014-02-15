/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.control;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author evan1026
 */
public class WaitForButton extends Command {
    
    private boolean _wasReleased;

    public WaitForButton() {
    }
    protected void initialize() {
        _wasReleased = false;
    }
    protected void execute() {
        if (!Robot.oi.isContinueButton()) _wasReleased = true;
    }
    protected boolean isFinished() {
        return _wasReleased && Robot.oi.isContinueButton();
    }
    protected void end() {}
    protected void interrupted() {
        end();
    }
    
}
