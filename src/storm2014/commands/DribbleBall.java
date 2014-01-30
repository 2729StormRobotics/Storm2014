package storm2014.commands;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Timer;
import storm2014.Robot;
import storm2014.subsystems.LEDStrip;

public class DribbleBall extends Command {
    
    private double _output;
    private double _robotVelocity;
    private double _rollerSpeed;
    
    public DribbleBall(){
        requires(Robot.intake);
    }
    
    protected void initialize() {
        Robot.leds.setMode(LEDStrip.USAMode);
    }
    
    protected void execute() {
        _robotVelocity = Robot.intake.getRollerSpeed();
        _rollerSpeed = Robot.intake.getRollerSpeed();
        _robotVelocity = Robot.intake.getEncoderValue();
        //TODO add formula to calculate output. will calculate the speed to spin roller to dribble ball.
        //covert curvelocity to usable value.
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
        Robot.intake.setRollerSpeed(0);
        Robot.leds.setMode(LEDStrip.ColorCycleMode);
    }
    
    protected void interrupted() {
        end();
    }
    
}