package storm2014.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.tables.ITable;

public class CurrentSensor {
    
    private final AnalogChannel currentSensor;
    private ITable _table;
    
    public CurrentSensor(int channel){
        currentSensor = new AnalogChannel(channel);
    }
    
    /** Returns (approximate) amperage being drawn. */
    public double getAmps(){
        return convertToAmps(currentSensor.getVoltage());
    }
    
    /** Returns voltage drop being read. */
    public double getVolts(){
        return currentSensor.getVoltage();
    }
    
    /** Puts current data into the {@link ITable}. */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("voltage", currentSensor.getVoltage());
            _table.putNumber("amperage", getAmps());
        }
    }

    public void startLiveWindowMode() {}
    public void stopLiveWindowMode() {}

    /** Sets up an {@link ITable} for writing. */
    public void initTable(ITable table) {
        _table = table;
        updateTable();
    }

    /** Returns the currently used {@link ITable}. */
    public ITable getTable() {
        return _table;
    }

    /** We have a custom widget, so it gets a custom type. */
    public String getSmartDashboardType() {
        return "Current Sensor";
    }
    
    private double convertToAmps(double voltage){
        return 32.6458 * voltage - 20.1785; //Derived using linear regression with experiment values
    }
}
