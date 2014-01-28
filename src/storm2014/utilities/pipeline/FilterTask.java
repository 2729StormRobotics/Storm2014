package storm2014.utilities.pipeline;

import java.util.Timer;
import java.util.TimerTask;
import storm2014.utilities.pipeline.IFilter;
import storm2014.utilities.pipeline.ISource;

/**
 *
 * @author Ginto
 */
public class FilterTask implements ISource {
    private final double  _dt;
    private final IFilter _filter;
    private final ISource _source;
    private final Timer   _timer = new Timer();
    
    public FilterTask(IFilter filter,ISource source,double dt) {
        _filter = filter;
        _source = source;
        _dt = dt;
        _timer.schedule(new TimerTask() {
            public void run() {
                _filter.addSample(_source.get(), _dt);
            }
        }, 0, (long)(1000*dt));
    }

    public double get() {
        return _filter.get();
    }
    
    public IFilter getFilter() {
        return _filter;
    }
    
    public ISource getSource() {
        return _source;
    }
}
