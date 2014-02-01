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
 * @author Matthew Rassmann/Garrett
 */
public class SetEngaged extends Command {
   
    private boolean _b;
    
    public SetEngaged(boolean b){
       requires(Robot.catapult);
       _b = b;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
       if(_b){
           Robot.catapult.engage();
       } else{
           Robot.catapult.disengage();
       }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return true;
    }

    // Called once after isFinished returns true
    protected void end() {
       
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
