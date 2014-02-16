package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;

/** 
 * Drives the robot based on joystick input (each side gets one axis). 
 */
public class TankDrive extends Command {
    public TankDrive() {
        requires(Robot.driveTrain);
    }
    
    protected void initialize() {}
    protected void execute() {
        double left  = Robot.oi.getLeftDrive(),
               right = Robot.oi.getRightDrive();
        Robot.driveTrain.tankDrive(left,right);
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {}
    protected void interrupted() {
        end();
    }
}
