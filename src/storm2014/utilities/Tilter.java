package storm2014.utilities;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;

/**
 *
 * @author garrett
 */

public class Tilter extends Subsystem{
    
    private StringPot _stringpot = new StringPot(RobotMap.PORT_SENSOR_STRINGPOT);
    private Talon _talon = new Talon(RobotMap.PORT_MOTOR_TILTER);
    private double _angle;
    
    protected void initDefaultCommand() {
        
    }
    
    
    public void setRawVal(double speed){
        _talon.set(speed);
    }
    
    public double getVolts(){
        return _stringpot.get();
    }
}
