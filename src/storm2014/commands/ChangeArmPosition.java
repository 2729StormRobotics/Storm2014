package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author garrett
 */
public class ChangeArmPosition extends Command {
    
    private int _curMode;
    private int _increment;
    
    public ChangeArmPosition(int increment){
        requires(Robot.intake);
        _increment = increment;
    } 

    protected void initialize() {
       _curMode = Robot.intake.getMode();
       Robot.intake.setMode((((_curMode + _increment ) % 4) + 4) % 4);
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
