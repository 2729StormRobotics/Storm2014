package storm2014.utilities;

/**
 * This prevents flickery signals from deceiving you
 */
public class Debouncer {
    private final long _targetTime;
    private long _prevTime = 0;

    public Debouncer(double timeDelay) {
        _targetTime = (long)(timeDelay*100);
    }
    
    public boolean check(boolean val) {
        if(!val) {
            reset();
        }
        return System.currentTimeMillis() >= _prevTime+_targetTime;
    }
    public void reset() {
        _prevTime = System.currentTimeMillis();
    }
}
