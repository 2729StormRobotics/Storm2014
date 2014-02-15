/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Tim
 */


public class IncrementWinchDistance extends Command{
    
    public final double [] pullBackPresets = new double[]{100, 283, 467, 650}; //presets are based on negation already in code
    public int presetIncrement = -1;

    protected void initialize() {
        presetIncrement++;
        presetIncrement%=pullBackPresets.length;
        new PullBack(pullBackPresets[presetIncrement]).start();
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
