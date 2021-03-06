package storm2014.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Spins up fast then ocelates around a value.
 */
public class BangBangController implements LiveWindowSendable {
    //if less then set point gun it if you are greater drop down to 0 or drop down to lower value
    private class _bgTask extends TimerTask {
        public void run() {
            if(_enabled){
                //System.out.println("bg task is running");
                curSpeed = _pidSource.pidGet(); //sets current speed to what the sensor is reading
                if(curSpeed < _setPoint){
                    _pidOutput.pidWrite(1);
                }
                else{
                    _pidOutput.pidWrite(0);
                }
            }
        }
    }
    
    private final Timer _timer = new Timer();
    private final double _period;
    private double _setPoint;
    private double curSpeed;
    private final PIDOutput _pidOutput;
    private final PIDSource _pidSource;
    
    private boolean _enabled = false;
    
    public BangBangController (PIDOutput pidOutput, PIDSource pidSource, double period){
        _pidSource = pidSource;
        _pidOutput = pidOutput;
        _period = period;
        _setPoint = 0;
        _timer.schedule(new _bgTask(), 0, (long) (1000*_period));
    }
    
    //sets speed
    private void setSetpoint(double setPoint) {
        _setPoint = setPoint;
    }
    
    //returns if the bangbangcontroller is enabled
    public boolean isEnable() {
        return _enabled;
    }
    //disables bangbangcontroller
    public void disable() {
        _pidOutput.pidWrite(0);
        _enabled = false;
        
        if (table != null) {
            table.putBoolean("enabled", false);
        }
    }
    //enables bang bang controller
    public void enable() {
        _enabled = true;
        
        if (table != null) {
            table.putBoolean("enabled", true);
        }
    }
    
    //updates values when user changes them
    private final ITableListener listener = new ITableListener() {
        public void valueChanged(ITable table, String key, Object value, boolean isNew){
            
            if (key.equals("setpoint")) {
                if (_setPoint != ((Double) value).doubleValue())
                    setSetpoint(((Double) value).doubleValue());
            }
            else if (key.equals("enabled")) {
                if (_enabled != ((Boolean) value).booleanValue()) {
                    if (((Boolean) value).booleanValue()) {
                        enable();
                    } else {
                        disable();
                    }
                }
                
            }
        }
        
    };
    
    //adds a table to smart dashboard
    private ITable table;
    
    public void initTable(ITable table){
        
        if(this.table!=null)
            this.table.removeTableListener(listener);
        this.table = table;
        if(table!=null){
            
            table.putNumber("setpoint", 0);
            table.putBoolean("enabled", isEnable());
            table.addTableListener(listener, false);
        }
    }
    
    public ITable getTable(){
        return table;
    }
    
    public void updateTable() {
        
    }
    
    public void startLiveWindowMode() {
        disable();
    }
    public void stopLiveWindowMode() {
        
    }
    
    public String getSmartDashboardType() {
        return "PIDController";
    }
    
}

