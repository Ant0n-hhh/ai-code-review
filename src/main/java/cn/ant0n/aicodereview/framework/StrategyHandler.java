package cn.ant0n.aicodereview.framework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface StrategyHandler<T, D, R> {

    StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws ExecutionException, InterruptedException, TimeoutException;
}
