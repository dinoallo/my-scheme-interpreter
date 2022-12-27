package main.operation;

import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

public class List implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        Object items = parameters;
        Object item = Eval.E(((Combination) items).GetFirst());
        if(((Combination) items).GetSecond() instanceof Nil) {
            return new Combination(item, new Nil());
        } else if(!(((Combination) items).GetSecond() instanceof Combination)) {
            throw new Exception("ill-formed list items!");
        }
        items = ((Combination) items).GetSecond();
        return new Combination(item, Do(items));
    }
}
