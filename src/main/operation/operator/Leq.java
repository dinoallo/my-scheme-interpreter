package main.operation.operator;

import main.operation.Operation;
import main.value.Bool;

public class Leq implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        Gt gt = new Gt();
        if(Bool.IsTrue(gt.Do(parameters))) return new Bool(Boolean.FALSE);
        else return new Bool(Boolean.TRUE);
    }
}
