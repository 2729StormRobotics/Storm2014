package storm2014.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.commands.ConvertToAngle;
import storm2014.commands.Tilt;
import storm2014.utilities.StringPot;

/**
 *
 * @author garrett
 */
//TODO waiting for joes array value list.

public class Tilter extends Subsystem{
    
    private StringPot _stringpot = new StringPot(RobotMap.PORT_SENSOR_STRINGPOT);
    private Talon _talon = new Talon(RobotMap.PORT_MOTOR_TILTER);
    private ConvertToAngle _convert = new ConvertToAngle();
    
    protected void initDefaultCommand() {
        setDefaultCommand(new Tilt(0.0));
    }
    
    public void setRawVal(double speed){
        _talon.set(speed);
    }
    
    public double getAngle(){
        return  _convert.getAngle(_stringpot.get());

    }
    
}
