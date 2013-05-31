package afluentes.core.impl;

public class Constant<X1> extends Evaluation0<X1> {
    public Constant(X1 x1) {
        y = x1;
        status = EVALUATED;
    }

    @Override
    protected void evaluate() {}
}