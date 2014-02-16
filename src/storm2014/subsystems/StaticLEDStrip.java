/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.subsystems;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2014.RobotMap;
import storm2014.utilities.StaticLED;

/**
 * Interfaces with the LEDs on the bottom of the robot.
 */
public class StaticLEDStrip extends Subsystem implements NamedSendable {
    
    private StaticLED _red,
                      _green,
                      _blue;

    public StaticLEDStrip(){
        _red   = new StaticLED(RobotMap.PORT_STATIC_LED_RED);
        _green = new StaticLED(RobotMap.PORT_STATIC_LED_GREEN);
        _blue  = new StaticLED(RobotMap.PORT_STATIC_LED_BLUE);
    }
    
    protected void initDefaultCommand() {}
    
    public void setRed(short color){
        _red.setValue(color);
    }
    
    public void setGreen(short color){
        _green.setValue(color);
    }
    
    public void setBlue(short color){
        _blue.setValue(color);
    }
    
    public short getRed(){
        return _red.getValue();
    }
    
    public short getGreen(){
        return _green.getValue();
    }
    
    public short getBlue(){
        return _blue.getValue();
    }
    
    public void initTable(ITable table){
        this._table = table;
        if(table!=null){
            table.putNumber("Static Red", 0);
            table.putNumber("Static Green", 0);
            table.putNumber("Static Blue", 0);
            table.addTableListener(listener);
        }
    }

    private ITableListener listener = new ITableListener() {
        public void valueChanged(ITable table, String key, Object value, boolean isNew) {
            if (key.equals("Static Red")) {
                setRed((byte) _table.getNumber("Static Red"));
            }
            else if (key.equals("Static Green")) {
                setGreen((byte) _table.getNumber("Static Green"));
            }
            else if (key.equals("Static Blue")) {
                setBlue((byte) _table.getNumber("Static Blue"));
            }
        }
    };

    public ITable getTable(){
        return _table;
    }
    
    private ITable _table;
}
