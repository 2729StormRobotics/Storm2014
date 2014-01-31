package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class Latch extends Command{

    public Latch(){
        requires(Robot.catapult);
    }
    
    protected void initialize() {
        Robot.catapult.latch(true);
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
