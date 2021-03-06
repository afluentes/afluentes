package afluentes.core.api;

import java.util.List;

public interface IReduceEvaluator<X1, Y> extends ISyncFn1<List<IEvaluation<? extends X1>>, IEvaluation<Y>> {
}