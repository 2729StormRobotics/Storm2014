/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Matthew Rassmann
 */
public class CatapultReload extends Command {
     double _power;
     double _distance;
    public CatapultReload(double power, double distance){
        requires(Robot.catapult);
        _power=power;
        _distance = distance;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
       Robot.catapult.resetWinchEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.catapult.setWinchRawVal(_power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_power >= 0 && Robot.catapult.getWinchDistance() >= _distance) || 
               (_power < 0 && Robot.catapult.getWinchDistance() <= -_distance);
    }

    // Called once after isFinished returns true
    protected void end() {
       Robot.catapult.setWinchRawVal(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
       
    }
}
