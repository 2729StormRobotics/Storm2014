package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2014.RobotMap;

public class LEDRing extends Subsystem implements NamedSendable {
    
    private static final int FREQUENCY = 100;
    private static final double INIT_DUTY_CYCLE = 10;
    
    DigitalOutput out;
    
    public LEDRing(){
        System.out.println("Got before stuff!");
        out = new DigitalOutput(RobotMap.PORT_LED_RING);
        out.setPWMRate(FREQUENCY);
        out.enablePWM(INIT_DUTY_CYCLE / 100);
        SmartDashboard.putString("Stuff","Enabled PWM on port " + RobotMap.PORT_LED_RING + " and set it to rate " + FREQUENCY + " with an initial duty cycle of 50%");
    }
    
    public void setPercentage(double percent){
        out.updateDutyCycle(percent / 100.0);
        System.out.println("Set duty cycle to " + percent + "%.");
        
    }
    
    protected void initDefaultCommand() {}
    
    public void initTable(ITable table){
        this._table = table;
        if(table!=null){
            table.putNumber("LED Percentage", INIT_DUTY_CYCLE);
            table.addTableListener(listener);
        }
    }

    private ITableListener listener = new ITableListener() {
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