package storm2014.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Timer;
import java.util.TimerTask;
import storm2014.Robot;
import storm2014.subsystems.LEDStrip;

public class TakeBackHalfPlusPlus implements LiveWindowSendable {
    
    private class _bgTask extends TimerTask {
        
        public void run(){
            
            if(_enabled){
                
                _currentSpeed = _pidSource.pidGet(); //updates current speed.
                _err = _setPoint - _currentSpeed;
               
                
                //lowers the overshoot of motor output.
                if((_oldSpeed < _setPoint && _currentSpeed > _setPoint) ||
                        (_oldSpeed > _setPoint && _currentSpeed < _setPoint )){
                    
                    _integral = (_integral + _oldMotorOutput)/2;
                    _oldMotorOutput = _integral;
                }
                
                _integral = _integral + _gain * _err * _period ; //tbh formula.
                
                if(_integral > _max/Math.abs(MULTIPLIER)){
                    _integral = _max/Math.abs(MULTIPLIER);
                }
                if(_integral < _min/Math.abs(MULTIPLIER)){
                    _integral = _min/Math.abs(MULTIPLIER);
                }
                
                _oldSpeed = _currentSpeed;
                _pidOutput.pidWrite( MULTIPLIER * (_integral + _propGain * _err)); //integral * multiplier 
            }
        }
    }
    
    private final PIDSource _pidSource;
    private final PIDOutput _pidOutput;
    private double _setPoint;
    private double _currentSpeed;
    private double _oldSpeed = 0;
    private double _oldMotorOutput = 0;
    private double _gain = 0;
    private double _integral;
    private double _min;
    private double _max;
    private final Timer _timer  = new Timer();
    private final double _period;
    private boolean _enabled;
    public final double MULTIPLIER = -.01;
    private double _err;
    private double _propGain;
   
    
    public TakeBackHalfPlusPlus(PIDOutput pidoutput, PIDSource pidsource, double period, double max, double min){
        
        _pidSource = pidsource;
        _pidOutput = pidoutput;
        _setPoint = 0;
        _period = period;
        _max = max;
        _min = min;
        _timer.schedule(new _bgTask(), 0, (long) (1000 * _period));
    }

    //sets speed
    public void setSetpoint(double setPoint) {
        _setPoint = setPoint;
    }
    //sets gain
    private void setGain(double gain){
        _gain = gain;
    }
    
    private void setPropGain(double propGain){
        _propGain = propGain;
    }
    
    public void setOutputRange(double min, double max){
        _min = min;
        _max = max;
    }
    
    //returns if the takebackcontroller is enabled
    public boolean isEnable() {
        return _enabled;
    }
    //disables takebackcontroller
    public void disable() {
        _oldMotorOutput = 0;
        _integral = 0;
        _pidOutput.pidWrite(0);
        _enabled = false;
        
        if (table != null) {
            table.putBoolean("enabled", false);
        }
    }
    //enables takebackcontroller
    public void enable() {
        _enabled = true;
        Robot.leds.setMode(LEDStrip.PileMode);
        
        if (table != null) {
            table.putBoolean("enabled", true);
        }
    }
    
    //updates changes from smart dashboard
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
            else if (key.equals("p")) {
                if(_gain != ((Double) value).doubleValue()){
                    setGain(((Double) value).doubleValue());
                }
            }
            
            else if (key.equals("d")) {
                if(_propGain != ((Double) value).doubleValue()){
                    setPropGain(((Double) value).doubleValue());
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
            table.putNumber("d", _propGain);
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
