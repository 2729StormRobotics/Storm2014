package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2014.RobotMap;

/**
 * A class that allows control over the LED ring on the camera.
 */
public class LEDRing extends Subsystem implements NamedSendable {
    private static final int FREQUENCY = 6000;
    private static final double INIT_DUTY_CYCLE = 20;
    
    private final DigitalOutput _out;
    
    public LEDRing(){
        _out = new DigitalOutput(RobotMap.PORT_LED_RING);
        _out.setPWMRate(FREQUENCY);
        _out.enablePWM(INIT_DUTY_CYCLE / 100);
    }
    
    public void setPercentage(double percent){
        _out.updateDutyCycle(percent / 100.0);
        
    }
    
    protected void initDefaultCommand() {}
    
    public void initTable(ITable table){
        this._table = table;
        if(table!=null){
            table.putNumber("LED Percentage", INIT_DUTY_CYCLE);
            table.addTableListener(listener);
        }
    }

    private final ITableListener listener = new ITableListener() {
        public void valueChanged(ITable table, String key, Object value, boolean isNew) {
            if (key.equals("LED Percentage")) {
                setPercentage(_table.getNumber("LED Percentage"));
            }   
        }
    };

    public ITable getTable(){
        return _table;
    }
    
    private ITable _table;
    
}