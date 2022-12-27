package main.operation;

import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

public class Length implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        Object _arg = ((Combination) parameters).GetFirst();
        Object arg = Eval.E(_arg);
        assert arg instanceof Combination;
        int count = 0;
        while (!(arg instanceof Nil)) {
            assert arg instanceof Combination;
            count++;
            arg = ((Combination) arg).GetSecond();
        }
        return count;
    }
}
