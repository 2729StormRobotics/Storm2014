package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Tensions the winch.
 */
public class TensionWinch extends Command {
    private final double power = 1;
    private int prevIndex = -1;

    public TensionWinch(){
        requires(Robot.catapult);
    }
    
    protected void initialize() {
        setInterruptible(false);
        prevIndex = -1;
    }
    
    protected void execute() {
        int index = Robot.catapult.getCurrentIndex();
        double distance = Robot.catapult.getCurrentPreset();
        double currDistance = Robot.catapult.getWinchDistance();
        if(currDistance < distance) {
            if(prevIndex != index) {
                setInterruptible(false);
                Robot.catapult.setFinishedPreLaunch(false);
                Robot.catapult.setWinchPower(power);
            } else {
                Robot.catapult.setWinchPower(Math.max(0, Robot.oi.getTension()));
            }
        } else {
            setInterruptible(true);
            Robot.catapult.setFinishedPreLaunch(true);
            prevIndex = index;
            Robot.catapult.setWinchPower(Math.max(0, Robot.oi.getTension()));
        }
    }

    protected boolean isFinished() {
        return false;
    }
    protected void end() {}
    protected void interrupted() {}
}
