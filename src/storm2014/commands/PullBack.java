package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/**
 *
 * @author Matthew Rassmann/Garrett
 */
public class PullBack extends Command {
     double _power;
     double _distance;
     
    public PullBack(double power, double distance){
        requires(Robot.catapult);
    }
   
    protected void initialize() {
       Robot.catapult.resetWinchEncoder();
    }

    protected void execute() {
        Robot.catapult.setWinchRawVal(_power);
    }

    protected boolean isFinished() {
        return (Math.abs(Robot.catapult.getWinchDistance())>=_distance);
    }

    protected void end() {
       Robot.catapult.setWinchRawVal(0.0);
    }

    protected void interrupted() {
        end();
       
    }
    
    public void setPower(double power){
        _power = power;
    }
    
    public void setDistance(double distance){
        _distance = distance;
    }
}
