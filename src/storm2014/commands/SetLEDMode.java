/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author evan1026
 */
public class SetLEDMode extends Command {

    private int _mode;
    
    public SetLEDMode(int mode){
        _mode = mode;
    }
    protected void initialize() {
        Robot.leds.setMode(_mode);
    }
    protected void execute() {}
    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
    protected void interrupted() {}
    
    public void setMode(int mode){
        _mode = mode;
    }
    public int getMode(){
        return _mode;
    }
    
}
