package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

public class DribbleBall extends Command {
    
    public DribbleBall(){
        requires(Robot.dribblerSub);
    }
    
    protected void initialize() {
        Robot.dribblerSub.enable();
    }
    
    protected void execute() {
        
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
        Robot.dribblerSub.disable();
    }
    
    protected void interrupted() {
        end();
    }
}
