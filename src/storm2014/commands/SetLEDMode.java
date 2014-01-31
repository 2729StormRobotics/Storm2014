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
    
    private byte _r, _g, _b;
    
    public SetLEDMode(int mode){
        _mode = mode;
        _r = 0;
        _g = 0;
        _b = 0;
    }
    public SetLEDMode(int mode, byte r, byte g, byte b){
        _r = r;
        _g = g;
        _b = b;
    }
    protected void initialize() {
        Robot.leds.setMode(_mode, _r, _g, _b);
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
    public void setColor(byte r, byte g, byte b){
        _r = r;
        _g = g;
        _b = b;
    }
    public void setRed(byte r){
        _r = r;
    }
    public void setGreen(byte g){
        _g = g;
    }
    public void setBlue(byte b){
        _b = b;
    }
    public int getMode(){
        return _mode;
    }
}

