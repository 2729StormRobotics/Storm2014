package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 *Spins the roller a given direction.
 */
//Not Tested
public class SpinRoller extends Command {
    private final float _direction;
    public SpinRoller(float direction) {
        requires(Robot.intake);
        _direction = direction;
    }
    
    protected void initialize() {
        Robot.intake.setRollerRaw(_direction);
    }
    
    protected void execute() {}
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {}
    protected void interrupted() {}
}

