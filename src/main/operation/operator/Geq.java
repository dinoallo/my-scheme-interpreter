package main.operation.operator;

import main.operation.Operation;
import main.value.Bool;

public class Geq implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        Lt lt = new Lt();
        if(Bool.IsTrue(lt.Do(parameters))) return new Bool(Boolean.FALSE);
        else return new Bool(Boolean.TRUE);
    }
}