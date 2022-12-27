package main.operation;

import main.eval.Eval;

public class Displayln implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        Object val = Eval.E(parameters);
        System.out.println(val);
        return val;
    }
}
