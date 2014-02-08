package storm2014.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.StringPotToAngle;
import storm2014.utilities.StringPot;

//TODO waiting for joes array value list.
public class Tilter extends Subsystem {
    
    private final StringPot _stringpot = new StringPot(RobotMap.PORT_SENSOR_STRINGPOT);
    private final Talon _tiltMotor = new Talon(RobotMap.PORT_MOTOR_TILTER);
    
    protected void initDefaultCommand() {}
    
    public void setRawVal(double speed){
        _tiltMotor.set(speed);
    }
    
    public double getAngle(){
        return StringPotToAngle.spotToAngle(_stringpot.get());
    }
}
