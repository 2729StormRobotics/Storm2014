

package storm2014.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.Timer;
import java.util.TimerTask;


public class BangBangController{

    //if less then set point gun it if you are greater drop down to 0 or drop down to lower value 
     private class _bgTask extends TimerTask {

        public void run() {
         curSpeed = _pidSource.pidGet(); //sets current speed to what the sensor is reading
           if(curSpeed < _speed){
              _pidOutput.pidWrite(1);
           }
           else{
                _pidOutput.pidWrite(0);
           }
        }
     }
   
    Timer _timer = new Timer();
    double _period;
    double _speed;
    double curSpeed;
    PIDOutput _pidOutput;
    PIDSource _pidSource;
    
    public BangBangController (PIDOutput pidOutput, PIDSource pidSource, double period, double speed){
        _pidSource = pidSource;
        _pidOutput = pidOutput;
        _period = period;
        _speed = speed;
        _timer.schedule(new _bgTask(), 0, (long) (1000*_period));
    }
}
    
