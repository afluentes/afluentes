package afluentes.core.api;

import java.util.List;

public interface IReduceEvaluator2<X1, Y> extends ISyncFn1<IEvaluation<List<IEvaluation<? extends X1>>>, IEvaluation<Y>> {
}