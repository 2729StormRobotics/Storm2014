package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Garrett
 */

public class Engage extends Command {

    public Engage(){
        requires(Robot.catapult);
    }
    
    protected void initialize() {
        Robot.catapult.engage(true);
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
       return true;
    }

    protected void end() {
        
    }

    protected void interrupted() {
       end();
    }
    
}
