package storm2014.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import java.util.Timer;
import java.util.TimerTask;
import storm2014.Robot;
import storm2014.subsystems.LEDStrip;

public class DribbleSpeedCalculator {
    
    private PIDSource _pidSource;
    private PIDSource _pidVelocity;
    private PIDOutput _pidOutput;
    private double _curSpeed;
    private double _curVelocity;
    private double _output;
    private Timer _timer  = new Timer();
    private double _period;
    private boolean _enabled;
    
    private class _bTask extends TimerTask {
        
        public void run(){
            if(_enabled){
                _curVelocity = _pidVelocity.pidGet();
                _curSpeed = _pidSource.pidGet();
                _curVelocity = _pidVelocity.pidGet();
                
                //TODO add formula to calculate output. will calculate the speed to spin roller to dribble ball.
                //covert curvelocity to usable value.
            }
        }
    }
    
    public DribbleSpeedCalculator(PIDOutput pidoutput, PIDSource pidsource, PIDSource pidPosSource, double period){
        _pidOutput = pidoutput;
        _pidSource = pidsource;
        _pidVelocity = pidPosSource;
        _period = period;
        _timer.schedule(new DribbleSpeedCalculator._bTask(), 0, (long) (1000 * _period));
    }
    
    public boolean isEnable() {
        return _enabled;
    }
    
    public void disable() {
        _output = 0;
        _pidOutput.pidWrite(0);
        _enabled = false;
        Robot.leds.setMode(LEDStrip.ColorCycleMode);
    }
    
    public void enable() {
        _enabled = true;
        Robot.leds.setMode(LEDStrip.USAMode);
    }
}
