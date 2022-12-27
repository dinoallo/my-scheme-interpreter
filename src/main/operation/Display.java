package main.operation;

import main.eval.Eval;

public class Display implements Operation{
    @Override
    public Object Do(Object parameters) throws Exception {
        Object val = Eval.E(parameters);
        System.out.print(val);
        return val;
    }
}
