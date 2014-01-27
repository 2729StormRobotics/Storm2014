package storm2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;


/**
 *
 * @author Storm
 */
public class DriveForward extends Command {
    
    final double _speed;
    final double _distance;
       
    public DriveForward(double speed, double distance) {
        requires(Robot.driveTrain);
        _speed = speed;
        _distance = distance;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    Robot.driveTrain.clearEncoder();
    
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       Robot.driveTrain.tankDrive(_speed, _speed);    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_speed >= 0 && Robot.driveTrain.getLeftDistance() >= _distance) || 
               (_speed < 0 && Robot.driveTrain.getLeftDistance() <= -_distance);
        
        
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.tankDrive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
