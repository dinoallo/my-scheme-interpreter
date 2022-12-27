package main.operation;

import main.eval.Eval;
import main.value.Combination;

public class Cons implements Operation {
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        Object first = Eval.E(((Combination) parameters).GetFirst());
        Object p2 = ((Combination) parameters).GetSecond();
        assert p2 instanceof Combination;
        Object rest = Eval.E(((Combination) p2).GetFirst());
        return new Combination(first, rest);
    }
}
