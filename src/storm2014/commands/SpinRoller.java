package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 *Spins the roller a given direction.
 */
//Not Tested
public class SpinRoller extends Command {
    private final int _direction;
    public SpinRoller(int direction) {
        requires(Robot.intake);
        _direction = direction;
    }
    
    protected void initialize() {
        if(_direction > 0) {
            Robot.intake.spinIn();
        } else if(_direction < 0) {
            Robot.intake.spinOut();
        } else {
            Robot.intake.stop();
        }
    }
    
    protected void execute() {}
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {}
    protected void interrupted() {}
}

