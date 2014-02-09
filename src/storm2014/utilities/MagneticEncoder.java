package storm2014.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class MagneticEncoder implements LiveWindowSendable {
    private final AnalogChannel _channel;
    
    public MagneticEncoder(int channelNum) {
        _channel = new AnalogChannel(channelNum);
    }
    
    public double getAngle() {
        return _channel.getAverageVoltage()/5.0*360;
    }
    
    public double get() {
        return _channel.getAverageVoltage();
    }
    
    private ITable _table = null;

    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", get());
        }
    }
    public void startLiveWindowMode() {}
    public void stopLiveWindowMode() {}

    public void initTable(ITable subtable) {
        _table = subtable;
        updateTable();
    }

    public ITable getTable() {
        return _table;
    }

    public String getSmartDashboardType() {
        return "Analog Input";
    }
}
