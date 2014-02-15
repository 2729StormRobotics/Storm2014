/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands.control;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author evan1026
 */
public class WaitForButton extends Command {
    
    private Button _button;
    private boolean _wasReleased;

    public WaitForButton(Button button){
        _button = button;
    }
    protected void initialize() {
        _wasReleased = false;
    }
    protected void execute() {
        if (!_button.get()) _wasReleased = true;
    }
    protected boolean isFinished() {
        return _wasReleased && _button.get();
    }
    protected void end() {}
    protected void interrupted() {
        end();
    }
    
}
