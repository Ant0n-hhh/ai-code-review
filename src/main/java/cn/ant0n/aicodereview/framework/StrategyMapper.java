package cn.ant0n.aicodereview.framework;

public interface StrategyMapper<T, D, R> {

    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext);
}
