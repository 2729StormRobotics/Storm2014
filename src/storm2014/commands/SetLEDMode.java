package storm2014.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
import storm2014.subsystems.LEDStrip;
/**
 * Sets the LED Mode.
 */

public class SetLEDMode extends Command {
    private final int _mode;
    
    private byte _r, _g, _b;
    
    public SetLEDMode(int mode){
        _mode = mode;
        _r = 0;
        _g = 0;
        _b = 0;
    }
    public SetLEDMode(int mode, byte r, byte g, byte b){
        _mode = mode;
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
    
    public int getMode(){
        return _mode;
    }
    public int getR(){
        return _r;
    }
    public int getG(){
        return _g;
    }
    public int getB(){
        return _b;
    }
}

