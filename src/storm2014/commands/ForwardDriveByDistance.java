package storm2014.commands;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.commands.TankDrive;
import storm2014.Robot;


/**
 *
 * @author Storm
 */
public class ForwardDriveByDistance extends Command {
    
    final double _power;
    final double _distance;
       
    public ForwardDriveByDistance(double power, double distance) {
        requires(Robot.driveTrain);
        _power = power;
        _distance = distance;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    Robot.driveTrain.clearEncoder();
    
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       Robot.driveTrain.tankDrive(_power, _power);    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_power >= 0 && Robot.driveTrain.getLeftDistance() >= _distance) || 
               (_power < 0 && Robot.driveTrain.getLeftDistance() <= -_distance);
        
        
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
