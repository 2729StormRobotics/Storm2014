

package storm2014.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Timer;
import java.util.TimerTask;

public class TakeBackHalfController implements LiveWindowSendable {
    
    private class _bgTask extends TimerTask {
        
        public void run(){
            
            if(_enabled){
                
                _currentSpeed = _pidSource.pidGet(); //updates current speed.
                
                //lowers the overshoot of motor output.
                if((_oldSpeed < _setPoint && _currentSpeed > _setPoint) ||
                   (_oldSpeed > _setPoint && _currentSpeed < _setPoint )){
                    
                    _motorOutput = (_motorOutput + _oldMotorOutput)/2;
                    _oldMotorOutput = _motorOutput;
                }
                
                _motorOutput = _motorOutput + (_gain * _period * (_setPoint - _currentSpeed)); //tbh formula. ravioli ravioli give me the formuoli.
                
                
                if(_motorOutput > 1){
                    _motorOutput = 1;
                }
                if(_motorOutput < -1){
                    _motorOutput = -1;
                }
                _motorOutput = _motorOutput * MULTIPLIER; //lowers output
                _oldSpeed = _currentSpeed;
                _pidOutput.pidWrite(_motorOutput);
            }
        }
    }
    
    private PIDSource _pidSource;
    private PIDOutput _pidOutput;
    private double _setPoint;
    private double _currentSpeed;
    private double _oldSpeed;
    private double _oldMotorOutput = 0;
    private double _gain = 0;
    private double _motorOutput;
    private Timer _timer  = new Timer();
    private double _period;
    private boolean _enabled;
    public final double MULTIPLIER = .01;
    
    public TakeBackHalfController(PIDOutput pidoutput, PIDSource pidsource, double period){
        
        _pidSource = pidsource;
        _pidOutput = pidoutput;
        _setPoint = 0;
        _period = period;
        
        _timer.schedule(new _bgTask(), 0, (long) (1000 * _period));
    }
    
    
    //sets speed
    private void setSetpoint(double setPoint) {
        _setPoint = setPoint;
    }
    //sets gain
    private void setGain(double gain){
        _gain = gain;
    }
    
    //returns if the takebackcontroller is enabled
    public boolean isEnable() {
        return _enabled;
    }
    //disables takebackcontroller
    public void disable() {
        _pidOutput.pidWrite(0);
        _enabled = false;
        
        if (table != null) {
            table.putBoolean("enabled", false);
        }
    }
    //enables takebackcontroller
    public void enable() {
        _enabled = true;
        
        if (table != null) {
            table.putBoolean("enabled", true);
        }
    }
    
    //updates changes from smart dashboard
    private ITableListener listener = new ITableListener() {
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
            else if (key.equals("p")) {
                if(_gain != ((Double) value).doubleValue()){
                    setGain(((Double) value).doubleValue());
                }
            }
        }
    }; 
    
    private ITable table;
    public void initTable(ITable table){
        
        if(this.table!=null)
            this.table.removeTableListener(listener);
        this.table = table;
        if(table!=null){
            table.putNumber("setpoint", _setPoint);
            table.putBoolean("enabled", isEnable());
            table.putNumber("p", _gain);
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
