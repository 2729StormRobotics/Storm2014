package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Tensions the winch.
 */
public class TensionWinch extends Command {
    private final double power = 1;
    private final double tolerance = 25.0;
    private int prevIndex = -1;
    private boolean reachedPoint = false;
    
    protected void initialize() {
        Robot.catapult.setIndex(0);
        prevIndex = -1;
    }

    public TensionWinch(){
        requires(Robot.catapult);
    }
    
    protected void execute() {
        int index = Robot.catapult.getCurrentIndex();
        double distance = Robot.catapult.getCurrentPreset();
        double currDistance = Robot.catapult.getWinchDistance();
        if(currDistance < distance) {
            if(prevIndex != index) {
                Robot.catapult.setWinchPower(power);
            } else {
                Robot.catapult.setWinchPower(Math.max(0, Robot.oi.getTension()));
            }
        } else {
            prevIndex = index;
            Robot.catapult.setWinchPower(Math.max(0, Robot.oi.getTension()));
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}
