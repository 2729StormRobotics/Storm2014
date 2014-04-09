/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.Robot;
import storm2014.subsystems.LEDStrip;

/**
 *
 * @author Tim
 */
public class PreFire extends Command{

    public PreFire(){
        setInterruptible(false);
    }
   
    int _mode;
    long _currTime, _startTime;
    private static final String prefireReady = "Prefire ready";
    
    protected void initialize() {
        _mode = Robot.leds.getMode();
        _startTime = System.currentTimeMillis();
//        Robot.leds.setMode(LEDStrip.SetColorMode,(byte)255,(byte)0,(byte)0);
    }

    protected void execute() {
        if(Robot.catapult.isLatched()) Robot.catapult.fireCatapult();
        _currTime = System.currentTimeMillis();
        SmartDashboard.putBoolean(prefireReady, _currTime - _startTime >= 1.25 * 1000);
    }

    protected boolean isFinished() {
        return !Robot.catapult.isLatched();
    }

    protected void end() {
        SmartDashboard.putBoolean(prefireReady, false);
        if(Robot.catapult.isLatched()) Robot.catapult.resetCatapult();
//        Robot.leds.setMode(_mode);
    }

    protected void interrupted() {
        end();
    }
    
}
