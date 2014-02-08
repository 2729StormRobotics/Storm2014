/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2014.RobotMap;

/**
 *
 * @author evan1026
 */
public class LEDRing extends Subsystem implements NamedSendable {
    
    private static final int FREQUENCY = 100;
    
    DigitalOutput out;
    
    public LEDRing(){
        out = new DigitalOutput(RobotMap.PORT_LED_RING);
        out.setPWMRate(FREQUENCY);
        out.enablePWM(50.0 / 100);
    }
    
    public void setPercentage(double percent){
        out.updateDutyCycle(percent / 100.0);
    }
    
    protected void initDefaultCommand() {}
    
    public void initTable(ITable table){
        this._table = table;
        if(table!=null){
            table.putNumber("LED Percentage", 50);
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