package main.operation;

import main.eval.Eval;
import main.value.Combination;

public class Car implements Operation {
    public Object Do(Object parameters) throws Exception {
        Object pair = Eval.E(parameters);
        if(!(pair instanceof Combination)) {
            return pair;
        }
        return ((Combination) pair).GetFirst();
    }
}
