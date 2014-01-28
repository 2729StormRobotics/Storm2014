package storm2014.utilities.pipeline;

/**
 *
 * @author Ginto
 */
public interface IFilter extends ISource {
    void addSample(double newVal,double dt);
}
