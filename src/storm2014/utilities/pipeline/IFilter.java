package storm2014.utilities.pipeline;

public interface IFilter extends ISource {
    void addSample(double newVal,double dt);
}
