package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

//Not tested
public class SetEngagedWinch extends Command {
    private final boolean _engaged;
    
    public SetEngagedWinch(boolean engaged){
       requires(Robot.catapult);
       _engaged = engaged;
    }
    
    protected void initialize() {
       if(_engaged){
           Robot.catapult.engageWinch();
       } else{
           Robot.catapult.disengageWinch();
       }
    }

    protected void execute() {}

    protected boolean isFinished() {
      return true;
    }

    protected void end() {}
    protected void interrupted() {}
}
