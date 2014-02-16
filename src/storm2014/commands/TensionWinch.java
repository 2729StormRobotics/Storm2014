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
public class TensionWinch extends Command{

    double _distance;
    double power = 0.5;
    
    protected void initialize() {
        Robot.catapult.setIndex(0);
    }

    public TensionWinch(double distance){
        requires(Robot.catapult);
        _distance = distance;
    }
    
    protected void execute() {
        while(Robot.catapult.getWinchDistance() < _distance)
            Robot.catapult.setWinchPower(power);
        Robot.catapult.setWinchPower(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}
