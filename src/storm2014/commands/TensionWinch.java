package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 * Tensions the winch.
 */
public class TensionWinch extends Command {
    private final double power = 1;
    private final double tolerance = 10.0;
    
    protected void initialize() {
        Robot.catapult.setIndex(0);
    }

    public TensionWinch(){
        requires(Robot.catapult);
    }
    
    protected void execute() {
        double distance = Robot.catapult.getCurrentPreset();
        if(Robot.catapult.getWinchDistance() < distance - tolerance) {
            Robot.catapult.setWinchPower(power);
        } else {
            Robot.catapult.setWinchPower(0);
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
