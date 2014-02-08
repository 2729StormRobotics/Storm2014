package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
import storm2014.subsystems.VisionSystem;

/**
 *
 * @author garrett
 */
//TODO
public class AutoAlign extends Command{
    
    private double _distance;
    private double _angle;
    private double _curAngle;
    
    public AutoAlign(){
        requires(Robot.tilter);
    }

    protected void initialize() {
        
    }

    protected void execute() {
        _distance = VisionSystem.getTargetDistance();
       //  _angle = sTODO waiting for lookup on joes table.
       // Robot.tilter waiting for convert to angle to be done.
    }

    protected boolean isFinished() {
      return Math.abs((_curAngle - _angle)/_angle) < .05;
    }

    protected void end() {
     
    }

    protected void interrupted() {
       
    }      
}
