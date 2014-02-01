package storm2014.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.StringPot;

/**
 *
 * @author garrett
 */

public class Tilter extends Subsystem{
    
    private StringPot _stringpot = new StringPot(RobotMap.PORT_SENSOR_STRINGPOT);
    private Talon _talon = new Talon(RobotMap.PORT_MOTOR_TILTER);
    private ADXL345_I2C _acc = new ADXL345_I2C(RobotMap.MODULE_SENSOR_ACCELEROMETER, ADXL345_I2C.DataFormat_Range.k2G);
    
    protected void initDefaultCommand() {
        
    }
    
    public void setRawVal(double speed){
        _talon.set(speed);
    }
    
    public double getVolts(){
        return _stringpot.get();
    }
    
    public double getAccValue(){
        return _acc.pidGet(); //TODO get bobo to fix this
    }
}
